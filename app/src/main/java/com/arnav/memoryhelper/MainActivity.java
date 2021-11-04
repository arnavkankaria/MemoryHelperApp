package com.arnav.memoryhelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Variables for transition
private TextView tv;
private ImageView iv;

private Button button;
private Button buttonhelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAnalytics.getInstance(this);
        FirebaseCrashlytics.getInstance();
        loadLocale();
        setContentView(R.layout.activity_main);

        //ImageView logo = findViewById(R.id.iv);
        //logo.setColorFilter(Color.);

        showPermissionDialog();

        buttonhelp = (Button) findViewById(R.id.Help);
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMaplost();
            }
        });

        //change action action bar title
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle(getResources().getString(R.string.app_name));


        Button changeLang = findViewById(R.id.changeMyLang );
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shows list of languages
                //throw new RuntimeException("Test Crash");
                showChangeLanguageDialog();

            }
        });

        RelativeLayout relativeLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });



        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        /*final Intent i = new Intent(this, Activity2.class);
        Thread timer = new Thread(){
            public void run (){
                try{
                    sleep(15000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
                timer.start();*/

    }


    private void showChangeLanguageDialog() {
        //alert dialog

        final String[] listItems = {"French", "हिन्दी", "मराठी", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int z) {
                if (z==0){
                    //french
                    setLocale("fr");
                    recreate();
                }
                else if (z==1){
                    //hindi
                    setLocale("hi");
                    recreate();
                }
                else if (z==2){
                    //marathi
                    setLocale("mr");
                    recreate();
                }
                else if (z==3){
                    //english
                    setLocale("en");
                    recreate();
                }

                //alert dialog closes
                dialogInterface .dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        //show alert dialog
        mDialog.show();

    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("setting", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();


    }

    //load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);


    }

    public void openActivityMaplost(){
        Intent z = new Intent(this, maplost.class);
        startActivity(z);
    }

    public void showPermissionDialog() {

        int secondsDelayed = 1;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                boolean result = Utility.checkAndRequestPermissions(MainActivity.this);
            }
        }, secondsDelayed * 100);
    }
}
