package com.arnav.memoryhelper;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class nav extends AppCompatActivity {
    SQLiteDatabase mybb;
    CardView photoAdd;
    CardView photoDel;
    CardView photoDelall;
    ImageView iv0, iv1, iv2, iv3, iv4, iv5;
    EditText  et0, et1, et2, et3, et4, et5;
    TextView add0, add1, add2, add3, add4, add5;
    TextView delete0, delete1, delete2, delete3, delete4, delete5;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        refresh = findViewById(R.id.refresh);
        et0 = findViewById(R.id.img_0_edit);
        et1 = findViewById(R.id.img_1_edit);
        et2 = findViewById(R.id.img_2_edit);
        et3 = findViewById(R.id.img_3_edit);
        et4 = findViewById(R.id.img_4_edit);
        et5 = findViewById(R.id.img_5_edit);

        iv0 = findViewById(R.id.img_0);
        iv1 = findViewById(R.id.img_1);
        iv2 = findViewById(R.id.img_2);
        iv3 = findViewById(R.id.img_3);
        iv4 = findViewById(R.id.img_4);
        iv5 = findViewById(R.id.img_5);

        add0 = findViewById(R.id.add_1);
        add1 = findViewById(R.id.add_2);
        add2 = findViewById(R.id.add_3);
        add3 = findViewById(R.id.add_4);
        add4 = findViewById(R.id.add_5);
        add5 = findViewById(R.id.add_6);

        delete0 = findViewById(R.id.delete_1);
        delete1 = findViewById(R.id.delete_2);
        delete2 = findViewById(R.id.delete_3);
        delete3 = findViewById(R.id.delete_4);
        delete4 = findViewById(R.id.delete_5);
        delete5 = findViewById(R.id.delete_6);

        refreshContent();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //refreshContent();
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String delstmt = "delete from nav_images;";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        add0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        delete0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '0' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '1' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '2' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '3' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        delete4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '4' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });

        delete5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delstmt = "delete from nav_images where id = '5' AND status = 'active';";
                mybb.execSQL(delstmt);

                refreshContent();

            }
        });


        mybb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");

        // add photo
        photoAdd = findViewById(R.id.photo_add);
        photoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, 22);
            }
        });

        photoDel = findViewById(R.id.photo_del);
        photoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
                //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
                startActivityForResult(chooserIntent, 2);
            }
        });

        //del all photos

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
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String delstmt = "delete from nav_images;";
                mybb.execSQL(delstmt);

                // Toast.makeText(this, delstmt, Toast.LENGTH_LONG).show();

            }
        });

        //mTitleField.setText(mCrime.getTitle());

        et0.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et0.getText() + "' where id = 0;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
        et1.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et1.getText() + "' where id = 1;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
        et2.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et2.getText() + "' where id = 2;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
        et3.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et3.getText() + "' where id = 3;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
        et4.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et4.getText() + "' where id = 4;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
        et5.addTextChangedListener(new TextWatcher() {
            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                String stmt = "update nav_images set address = '" + et5.getText() + "' where id = 5;";
                mybb.execSQL(stmt);
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}
            public void afterTextChanged(Editable c) {}
        });
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
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

                    mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                    //String delstmt = "delete from nav_images;";

                    String delstmt = "delete from nav_images where path = '" + imgDecodableString + "';";
                    mybb.execSQL(delstmt);
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

                    int count = 0;
                    mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");
                    cursor = mybb.rawQuery("SELECT count(*) FROM nav_images WHERE status = 'active'", null);
                    cursor.moveToFirst();
                    count = cursor.getInt(0);

                    String sqlstmt = "insert into nav_images values ("+ (count)+",'" + imgDecodableString + "','" + "" + "','active','');";
                    String insertstmt = sqlstmt;    //+  '" + imgDecodableString + "','" + "" + "','active','');";
                    mybb.execSQL(insertstmt);
                    cursor.close();

                    refreshContent();

                    Toast.makeText(this, insertstmt, Toast.LENGTH_LONG).show();
                    break;
                }
                break;
        }
    }

    public void refreshContent(){

        add0.setVisibility(View.GONE);
        add1.setVisibility(View.GONE);
        add2.setVisibility(View.GONE);
        add3.setVisibility(View.GONE);
        add4.setVisibility(View.GONE);
        add5.setVisibility(View.GONE);

        delete0.setVisibility(View.GONE);
        delete1.setVisibility(View.GONE);
        delete2.setVisibility(View.GONE);
        delete3.setVisibility(View.GONE);
        delete4.setVisibility(View.GONE);
        delete5.setVisibility(View.GONE);

        iv0.setImageBitmap(null);
        iv1.setImageBitmap(null);
        iv2.setImageBitmap(null);
        iv3.setImageBitmap(null);
        iv4.setImageBitmap(null);
        iv5.setImageBitmap(null);

        mybb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        //mybb.execSQL("drop table nav_images;");


        mybb.execSQL("create table if not exists nav_images (id int, path varchar, name varchar, status varchar, address varchar);");

        Cursor c = mybb.rawQuery("select * from nav_images where status = 'active'", null);
        if (c.moveToFirst()) {
            int i = 0;
            int j = 0;
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndex("id"));
                final String path = c.getString(c.getColumnIndex("path"));
                String name = c.getString(c.getColumnIndex("name"));
                String status = c.getString(c.getColumnIndex("status"));
                String address = c.getString(c.getColumnIndex("address"));

                String logstr = "row is : " +id + ", " +  path + " , " + name + " , " + status + ", " +  address;
                System.out.println(logstr);

                if (path.contains("jpg") || path.contains("png") || path.contains("tif") || path.contains("jpeg") || path.contains("gif")) {
                    if (i == 0) {
                        iv0.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete0.setVisibility(View.VISIBLE);
                        // et0.setText(address);
                    } else if (i == 1) {
                        iv1.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete1.setVisibility(View.VISIBLE);
                        //et1.setText(address);
                    } else if (i == 2) {
                        iv2.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete2.setVisibility(View.VISIBLE);
                        //et2.setText(address);
                    } else if (i == 3) {
                        iv3.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete3.setVisibility(View.VISIBLE);
                        //et3.setText(address);
                    } else if (i == 4) {
                        iv4.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete4.setVisibility(View.VISIBLE);
                        //et4.setText(address);
                    } else if (i == 5) {
                        iv5.setImageBitmap(BitmapFactory.decodeFile(path));
                        delete5.setVisibility(View.VISIBLE);
                        // et5.setText(address);
                    }
                    i++;
                }

                if (id == 0)  et0.setHint(address);
                if (id == 1)  et1.setHint(address);
                if (id == 2)  et2.setHint(address);
                if (id == 3)  et3.setHint(address);
                if (id == 4)  et4.setHint(address);
                if (id == 5)  et5.setHint(address);


                c.moveToNext();
            }
        }

        int tCount = c.getCount();

        if (tCount == 0){
            add0.setVisibility(View.VISIBLE);
        }else if (tCount == 1){
            add1.setVisibility(View.VISIBLE);
        }else if (tCount == 2){
            add2.setVisibility(View.VISIBLE);
        }else if (tCount == 3){
            add3.setVisibility(View.VISIBLE);
        }else if (tCount == 4){
            add4.setVisibility(View.VISIBLE);
        }else if (tCount == 5){
            add5.setVisibility(View.VISIBLE);
        }
    }
}

