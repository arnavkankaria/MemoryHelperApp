package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowPhotos extends AppCompatActivity {
    GridView gridView;
    GridView gridVideo;
    SQLiteDatabase mydb;
    public String al[] = new String[8];
    public String alVid[] = new String[8];
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mediaController = new MediaController(this);

        mydb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mydb.execSQL("create table if not exists images (path varchar, name varchar, status varchar);");

        Cursor c = mydb.rawQuery("select * from images where status = 'active'", null);
        if (c.moveToFirst()) {
            int i = 0;
            int j = 0;
            while (!c.isAfterLast()) {
                final String path = c.getString(c.getColumnIndex("path"));
                String name = c.getString(c.getColumnIndex("name"));
                String status = c.getString(c.getColumnIndex("status"));

                System.out.println("row is : " + path + " , " + name + " , " + status);

                if (path.contains("jpg") || path.contains("png") || path.contains("tif") || path.contains("jpeg") || path.contains("gif")) {
                    al[i] = path;
                    i++;
                } else{
                    alVid[j] = path;
                    j++;
                }
                c.moveToNext();
            }
        }
        // image grid view
        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));

        //video grid view
        gridVideo = (GridView) findViewById(R.id.grid_view_video);
        gridVideo.setAdapter(new VideoAdapter(this));

        //gridVideo.setAdapter(new VideoAdapter(this));


        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent ep = new Intent(getApplicationContext(), EnlargedPhoto.class);
                ep.putExtra("id", position);
                startActivity(ep);

            }
        });*/
    }

    public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return al.length;
        }

        @Override
        public Object getItem(int position) {
            return al[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = new ImageView(mContext);
            iv.setImageBitmap(BitmapFactory.decodeFile(al[position]));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new GridView.LayoutParams(300, 300));
            iv.setPadding(8, 8, 8, 8);

            return iv;
        }
    }

    public class VideoAdapter extends BaseAdapter{
        private Context mContext;

        public void display_video (VideoView vv, String path, int flag) {

            /*
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, 0); //Creation of Thumbnail of video
                vv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                vv.setPadding(8, 8, 8, 8);
                vv.setLayoutParams(new GridView.LayoutParams(100, 100));
*/

            vv.setVideoPath(path);
            //vv.setPadding(0, 0, 0, 0);
            //vv.setHorizontalScrollBarEnabled(true);
            vv.setScaleX(300);
            vv.setScaleY(300);
            vv.setLayoutParams(new GridView.LayoutParams(300, 300));
            vv.setMediaController(mediaController);
            mediaController.setAnchorView(vv);
            mediaController.setPadding(0, 0, 0, 0);

            vv.seekTo(100);
            vv.start();
            if (flag != 1) {
                try {
                    this.wait(1000);
                } catch (Exception e) {}
                vv.seekTo(1000);
                vv.pause();
            }
            //   mediaController.show();

        }

        public void display_video (VideoView vv, String path) {
            display_video (vv, path, 0);
        }

        public VideoAdapter(Context mContext) { this.mContext = mContext; }

        @Override
        public int getCount() {
            return alVid.length;
        }

        @Override
        public Object getItem(int position) {
            return alVid[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            VideoView vv = new VideoView(mContext);
            display_video (vv, alVid[position]);

            //vv.setScaleType(ImageView.ScaleType.FIT_XY);
            //iv.setLayoutParams(new GridView.LayoutParams(300, 300));
            //iv.setPadding(8, 8, 8, 8);
            vv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*ViewGroup.LayoutParams params = vv.getLayoutParams();
                    params.height = 3000;
                    params.width = 1000;
                    v.setLayoutParams(params);
                     this.setMediaController(mediaController);
                    mediaController.setAnchorView(vv);
                    mediaController.setPadding(0, 0, 0, 0);
                    mediaController.show();
                    v.start();*/
                }

            });

            return vv;

        }
    }
}