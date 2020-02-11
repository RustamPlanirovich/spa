package com.spa.spa;

import android.os.Handler;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class timeanddate {

  public void timeAndDate(TextView time) {

     Handler mainLoopHandler = new Handler();
    mainLoopHandler.postDelayed(new Runnable(){
      public void run(){
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timaeText = timeFormat.format(currentDate);
        time.setText(timaeText);
        mainLoopHandler.postDelayed(this, 1000);
      }
     },1000);
  }
}
