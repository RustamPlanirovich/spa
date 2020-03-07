package com.spa.spa.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.R;

public class Notes extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);
    //Скрываем ActionBar
    getSupportActionBar().hide();
  }


}
