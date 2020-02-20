package com.spa.spa;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.ToggleButton;

public class mobileDate {

  /**
   * Включение мобильных даных (открывает окно настроек).
   * @param mcontext mcontext.
   */
  public static void onData(final Context mcontext) {
    Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mcontext.startActivity(intent);
  }

  /**
   * Включение мобильных даных (открывает окно настроек).
   * @param mcontext mcontext.
   */
  public static void offData(final Context mcontext) {
    Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mcontext.startActivity(intent);
  }

  //Проверка включен ли мобильный интернет

  /**
   * Проверка включен ли мобильный интернет.
   * @param mcontext mcontext.
   * @param mobileData mobileData.
   */
  public void reData(final Context mcontext,
                            final ToggleButton mobileData) {

    int mobileDatas = Settings.Secure.getInt(mcontext.getContentResolver(), "mobile_data", 0);
    if (mobileDatas == 1) {
      mobileData.setChecked(true);
    } else {
      mobileData.setChecked(false);
    }
  }
}
