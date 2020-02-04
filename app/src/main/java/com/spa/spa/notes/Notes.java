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

  /**
   * active.
   */
  private static boolean active = false;

  @Override
  public void onStart() {
    super.onStart();
    active = true;
  }
  @Override
  public void onStop() {
    super.onStop();
    active = false;
  }
}
