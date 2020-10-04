package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class End_Workout extends AppCompatActivity {


    DBHelperStep myDB;

    EditText text_date,text_type,text_steps;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end__workout);

        myDB = new DBHelperStep(this);//Calling Constructor

        //Getting values
        text_steps= findViewById(R.id.insert_steps);
        text_type= findViewById(R.id.insert_type);
        text_date= findViewById(R.id.insert_date);

        button= findViewById(R.id.button_w1);


        AddData();

    }


    public void AddData() {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Inserting Got values using insertData Constructor
                        boolean isInserted = myDB.insertInfo(text_steps.getText().toString(),text_type.getText().toString(), text_date.getText().toString());
                        if (isInserted==true) {
                            Toast.makeText(End_Workout.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(End_Workout.this, ListStep.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(End_Workout.this, " Not Inserted", Toast.LENGTH_LONG).show();

                        }

                    }
                }
        );
    }


}