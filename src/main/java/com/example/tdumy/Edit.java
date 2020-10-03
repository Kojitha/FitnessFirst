package com.example.tdumy;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Edit extends AppCompatActivity {

    EditText waterEdit;
    TextView wakeupEdit,gotobedEdit;
    int t3Hour,t3Minute,t4Hour,t4Minute;
    //Databse Row id
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String water = getIntent().getExtras().getString("water");
        String wakeup = getIntent().getExtras().getString("wakeup");
        String gotobed = getIntent().getExtras().getString("gotobed");

        waterEdit =  findViewById(R.id.waterE);
        wakeupEdit = findViewById(R.id.wakeupE);
        gotobedEdit = findViewById(R.id.gotobedE);

        wakeupEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Edit.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                //Initialize hour and minute
                                t3Hour = hourofDay;
                                t3Minute = minute;
                                //Initialize calender
                                Calendar calendar = Calendar.getInstance();
                                //Initialize hour and minute
                                calendar.set(0,0,0,t3Hour,t3Minute);
                                //set selected time on text view
                                wakeupEdit.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Display prious selected time
                timePickerDialog.updateTime(t3Hour,t3Minute);
                //show Dialog
                timePickerDialog.show();
            }
        });

        gotobedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Edit.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                //Initialize hour and minute
                                t4Hour = hourofDay;
                                t4Minute = minute;
                                //Initialize calender
                                Calendar calendar = Calendar.getInstance();
                                //Initialize hour and minute
                                calendar.set(0,0,0,t4Hour,t4Minute);
                                //set selected time on text view
                                gotobedEdit.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Display prious selected time
                timePickerDialog.updateTime(t4Hour,t4Minute);
                //show Dialog
                timePickerDialog.show();
            }
        });

        //Set values
        waterEdit.setText(water);
        wakeupEdit.setText(wakeup);
        gotobedEdit.setText(gotobed);
    }
    //Calling OnClick Method
    public void edit(View view){
        //Recevie Edit Text Content
        String water = waterEdit.getText().toString();
        String wakeup = wakeupEdit.getText().toString();
        String gotobed = gotobedEdit.getText().toString();

        History.myDB.updateInfo(water,wakeup,gotobed);
        startActivity(new Intent(Edit.this, History.class));
        //Toast.makeText(Edit.this,"Show",Toast.LENGTH_LONG).show();
        finish();
    }

    public void deleteInfo(View view) {
        String water = waterEdit.getText().toString();

        History.myDB.deleteData(water);
        startActivity(new Intent(Edit.this, History.class));
        finish();
    }
}
