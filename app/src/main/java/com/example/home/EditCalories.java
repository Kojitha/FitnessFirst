package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditCalories extends AppCompatActivity {

    //date picker
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText pcalorie, pfeelings, pnotes;
    private TextView pdate;

    private String calorie, date, feelings, notes, id;
    private boolean editMode = false;
    private com.example.home.CDatabaseHelper dbHelper;
    Button saveInfoBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calories);

        //get cols from xml
        pcalorie = findViewById(R.id.calEdit);
        pdate = findViewById(R.id.dateEdit);
        pfeelings = findViewById(R.id.feelingsEdit);
        pnotes = findViewById(R.id.notesEdit);


        //date
        mDisplayDate = (TextView) findViewById(R.id.dateEdit);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editCal", editMode);
        id = intent.getStringExtra("ID");
        calorie = intent.getStringExtra("CALORIE");
        date = intent.getStringExtra("DATE");
        feelings = intent.getStringExtra("FEELINGS");
        notes = intent.getStringExtra("NOTES");

        if (editMode){

            editMode = intent.getBooleanExtra("editCal", editMode);
            id = intent.getStringExtra("ID");
            calorie = intent.getStringExtra("CALORIE");
            date = intent.getStringExtra("DATE");
            feelings = intent.getStringExtra("FEELINGS");
            notes = intent.getStringExtra("NOTES");

            pcalorie.setText(calorie);
            pdate.setText(date);
            pfeelings.setText(feelings);
            pnotes.setText(notes);

        }
        else{

        }





        //date picker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditCalories.this,
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

        //databse object in mainfunction
        dbHelper = new CDatabaseHelper(this);
        //log details
        saveInfoBt = findViewById(R.id.button6);

        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                startActivity(new Intent(EditCalories.this, CaloriesActivity.class));
                Toast.makeText(EditCalories.this, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {

        calorie = ""+pcalorie.getText().toString().trim();
        date = ""+pdate.getText().toString().trim();
        feelings = ""+pfeelings.getText().toString().trim();
        notes = ""+pnotes.getText().toString().trim();

        if(editMode){

            dbHelper.updateInfo(
                    ""+id,
                    ""+calorie,
                    ""+date,
                    ""+feelings,
                    ""+notes
            );
        }
        else {

            dbHelper.insertInfo(
                    "" + calorie,
                    "" + date,
                    "" + feelings,
                    "" + notes
            );
        }

        //Toast.makeText(this, "Query Added:"+id, Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(EditCalories.this, MainActivity.class));
    }


    @Override
    public boolean onSupportNavigateUp(){ //navigate to add calorie page from this
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}