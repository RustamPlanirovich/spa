package com.spa.spa.plans;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.spa.spa.R;

public class Plans extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plans);
    getSupportActionBar().hide();
  }
}
