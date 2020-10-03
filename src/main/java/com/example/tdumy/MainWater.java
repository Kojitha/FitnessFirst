package com.example.tdumy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainWater extends AppCompatActivity {
    DatabaseCon myDB;
    EditText editwater;
    TextView editwakeup, editgotup;
    int t1Hour,t1Minute,t2Hour,t2Minute;

    Button btnAddData;
    Button  btnViewData;
    ProgressDialog loadingBar;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        myDB = new DatabaseCon(this);//Calling Constructor

        createNotificationChannel();

        //Geting values
        editwater = (EditText) findViewById(R.id.water);
        editwakeup = findViewById(R.id.wakeup);
        editgotup = findViewById(R.id.gotobed);
        btnAddData = (Button) findViewById(R.id.button_w1);
        btnViewData = (Button) findViewById(R.id.button_w2);
        loadingBar = new ProgressDialog(this);

        AddData();
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainWater.this, History.class);
                startActivity(intent2);
            }
        });

        editwakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainWater.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                //Initialize hour and minute
                                t1Hour = hourofDay;
                                t1Minute = minute;
                                //Initialize calender
                                Calendar calendar = Calendar.getInstance();
                                //Initialize hour and minute
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                //set selected time on text view
                                editwakeup.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Display prious selected time
                timePickerDialog.updateTime(t1Hour,t1Minute);
                //show Dialog
                timePickerDialog.show();
            }
        });

        editgotup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainWater.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                //Initialize hour and minute
                                t2Hour = hourofDay;
                                t2Minute = minute;
                                //Initialize calender
                                Calendar calendar = Calendar.getInstance();
                                //Initialize hour and minute
                                calendar.set(0,0,0,t2Hour,t2Minute);
                                //set selected time on text view
                                editgotup.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Display prious selected time
                timePickerDialog.updateTime(t2Hour,t2Minute);
                //show Dialog
                timePickerDialog.show();
            }
        });

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                view -> {
                    editwater = (EditText) findViewById(R.id.water);
                    editwakeup = findViewById(R.id.wakeup);
                    editgotup = findViewById(R.id.gotobed);

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
                            Intent intent = new Intent(MainWater.this, History.class);
                            startActivity(intent);
                            loadingBar.dismiss();

                            Intent intent1 = new Intent(MainWater.this,ReminderToUser.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainWater.this,0,intent1,0);

                            AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

                            long timeAtButtonClick = System.currentTimeMillis();
                            long oneseconds = 1000 * 1;
                            alarmManager.set(AlarmManager.RTC_WAKEUP,
                                    timeAtButtonClick + oneseconds,
                                    pendingIntent);


                        } else {
                            Toast.makeText(MainWater.this, "Not Inserted", Toast.LENGTH_LONG).show();
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