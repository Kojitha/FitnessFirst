package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class musclegroupsloose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musclegroupsloose);
    }

    public void abs (View view){
        Intent intent = new Intent (this, abs.class);
        startActivity(intent);
    }

    public void looseback (View view){
        Intent intent = new Intent (this, looseback.class);
        startActivity(intent);
    }

    public void loosetriceps (View view){
        Intent intent = new Intent (this, loosetriceps.class);
        startActivity(intent);
    }

    public void loosechest (View view){
        Intent intent = new Intent (this, loosechest.class);
        startActivity(intent);
    }

    public void looseshoulders (View view){
        Intent intent = new Intent (this, looseshoulders.class);
        startActivity(intent);
    }

    public void looselegs (View view){
        Intent intent = new Intent (this, looselegs.class);
        startActivity(intent);
    }

    public void loosebiceps (View view){
        Intent intent = new Intent (this, loosebiceps.class);
        startActivity(intent);
    }



}