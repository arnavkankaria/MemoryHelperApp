package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.net.URI;

public class ShowPhoto extends AppCompatActivity {

    SQLiteDatabase mydb;
    ImageView iv0, iv1, iv2, iv3, iv4, iv5;
    VideoView vv0, vv1, vv2, vv3, vv4, vv5,vv7;
    RelativeLayout rv0,rv1,rv2,rv3,rv4,rv5,rv7;
    TextView del0,del1,del2,del3,del4,del5;
    TextView vDel0,vDel1,vDel2,vDel3,vDel4,vDel5;
    String id0,id1,id2,id3,id4,id5;
    String vid0,vid1,vid2,vid3,vid4,vid5;
    TextView lb;
    MediaController mediaController;
    boolean imgScreenFit;
    private String fullScreenInd;


    void display_video (VideoView vv, String path, int flag) {
        vv.getLayoutParams().width = 400;
        vv.getLayoutParams().height = 200;
        vv.setVideoPath(path);
        vv.start();
        if (flag != 1) {
            try {
                this.wait(1000);
            } catch (Exception e) {}
            vv.pause();
            vv.seekTo(1000);
        }
    }

    void display_video (VideoView vv, String path) {
        display_video (vv, path, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        iv0 = findViewById(R.id.img_0);
        iv1 = findViewById(R.id.img_1);
        iv2 = findViewById(R.id.img_2);
        iv3 = findViewById(R.id.img_3);
        iv4 = findViewById(R.id.img_4);
        iv5 = findViewById(R.id.img_5);

        del0 = findViewById(R.id.delete_1);
        del1 = findViewById(R.id.delete_2);
        del2 = findViewById(R.id.delete_3);
        del3 = findViewById(R.id.delete_4);
        del4 = findViewById(R.id.delete_5);
        del5 = findViewById(R.id.delete_6);

        vDel0 = findViewById(R.id.v_delete_1);
        vDel1 = findViewById(R.id.v_delete_2);
        vDel2 = findViewById(R.id.v_delete_3);
        vDel3 = findViewById(R.id.v_delete_4);
        vDel4 = findViewById(R.id.v_delete_5);
        vDel5 = findViewById(R.id.v_delete_6);

        refreshUI();

        del0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id0+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id1+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        del2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id2+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        del3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id3+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        del4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id4+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        del5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+id5+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid0+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid1+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid2+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid3+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid4+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });

        vDel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delstmt = "delete from images where id = '"+vid5+"' AND status = 'active';";
                mydb.execSQL(delstmt);

