package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMI_Calculator extends AppCompatActivity {

    EditText weightt,heightt;
    TextView resulttext;
    String calculation,BMIresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i__calculator);

        weightt = findViewById(R.id.weightt);
        heightt = findViewById(R.id.heightt);
        resulttext = findViewById(R.id.result);

    }

    public void calculateBMI(View view) {

        String S1 = weightt.getText().toString();
        String S2 = heightt.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;

        float bmi = weightValue / (heightValue * heightValue);

        if(bmi < 16){
            BMIresult = "Severly Under Weight";
        }else if(bmi <18.5) {
            BMIresult = "Under Weight";
        }else if(bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal Weight";
        }else if(bmi >= 25 && bmi <= 29.9 ) {
            BMIresult = "Over Weight";
        }else {
            BMIresult = "Obese";
        }

        calculation = "Result: \n\n" + bmi + "\n" + BMIresult;
        resulttext.setText(calculation);

    }


    //
    public void gotomusclegroups (View view){
        Intent intent = new Intent (this, MUSCLEGROUPSS.class);
        startActivity(intent);
    }
    //

    //
    public void gotomusclegroupsloose (View view){
        Intent intent = new Intent (this, musclegroupsloose.class);
        startActivity(intent);
    }
    //



}