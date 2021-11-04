package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class EnlargedPhoto extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarged_photo);

        imageView = findViewById(R.id.enlarged_image);

        Intent i = getIntent();
        int pos = i.getExtras().getInt("id");

        //ShowPhotos imageAdapter = new ImageAdapter(this);

        //imageView.setImageResource(imageAdapter.);


    }
}
