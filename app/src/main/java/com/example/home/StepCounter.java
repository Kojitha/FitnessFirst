package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounter extends AppCompatActivity implements SensorEventListener {


    TextView tv_steps;
    SensorManager sensorManager;
    TextView text_date,text_type;
    Button button;


    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_step);

        button=(Button) findViewById(R.id.btn_end);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StepCounter.this, com.example.home.End_Workout.class);
                startActivity(intent);
            }
        });

        //databaseHelper=new DBHelperStep(this);
        //SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();



        //Update
        //ListView lvContact=findViewById(R.id.lvContact);
        //SimpleCursorAdapter simpleCursorAdapter=databaseHelper.populate();


        //update

        tv_steps= (TextView) findViewById(R.id.tv_steps_counter);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor !=null){
               sensorManager.registerListener(this,countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running=false;
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            tv_steps.setText(String.valueOf(sensorEvent.values[0]));

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}