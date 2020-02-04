package com.spa.spa.currencyconverter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.spa.spa.R;

public class currencyConverter extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_currency_converter);
    getSupportActionBar().hide();
  }
}
