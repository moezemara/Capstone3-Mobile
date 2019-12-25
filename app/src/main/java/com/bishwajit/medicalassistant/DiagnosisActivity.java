package com.bishwajit.medicalassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

public class DiagnosisActivity extends AppCompatActivity {

    ImageButton boyButton, girlButton;
    EditText age;
    int g =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_and_proceed();
            }
        });

        // getting the instances of the gui elements
        boyButton = (ImageButton)findViewById(R.id.boyButton);
        girlButton = (ImageButton)findViewById(R.id.girlButton);
        age = (EditText)findViewById(R.id.age);

        boyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyButton.setEnabled(false);
                girlButton.setEnabled(true);
                boyButton.setElevation(35);
                boyButton.buildLayer();
                g = 1;

            }
        });
        girlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girlButton.setEnabled(false);
                boyButton.setEnabled(true);
                girlButton.setElevation(35);
                g = 2;
            }
        });

    }

    private void verify_and_proceed()
    {
        if(g == 0)
        {
            Toast.makeText(this, "Please let me know your gender.", Toast.LENGTH_LONG).show();
            return;
        }

        String sex ="";
        if(g == 1)
            sex = "male";
        else if(g == 2)
            sex = "female";

        int years;
        try {
            years = Integer.parseInt(age.getText().toString());
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Enter Valid age.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(years>0 && years <120))
        {
            Toast.makeText(this, "Enter Valid age.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent symptom_choose = new Intent(this, SymptomsActivity.class);
        symptom_choose.putExtra("gender", sex );
        symptom_choose.putExtra("age", years);
        startActivity(symptom_choose);
    }

}
