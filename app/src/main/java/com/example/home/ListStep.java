package com.example.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListStep extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    AdapterStep adapterStep = null;
    static com.example.home.DBHelperStep myDB;
    ArrayList<com.example.home.ModelStep> arrayList = new ArrayList<>();
    private AdapterView<?> parent;
    private View view;
    private int position;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_step);
        myDB = new com.example.home.DBHelperStep(this);//Calling Constructor
        listView = findViewById(R.id.lvContact);
        RetriveProductList();

        //listView.setAdapter(adapterStep);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                com.example.home.ModelStep selectmodel;
                //All data pass

                selectmodel= adapterStep.getItem(position);

                String steps = selectmodel.getSteps();
                String type = selectmodel.getType();
                String date = selectmodel.getDate();


                Intent intent=new Intent(ListStep.this, com.example.home.EditStep.class);


                intent.putExtra("steps",steps);
                intent.putExtra("type",type);
                intent.putExtra("date",date);


                startActivity(intent);
                finish();
                //Toast.makeText(ListStep.this, steps, Toast.LENGTH_LONG).show();



            }
        });


    }

    private void RetriveProductList() {
        myDB = new com.example.home.DBHelperStep(this);

        arrayList = myDB.getAllData();
        adapterStep = new AdapterStep(this, R.layout.activity_list_step, arrayList);
        listView.setAdapter(adapterStep);
    }


}