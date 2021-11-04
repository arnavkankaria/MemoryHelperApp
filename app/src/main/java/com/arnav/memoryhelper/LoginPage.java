package com.arnav.memoryhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginPage extends AppCompatActivity {

    Button sub;
    EditText aone, atwo;// athree, afour;
    String sone, stwo;// sthree, sfour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        aone = (EditText) findViewById(R.id.answer_one);
        atwo = (EditText) findViewById(R.id.answer_two);
        //athree = (EditText) findViewById(R.id.answer_three);
        //afour = (EditText) findViewById(R.id.answer_four);
        sub = (Button) findViewById(R.id.submit);

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sone = aone.getText().toString();
                stwo = atwo.getText().toString();
                //sthree = athree.getText().toString();
                //sfour = afour.getText().toString();

                if (sone.equals("73")  && stwo.equals("13")
                       // && sthree.equals("12") && sfour.equals("84")
                ) {

                    openActivityContacts();

                } else {
                    aone.setText("");
                    atwo.setText("");
                    //athree.setText("");
                    //afour.setText("");

                    //Toast.makeText(this, "Incorrect please retry",Toast.LENGTH_SHORT);

                }

            }
        });
    }
    public void openActivityContacts(){
        Intent z = new Intent(this, Contacts.class);
        startActivity(z);
    }

}
