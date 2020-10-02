package com.example.tdumy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    EditText waterEdit,wakeupEdit,gotobedEdit;
    //Databse Row id
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String water = getIntent().getExtras().getString("water");
        String wakeup = getIntent().getExtras().getString("wakeup");
        String gotobed = getIntent().getExtras().getString("gotobed");

        waterEdit =  findViewById(R.id.waterE);
        wakeupEdit = findViewById(R.id.wakeupE);
        gotobedEdit = findViewById(R.id.gotobedE);

        //Set values
        waterEdit.setText(water);
        wakeupEdit.setText(wakeup);
        gotobedEdit.setText(gotobed);
    }
    //Calling OnClick Method
    public void edit(View view){
        //Recevie Edit Text Content
        String water = waterEdit.getText().toString();
        String wakeup = wakeupEdit.getText().toString();
        String gotobed = gotobedEdit.getText().toString();

        History.myDB.updateInfo(water,wakeup,gotobed);
        startActivity(new Intent(Edit.this, History.class));
        //Toast.makeText(Edit.this,"Show",Toast.LENGTH_LONG).show();
        finish();
    }

    public void deleteInfo(View view) {
        String water = waterEdit.getText().toString();

        History.myDB.deleteData(water);
        startActivity(new Intent(Edit.this, History.class));
        finish();
    }
}
