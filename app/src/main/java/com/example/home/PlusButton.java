package com.example.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlusButton extends AppCompatActivity {

   FloatingActionButton fab;
   ActionBar actionBar;
   RecyclerView mRecyclerView;
   DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_button);

        actionBar = getSupportActionBar();
        actionBar.setTitle("All Information");

        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);


        showRecord();

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener((v) -> {

            Intent intent = new Intent(PlusButton.this, Bmi_Inputss.class);
            intent.putExtra("editMode", false);
            startActivity(intent);

        });
    }

    //
    public void calculateBMI (View view){
        Intent intent = new Intent (this, BMI_Calculator.class);
        startActivity(intent);
    }
    //


    private void showRecord() {

        Adapter adapter = new Adapter(PlusButton.this, databaseHelper.getAllData("DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
            return super.onKeyUp(keyCode, event);
    }

}