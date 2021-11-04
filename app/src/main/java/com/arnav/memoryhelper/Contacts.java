package com.arnav.memoryhelper;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.widget.Toast;

public class Contacts extends AppCompatActivity {


    CardView ecButton;
    SQLiteDatabase mydb;
    CardView ecdelallButton;
    CardView ecdelButton;
    CardView photoAdd;
    CardView photoDel;
    CardView photoshow;
    CardView photoDelall;


    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        enableRunTimePermission();

        mydb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mydb.execSQL("create table if not exists contacts (name varchar, number varchar, status varchar);");

        //for delete single button

        ecdelButton = findViewById(R.id.ecdel_button);
        ecdelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //mydb.execSQL("insert into contacts values('arnav', '+918378987322','active'); ");
                startActivityForResult(a, 1);

            }
        });

        //delete button
        //show photos
        photoshow = findViewById(R.id.photo_show);
        photoshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityShowPhotos();
            }
        });

        //del photos

        photoDel = findViewById(R.id.photo_del);
        photoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
                startActivityForResult(chooserIntent, 2);
            }
        });

        //del all phitos

        photoDelall = findViewById(R.id.photo_delall);
        photoDelall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
                */
               // startActivityForResult(null, 222);
                mydb.execSQL("create table if not exists images (path varchar, name varchar, status varchar);");
                String delstmt = "delete from images;";
                mydb.execSQL(delstmt);

               // Toast.makeText(this, delstmt, Toast.LENGTH_LONG).show();

            }
        });



        ecdelallButton = findViewById(R.id.ecdelall_button);
        ecdelallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.execSQL("delete from contacts;");
                //Toast.makeText(getParent(),"Contacts have been deleted",Toast.LENGTH_SHORT).show();
            }
        });


        //add button
        ecButton = findViewById(R.id.ecadd_button);
        ecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Intent a = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //startActivityForResult(a, 7);

                Intent a = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //mydb.execSQL("insert into contacts values('arnav', '+918378987322','active'); ");
                startActivityForResult(a, 11);


                //               openActivityContacts();

            }


        });

        // add photo
        photoAdd = findViewById(R.id.photo_add);
        photoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/* video/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/* video/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);


            }
        });
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (1): //delete contacts
                if (resultCode == Activity.RESULT_OK) {
                    printContacts();
                    Uri uri;
                    Cursor c1, c2;
                    String tName, tNum, tId, idRes = "";
                    int idResHolder;
                    uri = data.getData();
                    c1 = getContentResolver().query(uri, null, null, null, null);
                    if (c1.moveToFirst()) {
                        tName = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        tId = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));
                        idRes = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        idResHolder = Integer.valueOf(idRes);
                        if (idResHolder == 1) {
                            c2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + tId,
                                    null,
                                    null);

                            while (c2.moveToNext()) {
                                tNum = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                String delStatement = "delete from contacts where name = '" + tName + "' and number = '" + tNum + "';";
                                mydb.execSQL(delStatement);
                                c2.close();
                                c1.close();
                                break;
                                // Toast.makeText(this, tName + " : " + tNum, Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
                break;
            case (11):  //add contacts
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri;
                    Cursor c1, c2;
                    String tName, tNum, tId, idRes = "";
                    int idResHolder;


                    uri = data.getData();
                    c1 = getContentResolver().query(uri, null, null, null, null);
                    if (c1.moveToFirst()) {
                        tName = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        tId = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));
                        idRes = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        idResHolder = Integer.valueOf(idRes);
                        if (idResHolder == 1) {
                            c2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + tId,
                                    null,
                                    null);

                            while (c2.moveToNext()) {
                                tNum = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                String insertStatement = "insert into contacts values('" + tName + "','" + tNum + "','active');";
                                mydb.execSQL(insertStatement);
                                c1.close();
                                c2.close();
                                break;
                                // Toast.makeText(this, tName + " : " + tNum, Toast.LENGTH_LONG).show();

                            }

                        }
                    }



                    /*

                    Uri uri1 = data.getData();

                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    String hasPhone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    String phoneNumber;
                    int idx = 0;
                    try {
                        idx = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    phoneNumber = cursor.getString(idx); //cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Toast.makeText(this, phoneNumber, 100);

                    Cursor cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,null, null);
                    while (cursor2.moveToNext()){
                        phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String name = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String insertStatement = "insert into contacts values('" + name+ "','"+phoneNumber+"','active');";
                        mydb.execSQL(insertStatement);
                    }
                }

                openActivityContacts();

                */
                    break;
                }

            case (2): //delete table
                if (resultCode == Activity.RESULT_OK) {
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();

                    String[] fPC = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, fPC, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(fPC[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();

                    mydb.execSQL("create table if not exists images (path varchar, name varchar, status varchar);");
                    //String delstmt = "delete from images;";

                    String delstmt = "delete from images where path = '" + imgDecodableString + "';";
                    mydb.execSQL(delstmt);
                    cursor.close();

                    Toast.makeText(this, delstmt, Toast.LENGTH_LONG).show();
                    break;
                }
                break;
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

                    mydb.execSQL("create table if not exists images (id int, path varchar, name varchar, status varchar);");

                    int count = 0;
                    cursor = mydb.rawQuery("SELECT count(*) FROM images WHERE status = 'active'", null);
                    cursor.moveToFirst();
                    count = cursor.getInt(0);

                    String insertstmt = "insert into images values ("+ (count)+",'" + imgDecodableString + "','" + "" + "','active');";
                    mydb.execSQL(insertstmt);
                    cursor.close();

                    Toast.makeText(this, insertstmt, Toast.LENGTH_LONG).show();
                    break;
                }
                break;
            case (222): //delete all table
                if (resultCode == Activity.RESULT_OK) {
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();

                    /*
                    String[] fPC = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, fPC, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(fPC[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    */
                    mydb.execSQL("create table if not exists images (path varchar, name varchar, status varchar);");
                    String delstmt = "delete from images;";
                    mydb.execSQL(delstmt);

                    Toast.makeText(this, delstmt, Toast.LENGTH_LONG).show();
                    break;
                }
                break;

        }

        if (requestCode == 1 || requestCode == 11) {
            printContacts();
        }

        /*public void openActivityContacts () {
            Intent z = new Intent(this, Contacts.class);
            startActivity(z);
        }*/
    }

    public void enableRunTimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){
            //Toast.makeText(this, )
        } else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int rc, String perm[], int[] res){
        switch (rc){
            case 1:
                if (res.length > 0 && res[0] == PackageManager.PERMISSION_GRANTED){

                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }



    void printContacts(){
        String numList = "";
        Cursor c = mydb.rawQuery("select * from contacts where status = 'active'",null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                String num = c.getString(c.getColumnIndex("number"));
                String name = c.getString(c.getColumnIndex("name"));
                numList += "[" + name + "=" + num + "]\n";

                Toast.makeText(this,  numList, Toast.LENGTH_LONG).show();

                c.moveToNext();
            }
        }

        if (numList.equals("")){
            numList += "Emergency Contact List is Empty";
        }
        Toast.makeText(this, numList, Toast.LENGTH_LONG).show();
        c.close();
    }
    public void openActivityShowPhotos(){
        Intent z = new Intent(this, ShowPhoto.class);
        startActivity(z);
    }

}
