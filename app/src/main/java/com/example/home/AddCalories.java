package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddCalories extends AppCompatActivity {


    //date picker
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText pcalorie, pfeelings, pnotes;
    private TextView pdate;

    private String date, feelings, notes;
    private int calorie;
    private com.example.home.CDatabaseHelper dbHelper;
    Button saveInfoBt, loghistory;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calories);

        //getdata from xml
        pcalorie = findViewById(R.id.editTextCalories);
        pdate = findViewById(R.id.editTextDate);
        pfeelings = findViewById(R.id.spinner);
        pnotes = findViewById(R.id.editTextNotes);


        //date
        mDisplayDate = (TextView) findViewById(R.id.editTextDate);



        //date picker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddCalories.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //new line
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //new line



                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

            }
        };

        //database object in main function
        dbHelper = new CDatabaseHelper(this);
        //log details
        saveInfoBt = findViewById(R.id.button6);
        loghistory = findViewById(R.id.button7);

        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ServiceCast")
            @Override
            public void onClick(View view) {
                    if(getData()>2000) {
                        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        vibrator.vibrate(1000);
                        Toast.makeText(AddCalories.this, "You have exceeded your calorie goal", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        loghistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddCalories.this, CaloriesActivity.class));
            }
        });

    }
    //insert
    private int getData() {

        calorie = Integer.parseInt(""+pcalorie.getText().toString().trim());
        date = ""+pdate.getText().toString().trim();
        feelings = ""+pfeelings.getText().toString().trim();
        notes = ""+pnotes.getText().toString().trim();

        long id = dbHelper.insertInfo(
                ""+calorie,
                ""+date,
                ""+feelings,
                ""+notes
        );

        if(TextUtils.isEmpty(Integer.toString(calorie))){
            Toast.makeText(this,"Cannot Leave Calorie empty",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(date)){
            Toast.makeText(this,"Cannot Leave  Date Empty..",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(feelings)){
            Toast.makeText(this,"Cannot Leave  Feelings Empty..",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(notes)){
            Toast.makeText(this,"Cannot Leave  Notes Empty..",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data Inserted:" + id, Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(AddCalories.this, MainActivity.class));
        }
        return calorie;
    }


    @Override
    public boolean onSupportNavigateUp(){ //navigate to addcalorie page from this
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}