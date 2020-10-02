package com.example.tdumy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseCon myDB;
    EditText editwater, editwakeup, editgotup;
    Button btnAddData;
    Button  btnViewData;
    ProgressDialog loadingBar;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseCon(this);//Calling Constructor

        createNotificationChannel();

        //Geting values
        //editwater = (EditText) findViewById(R.id.water);
        //editwakeup = (EditText) findViewById(R.id.wakeup);
        //editgotup = (EditText) findViewById(R.id.gotobed);
        btnAddData = (Button) findViewById(R.id.button_w1);
        btnViewData = (Button) findViewById(R.id.button_w2);
        loadingBar = new ProgressDialog(this);

        AddData();
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, History.class);
                startActivity(intent2);
            }
        });

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                view -> {
                    editwater = (EditText) findViewById(R.id.water);
                    editwakeup = (EditText) findViewById(R.id.wakeup);
                    editgotup = (EditText) findViewById(R.id.gotobed);

                    String editwater1 = editwater.getText().toString();
                    String editwakeup1 = editwakeup.getText().toString();
                    String editgotup1 = editgotup.getText().toString();

                    if(TextUtils.isEmpty(editwater1)){
                        Toast.makeText(this, "Please enter your water consumption",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(editwakeup1)){
                        Toast.makeText(this, "Please enter your wakeup time",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(editgotup1)){
                        Toast.makeText(this, "Please enter your wakeup time",Toast.LENGTH_SHORT).show();
                    }
                    else{


                        //Inserting Got values using insertData Constructor
                        boolean isInserted = myDB.insertData(editwater.getText().toString(), editwakeup.getText().toString(), editgotup.getText().toString());
                        if (isInserted == true) {
                            //Dialog Box
                            loadingBar.setTitle("Successfully Inserted");
                            loadingBar.setMessage("Please wait");
                            loadingBar.setCanceledOnTouchOutside(true);
                            loadingBar.show();
                            Intent intent = new Intent(MainActivity.this, History.class);
                            startActivity(intent);
                            loadingBar.dismiss();

                            Intent intent1 = new Intent(MainActivity.this,ReminderToUser.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent1,0);

                            AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

                            long timeAtButtonClick = System.currentTimeMillis();
                            long oneseconds = 1000 * 1;
                            alarmManager.set(AlarmManager.RTC_WAKEUP,
                                    timeAtButtonClick + oneseconds,
                                    pendingIntent);


                        } else {
                            Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
                        }

                    }


                }
        );
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }






}