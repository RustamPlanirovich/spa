package com.spa.spa.income;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.spa.spa.R;

public class Income extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_income);
    getSupportActionBar().hide();
  }
}
