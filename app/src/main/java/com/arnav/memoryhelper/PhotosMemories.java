package com.arnav.memoryhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotosMemories extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_memories);

      Button chooseImage = findViewById(R.id.image_choose);
      chooseImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (ActivityCompat.checkSelfPermission(PhotosMemories.this,
                      Manifest.permission.READ_EXTERNAL_STORAGE)
                      != PackageManager.PERMISSION_GRANTED){

                  ActivityCompat.requestPermissions(PhotosMemories.this,
                          new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                          100);
                  return;
              }

              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
              intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
              intent.setType("image/* ");
              startActivityForResult(intent, 1);


          }
      });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            final ImageView imageShow = findViewById(R.id.image_show);

            final List<Bitmap> bitmaps = new ArrayList<>();

            ClipData clipData = data.getClipData();

            if (clipData != null) {

                for (int i = 0; i < clipData.getItemCount(); i++) {

                    Uri imageUri = clipData.getItemAt(i).getUri();

                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        bitmaps.add(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            } else {

                Uri imageUri = data.getData();

                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);

                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    bitmaps.add(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (final Bitmap b : bitmaps) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageShow.setImageBitmap(b);
                            }
                        });

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }
    }
}
