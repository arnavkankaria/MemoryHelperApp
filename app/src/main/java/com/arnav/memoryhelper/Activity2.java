package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    private CardView exButton, navButton, photoButton, infoButton, contactButton, noteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //defining cards
        exButton = (CardView) findViewById(R.id.ex_button);
        navButton = (CardView) findViewById(R.id.nav_button);
        photoButton = (CardView) findViewById(R.id.photo_button);
        infoButton = (CardView) findViewById(R.id.info_button);
        contactButton = (CardView) findViewById(R.id.contact_button);
        noteButton = (CardView) findViewById(R.id.note_button);

        //adding click listener to the cards
        exButton.setOnClickListener(this);
        navButton.setOnClickListener(this);
        photoButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        noteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()){
            case R.id.ex_button :
                Intent exWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://arnavkankaria.wixsite.com/alzheimerexercise"));
                startActivity(exWebsite);
                break;
            case R.id.nav_button :
                i = new Intent(this, nav.class);
                startActivity(i);
                break;
            case R.id.photo_button :
                i = new Intent(this, ShowPhoto.class);
                startActivity(i);
                break;
            case R.id.info_button :
                Intent infoWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://arnavkankaria.wixsite.com/aboutalzheimers"));
                startActivity(infoWebsite);
                break;
            case R.id.contact_button :
                i = new Intent(this, LoginPage.class);
                startActivity(i);
                break;
            case R.id.note_button :
                i = new Intent(this, NotesReminders.class);
                startActivity(i);
                break;
            default:break;
        }
    }
}
