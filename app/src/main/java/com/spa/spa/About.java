package com.spa.spa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {
private TextView ver;
private codeVersion mCodeVersion;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);
    getSupportActionBar().hide();
    mCodeVersion = new codeVersion();
    ver = (TextView) findViewById(R.id.ver);

    ver.setText(mCodeVersion.versionInfo());
  }
}
