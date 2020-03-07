package com.spa.spa;

import android.os.Handler;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class timeanddate {

  public void timeAndDate(TextView time,TextView textView) {

     Handler mainLoopHandler = new Handler();
    mainLoopHandler.postDelayed(new Runnable(){
      public void run(){
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timaeText = timeFormat.format(currentDate);
        time.setText(timaeText);

        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        String dateText1 = dateFormat1.format(currentDate);
        textView.setText(dateText + " " + dateText1);
        mainLoopHandler.postDelayed(this, 1000);
      }
     },1000);
  }
}
