package com.spa.spa.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.spa.spa.About;
import com.spa.spa.Apps;
import com.spa.spa.R;

public class Settingss extends AppCompatActivity {
  private TextView appp;
  private TextView about;
  private CardView favapp;
  private CardView aboutapp;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    getSupportActionBar().hide();
    appp = (TextView) findViewById(R.id.appp);
    about = (TextView) findViewById(R.id.about);
    favapp = (CardView) findViewById(R.id.favapp);
    aboutapp = (CardView) findViewById(R.id.aboutapp);

    favapp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent;
        intent = new Intent(Settingss.this, Apps.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
      }
    });

    aboutapp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent;
        intent = new Intent(Settingss.this, About.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
      }
    });
  }
}