                refreshUI();
            }
        });
    }

    private void refreshUI(){

        vv0 = findViewById(R.id.vid_1);
        vv1 = findViewById(R.id.vid_2);
        vv2 = findViewById(R.id.vid_3);
        vv3 = findViewById(R.id.vid_4);
        vv4 = findViewById(R.id.vid_5);
        vv5 = findViewById(R.id.vid_6);

        del0.setVisibility(View.GONE);
        del1.setVisibility(View.GONE);
        del2.setVisibility(View.GONE);
        del3.setVisibility(View.GONE);
        del4.setVisibility(View.GONE);
        del5.setVisibility(View.GONE);

        vDel0.setVisibility(View.GONE);
        vDel1.setVisibility(View.GONE);
        vDel2.setVisibility(View.GONE);
        vDel3.setVisibility(View.GONE);
        vDel4.setVisibility(View.GONE);
        vDel5.setVisibility(View.GONE);

        iv0.setImageBitmap(null);
        iv1.setImageBitmap(null);
        iv2.setImageBitmap(null);
        iv3.setImageBitmap(null);
        iv4.setImageBitmap(null);
        iv5.setImageBitmap(null);

        mediaController = new MediaController(this);

        mydb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mydb.execSQL("create table if not exists images (id int, path varchar, name varchar, status varchar);");

        Cursor c = mydb.rawQuery("select * from images where status = 'active'", null);

        if (c.moveToFirst()) {
            int i = 0;
            int j = 0;
            while (!c.isAfterLast()) {
                final String path = c.getString(c.getColumnIndex("path"));
                final int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String status = c.getString(c.getColumnIndex("status"));

                System.out.println("row is : " + path + " , " + name + " , " + status+ " , " + id);

                if (path.contains("jpg") || path.contains("png") || path.contains("tif") || path.contains("jpeg") || path.contains("gif")) {
                    if (i == 0) {
                        iv0.setImageBitmap(BitmapFactory.decodeFile(path));
                        del0.setVisibility(View.VISIBLE);
                        id0 = String.valueOf(id);
                    } else if (i == 1) {
                        iv1.setImageBitmap(BitmapFactory.decodeFile(path));
                        del1.setVisibility(View.VISIBLE);
                        id1 = String.valueOf(id);
                    } else if (i == 2) {
                        iv2.setImageBitmap(BitmapFactory.decodeFile(path));
                        del2.setVisibility(View.VISIBLE);
                        id2 = String.valueOf(id);
                    } else if (i == 3) {
                        iv3.setImageBitmap(BitmapFactory.decodeFile(path));
                        del3.setVisibility(View.VISIBLE);
                        id3 = String.valueOf(id);
                    } else if (i == 4) {
                        iv4.setImageBitmap(BitmapFactory.decodeFile(path));
                        del4.setVisibility(View.VISIBLE);
                        id4 = String.valueOf(id);
                    } else if (i == 5) {
                        iv5.setImageBitmap(BitmapFactory.decodeFile(path));
                        del5.setVisibility(View.VISIBLE);
                        id5 = String.valueOf(id);
                    }
                    i++;
                } else { // video
                    if (j == 0) {
                        // rv7 = findViewById(R.id.rel_vid_7);
                        // vv7 = findViewById(R.id.vid_7);

                        rv0 = findViewById(R.id.rel_vid_1);
                        display_video (vv0,path);
                        vDel0.setVisibility(View.VISIBLE);
                        vid0 = String.valueOf(id);
                        vv0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = rv0.getLayoutParams();
                                params.height = 600;
                                params.width = 600;
                                vv0.setLayoutParams(params);
                                vv0.setMediaController(mediaController);
                                mediaController.setAnchorView(rv0);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                //display_video (vv7,path);
                                vv0.start();
                            }

                        });
                    } else if (j == 1) {
                        //  lb = findViewById(R.id.label_images);
                        //  rv0 = findViewById(R.id.rel_vid_1);
                        rv0 = findViewById(R.id.rel_vid_1);
                        rv1 = findViewById(R.id.rel_vid_2);
                        display_video (vv1,path);
                        vDel1.setVisibility(View.VISIBLE);
                        vid1 = String.valueOf(id);
                        vv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = rv1.getLayoutParams();
                                params.height = 600;
                                params.width = 600;
                                vv1.setLayoutParams(params);
                                vv1.setMediaController(mediaController);
                                mediaController.setAnchorView(rv0);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                vv1.start();
                            }
                        });
                    } else if (j == 2) {
                        rv0 = findViewById(R.id.rel_vid_1);
                        rv2 = findViewById(R.id.rel_vid_3);
                        display_video (vv2,path);
                        vDel2.setVisibility(View.VISIBLE);
                        vid2 = String.valueOf(id);
                        vv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = rv2.getLayoutParams();
                                params.height = 600;
                                params.width = 600;
                                vv2.setLayoutParams(params);
                                vv2.setMediaController(mediaController);
                                mediaController.setAnchorView(rv2);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                vv2.start();
                            }
                        });
                    } else if (j == 3) {
                        rv0 = findViewById(R.id.rel_vid_1);
                        rv3 = findViewById(R.id.rel_vid_4);
                        display_video (vv3,path);
                        vDel3.setVisibility(View.VISIBLE);
                        vid3 = String.valueOf(id);
                        vv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = rv3.getLayoutParams();
                                params.height = 600;
                                params.width = 600;
                                vv3.setLayoutParams(params);
                                vv3.setMediaController(mediaController);
                                mediaController.setAnchorView(vv3);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                vv3.start();
                            }
                        });
                    } else if (j == 4) {
                        rv0 = findViewById(R.id.rel_vid_1);
                        rv4 = findViewById(R.id.rel_vid_5);
                        display_video (vv4,path);
                        vDel4.setVisibility(View.VISIBLE);
                        vid4 = String.valueOf(id);
                        vv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = rv4.getLayoutParams();
                                params.height = 600;
                                params.width = 600;
                                vv4.setLayoutParams(params);
                                vv4.setMediaController(mediaController);
                                mediaController.setAnchorView(rv4);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                vv4.start();
                            }
                        });
                    } else if (j == 5) {
                        vDel5.setVisibility(View.VISIBLE);
                        vid5 = String.valueOf(id);
                        vv5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewGroup.LayoutParams params = vv5.getLayoutParams();
                                params.height = 3000;
                                params.width = 1000;
                                vv5.setLayoutParams(params);
                                vv5.setMediaController(mediaController);
                                mediaController.setAnchorView(vv5);
                                mediaController.setPadding(0, 0, 0, 0);
                                mediaController.show();
                                vv5.start();
                            }
                        });                        /*
                        display_video (vv5,path);
                        vv5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                display_video((VideoView)v, path, 1);
                            }
                        });

                         */
                    }
                    j++;

                }
                c.moveToNext();
            }
        }

    }
}
