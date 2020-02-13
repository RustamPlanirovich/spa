package com.spa.spa.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.spa.spa.R;

public class Settingss extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    getSupportActionBar().hide();
  }
}
