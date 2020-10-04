package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //
    public void gotobmiin (View view){
        Intent intent = new Intent (this, Bmi_Inputss.class);
        startActivity(intent);
    }
    //

    public void gotowaterinput (View view){
        Intent intent = new Intent (this, MainWater.class);
        startActivity(intent);
    }

    public void gotocalorieactivity (View view){
        Intent intent = new Intent (this, CaloriesActivity.class);
        startActivity(intent);
    }

    public void gotostep (View view){
        Intent intent = new Intent (this, com.example.home.StepCounter.class);
        startActivity(intent);
    }



}