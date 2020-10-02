package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MUSCLEGROUPSS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_u_s_c_l_e_g_r_o_u_p_s_s);
    }


    //
    public void gainchest (View view){
        Intent intent = new Intent (this, Excercises.class);
        startActivity(intent);
    }
    //

    public void abs (View view){
        Intent intent = new Intent (this, abs.class);
        startActivity(intent);
    }

    public void gainbicep (View view){
        Intent intent = new Intent (this, gainbicep.class);
        startActivity(intent);
    }

    public void gainshoulders (View view){
        Intent intent = new Intent (this, gainshoulders.class);
        startActivity(intent);
    }

    public void gaintriceps (View view){
        Intent intent = new Intent (this, gaintriceps.class);
        startActivity(intent);
    }

    public void gainback (View view){
        Intent intent = new Intent (this, gainback.class);
        startActivity(intent);
    }


    public void gainlegs (View view){
        Intent intent = new Intent (this, gainlegs.class);
        startActivity(intent);
    }


}