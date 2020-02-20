package com.spa.spa;

import android.content.Context;
import android.widget.ImageView;

public class battary {

  public void setimg(Context mcontext, int level, ImageView batt, String a) {

    //Установка иконки на SeekBar
    int[] foren = {
        R.drawable.bat_25_precent,
        R.drawable.bat_50_precent,
        R.drawable.bat_75_precent,
        R.drawable.bat_100_precent,
        R.drawable.charge
    };
//    Drawable z25 = mcontext.getDrawable(foren[0]);
//    Drawable z50 = mcontext.getDrawable(foren[1]);
//    Drawable z75 = mcontext.getDrawable(foren[2]);
//    Drawable z100 = mcontext.getDrawable(foren[3]);

    if (a == "true" ) {
      batt.setImageResource(foren[4]);
    } else {
      if (level <= 25) {
        batt.setImageResource(foren[0]);
      }
      if (level >= 27 || level <= 50) {
        batt.setImageResource(foren[1]);
      }
      if (level >= 51 || level <= 75) {
        batt.setImageResource(foren[2]);
      }
      if (level >= 76 || level == 100) {
        batt.setImageResource(foren[3]);
      }
    }
  }
}
