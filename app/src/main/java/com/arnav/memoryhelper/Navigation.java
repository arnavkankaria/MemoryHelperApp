package com.arnav.memoryhelper;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Navigation extends AppCompatActivity {

    SQLiteDatabase mydb;
    Button chooseImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // add photo
        chooseImg = findViewById(R.id.choose_button_1);
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/* video/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/* video/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);

            }
        });
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (22): //add image
                if (resultCode == Activity.RESULT_OK) {
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();

                    String[] fPC = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, fPC, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(fPC[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();

                    mydb.execSQL("create table if not exists img (path varchar, name varchar, status varchar);");
                    String insertstmt = "insert into img values ('" + imgDecodableString + "','" + "" + "','active');";
                    mydb.execSQL(insertstmt);
                    cursor.close();

                    Toast.makeText(this, insertstmt, Toast.LENGTH_LONG).show();
                    break;
                }
        }


    }
}
