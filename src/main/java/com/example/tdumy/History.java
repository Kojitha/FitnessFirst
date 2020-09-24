package com.example.tdumy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    Adapter adapter = null;
    DatabaseCon myDB;
    ArrayList<Model> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);
        myDB = new DatabaseCon(this);//Calling Constructor
        listView = findViewById(R.id.lvContact);
        RetriveProductList();
    }

    private void RetriveProductList() {
        myDB = new DatabaseCon(this);

        arrayList = myDB.getAllData();
        adapter = new Adapter(this, R.layout.water_history, arrayList);
        listView.setAdapter(adapter);
    }


}