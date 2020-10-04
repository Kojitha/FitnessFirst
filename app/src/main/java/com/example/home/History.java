package com.example.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    AdapterWater adapterWater = null;
    static DatabaseCon myDB;
    ArrayList<ModelWater> arrayList = new ArrayList<>();
    private AdapterView<?> parent;
    private View view;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);
        myDB = new DatabaseCon(this);//Calling Constructor
        listView = findViewById(R.id.lvContact);
        RetriveProductList();

        //listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ModelWater selectmodel;
                //All data pass
                selectmodel = adapterWater.getItem(position);

                String water = selectmodel.getWater();
                String wakeup = selectmodel.getWakeup();
                String gotobed = selectmodel.getGotup();

                Intent intent = new Intent(History.this, Edit.class);
                intent.putExtra("water",water);
                intent.putExtra("wakeup",wakeup);
                intent.putExtra("gotobed",gotobed);

                startActivity(intent);
                finish();
                Toast.makeText(History.this, wakeup, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void RetriveProductList() {
        myDB = new DatabaseCon(this);

        arrayList = myDB.getAllData();
        adapterWater = new AdapterWater(this, R.layout.water_history, arrayList);
        listView.setAdapter(adapterWater);
    }


}