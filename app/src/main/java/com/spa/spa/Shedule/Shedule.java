package com.spa.spa.Shedule;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.R;

public class Shedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule);
        getSupportActionBar().hide();
    }
}