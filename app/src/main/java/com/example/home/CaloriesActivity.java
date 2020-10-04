package com.example.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CaloriesActivity extends AppCompatActivity {

    FloatingActionButton fab1, fab2;
    RecyclerView mRecyclerView;
    com.example.home.CDatabaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloriesactivity);


        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new CDatabaseHelper(this);

        showRecord();

        fab1 = findViewById(R.id.addButton);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, AddCalories.class));

                Intent intent = new Intent(CaloriesActivity.this, AddCalories.class);
                intent.putExtra("editCal", false);
                startActivity(intent);



            }
        });

        fab2 = findViewById(R.id.addButton1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, AddCalories.class));

                Intent intent = new Intent(CaloriesActivity.this, SearchCalories.class);
                startActivity(intent);



            }
        });




    }

    private void showRecord() {
        CalorieAdapter adapter = new CalorieAdapter( CaloriesActivity.this, databaseHelper.getAllData(CalorieConstants.ID + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        showRecord();
    }
}