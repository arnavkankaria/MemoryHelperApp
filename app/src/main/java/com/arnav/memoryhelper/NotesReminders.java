package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NotesReminders extends AppCompatActivity {

    String formattedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_reminders);

        Calendar cal = Calendar.getInstance();
        System.out.println("Current time => "+cal.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(cal.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        EditText txt_df = findViewById(R.id.df);
        Switch morning = findViewById(R.id.sun_switch);
        Switch afternoon = findViewById(R.id.afternoon_switch);
        Switch night = findViewById(R.id.night_switch);


        txt_df.setText(formattedDate);
        final SQLiteDatabase mydb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mydb.execSQL("create table if not exists MedicalAlert (dt varchar, morning varchar, afternoon varchar, night varchar);");
        Cursor cursor = mydb.rawQuery("SELECT count(*) FROM MedicalAlert WHERE dt = '" + formattedDate + "';", null );
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            String sqlstmt = "insert into MedicalAlert values ('" + formattedDate + "','','','');";
            mydb.execSQL(sqlstmt);
        } else {
            String sqlstmt = "select * from MedicalAlert where dt = '" + formattedDate + "';";
            cursor = mydb.rawQuery(sqlstmt, null );
            cursor.moveToFirst();

            String str_morning = cursor.getString(cursor.getColumnIndex("morning"));
            String str_afternoon = cursor.getString(cursor.getColumnIndex("afternoon"));
            String str_night = cursor.getString(cursor.getColumnIndex("night"));

            if (str_morning.isEmpty())    morning.setChecked(false);
            else morning.setChecked(true);

            if (str_afternoon.isEmpty())    afternoon.setChecked(false);
            else afternoon.setChecked(true);

            if (str_night.isEmpty())    night.setChecked(false);
            else night.setChecked(true);

            cursor.close();
        }

        // Set a checked change listener for switch button
        morning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String sqlstmt = "update MedicalAlert set morning = 'morning' where dt = '"+ formattedDate+ "';";
                    mydb.execSQL(sqlstmt);
                    // Show the switch button checked status as toast message
                    Toast.makeText(getApplicationContext(),
                            "Morning Switch is on", Toast.LENGTH_LONG).show();

                    Calendar mycalendar  = Calendar.getInstance();
                    mycalendar.set(Calendar.HOUR_OF_DAY, 10);
                    mycalendar.set(Calendar.MINUTE, 0);
                    mycalendar.set(Calendar.SECOND, 0);

                    Intent notifyIntent = new Intent(NotesReminders.this,MyReceiver.class);
                    notifyIntent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast
                            (NotesReminders.this, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) NotesReminders.this.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  mycalendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        });

        afternoon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String sqlstmt = "update MedicalAlert set afternoon = 'afternoon'  where dt = '"+ formattedDate+ "';";
                    mydb.execSQL(sqlstmt);

                    // Show the switch button checked status as toast message
                    Toast.makeText(getApplicationContext(),
                            "Afternoon Switch is on", Toast.LENGTH_LONG).show();

                    Calendar mycalendar  = Calendar.getInstance();
                    mycalendar.set(Calendar.HOUR_OF_DAY, 14);
                    mycalendar.set(Calendar.MINUTE, 0);
                    mycalendar.set(Calendar.SECOND, 0);

                    Intent notifyIntent = new Intent(NotesReminders.this,MyReceiver.class);
                    notifyIntent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast
                            (NotesReminders.this, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) NotesReminders.this.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  mycalendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        });

        night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String sqlstmt = "update MedicalAlert set night = 'night'  where dt = '"+ formattedDate+ "';";
                    mydb.execSQL(sqlstmt);
                    // Show the switch button checked status as toast message
                    Toast.makeText(getApplicationContext(),
                            "Night Switch is on", Toast.LENGTH_LONG).show();

                    Calendar mycalendar  = Calendar.getInstance();
                    mycalendar.set(Calendar.HOUR_OF_DAY, 21);
                    mycalendar.set(Calendar.MINUTE, 0);
                    mycalendar.set(Calendar.SECOND, 0);

                    Intent notifyIntent = new Intent(NotesReminders.this,MyReceiver.class);
                    notifyIntent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast
                            (NotesReminders.this, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) NotesReminders.this.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  mycalendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        });


/*
        final SQLiteDatabase mydb = openOrCreateDatabase("memoryhelper", MODE_PRIVATE, null);
        mydb.execSQL("create table if not exists Reminders (dt varchar, morning varchar, afternoon varchar, night varchar);");

        Cursor c = mydb.rawQuery("select * Reminders where dt = '" + formattedDate + "'",null);
        String m1="",n1="",a1="";
        boolean record_exist = false;
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                record_exist = true;
                 m1 = c.getString(c.getColumnIndex("morning"));
                 a1 = c.getString(c.getColumnIndex("afternoon"));
                 n1 = c.getString(c.getColumnIndex("night"));

                Toast.makeText(this,  m1+"_"+a1+"_"+n1, Toast.LENGTH_LONG).show();
                break;

               // c.moveToNext();
            }
        }

        final boolean bool_record_exists = record_exist;
        */


        /*

        morning.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                if (bool_record_exists) {
                    mydb.rawQuery("update Reminders set morning = taken where dt = '"+ formattedDate + "'", null );
                } else {
                    mydb.rawQuery("insert into Reminders values ('taken', '','')", null );
                }
           }
       });

        afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bool_record_exists) {
                    mydb.rawQuery("update Reminders set afternoon = taken where dt = '"+ formattedDate + "'", null );
                } else {
                    mydb.rawQuery("insert into Reminders values ('', 'taken','')", null );
                }
            }
        });


        */

        /*
        morning.setChecked( (m1 != "") );
        afternoon.setChecked( (a1 != "") );
        night.setChecked( (n1 != "") );


        mydb.execSQL("select * Reminders where dt = " + formattedDate);

        Boolean m = morning.isChecked();
        Boolean a = afternoon.isChecked();
        Boolean n = night.isChecked();

        if (m == true) {
            Toast.makeText(this,"Well done! Take medicines for the rest of the day as well", Toast.LENGTH_LONG).show();
        }
        if (a == true) {
            Toast.makeText(this,"Well done! Take medicines for the rest of the day as well", Toast.LENGTH_LONG).show();
        }
        if (n == true) {
            Toast.makeText(this,"Well done! Take medicines tomorrow as well", Toast.LENGTH_LONG).show();
        }
        */


       // c.close();
    }
}
