package com.example.tdumy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseCon myDB;
    EditText editwater, editwakeup, editgotup;
    Button btnAddData;
    Button btnViewAll;
    Button btnrr;

   // @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseCon(this);//Calling Constructor

        //createNotificationChannel();

        //Geting values
        editwater = (EditText) findViewById(R.id.water);
        editwakeup = (EditText) findViewById(R.id.wakeup);
        editgotup = (EditText) findViewById(R.id.gotobed);
        btnAddData = (Button) findViewById(R.id.button_w1);
        btnViewAll = (Button) findViewById(R.id.button_view);

        AddData();
        viewAll();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                view -> {
                    //Inserting Got values using insertData Constructor
                    boolean isInserted = myDB.insertData(editwater.getText().toString(), editwakeup.getText().toString(), editgotup.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Successfully Inserted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, History.class);
                        startActivity(intent);



                    } else {
                        Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }





    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor cs = myDB.getallData();
                        if (cs.getCount() == 0){
                            //Dislay msg (if data is not found)
                            showMessage("Error","No Data");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        //Get all the data one by one using while loop
                        while(cs.moveToNext()){
                            buffer.append("Water :"+cs.getString(0)+"\n");
                            buffer.append("Wakeup time :"+cs.getString(1)+"\n");
                            buffer.append("Bed time:"+cs.getString(2)+"\n\n");
                        }
                        //If Data is found
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}