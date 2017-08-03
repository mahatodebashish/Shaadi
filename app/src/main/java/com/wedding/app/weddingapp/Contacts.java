package com.wedding.app.weddingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Contacts extends AppCompatActivity implements OnClickListener {

    private int entries = 8;
    private String phoneNum[];
    private String buttonLabels[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_contacts);


        phoneNum = new String[entries];
        buttonLabels = new String[entries];

        // Populate the data arrays
        populateArrays();

        // Set up buttons and attach click listeners

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setText(buttonLabels[0]);
        button1.setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(buttonLabels[1]);
        button2.setOnClickListener(this);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setText(buttonLabels[2]);
        button3.setOnClickListener(this);

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setText(buttonLabels[3]);
        button4.setOnClickListener(this);

        Button button5 = (Button)findViewById(R.id.button5);
        button5.setText(buttonLabels[4]);
        button5.setOnClickListener(this);

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setText(buttonLabels[5]);
        button6.setOnClickListener(this);

        Button button7 = (Button)findViewById(R.id.button7);
        button7.setText(buttonLabels[6]);
        button7.setOnClickListener(this);

        Button button8 = (Button)findViewById(R.id.button8);
        button8.setText(buttonLabels[7]);
        button8.setOnClickListener(this);
    }

    // Launch the phone dialer

    public void launchDialer(String number){
        String numberToDial = "tel:"+number;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
    }


    /** Method to populate the data arrays */

    public void populateArrays(){

        /** In a practical application the arrays phoneNum and buttonLabels could be
         * updated dynamically from the Web in this method.  For this project we just
         * hard-wire in some values to illustrate how to use such data, once obtained,
         * to make phone calls. The names and numbers are made up.*/

        phoneNum[0] = "+919437970223";
        phoneNum[1] = "+919437452333";
        phoneNum[2] = "+917894431500";
        phoneNum[3] = "+917894412585";
        phoneNum[4] = "+918018669111";
        phoneNum[5] = "+919871203659";
        phoneNum[6] = "+917077703434";
        phoneNum[7] = "+917749988068";

        buttonLabels[0] = "TRAVEL DESK (Linku Babu)";
        buttonLabels[1] = "FUNCTION COORDINATER (Sawnrilal Agrawal)";
        buttonLabels[2] = "EVENT TIME (Nilesh Kheria)";
        buttonLabels[3] = "ROOM SERVICE (Sunil Babu)";
        buttonLabels[4] = "ROOM ALLOTMENT (Sanjib Babu)";
        buttonLabels[5] = "HOSPITALITY (Golu Babu)";
        buttonLabels[6] = "EVENT (Susant Babu)";
        buttonLabels[7] = "PHOTOGRAPHY (Supravat Babu)";
    }

    /** Process button events */

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        System.out.println("----main activity---onStart---");
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button1:
                launchDialer(phoneNum[0]);
                break;

            case R.id.button2:
                launchDialer(phoneNum[1]);
                break;

            case R.id.button3:
                launchDialer(phoneNum[2]);
                break;

            case R.id.button4:
                launchDialer(phoneNum[3]);
                break;

            case R.id.button5:
                launchDialer(phoneNum[4]);
                break;

            case R.id.button6:
                launchDialer(phoneNum[5]);
                break;
            case R.id.button7:
                launchDialer(phoneNum[6]);
                break;
            case R.id.button8:
                launchDialer(phoneNum[7]);
                break;
        }
    }

}
