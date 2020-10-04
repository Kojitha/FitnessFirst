package com.example.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditStep extends AppCompatActivity {
    EditText type_edit,date_edit,steps_edit;
    //Database Row id



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_step);


        String steps = getIntent().getExtras().getString("steps");
        String type = getIntent().getExtras().getString("type");
        String date = getIntent().getExtras().getString("date");

        steps_edit=findViewById(R.id.edit_steps);
        type_edit=findViewById(R.id.edit_type);
        date_edit=findViewById(R.id.edit_date);
        
        //Set values

        steps_edit.setText(steps);
        type_edit.setText(type);
        date_edit.setText(date);


    }
      //calling onclick method
    public void edit(View view){
        //Recevie EditStep Text Content

        String steps= steps_edit.getText().toString();
        String type = type_edit.getText().toString();
        String date = date_edit.getText().toString();


        ListStep.myDB.updateInfo(steps,type,date);
        startActivity(new Intent(EditStep.this, ListStep.class));
        //Toast.makeText(EditStep.this,"Show",Toast.LENGTH_LONG).show();
        finish();


    }
     public void deletedata(View view){

        String steps =steps_edit.getText().toString();


         ListStep.myDB.deleteinfo(steps);
         startActivity(new Intent(EditStep.this, ListStep.class));
         finish();


     }

}