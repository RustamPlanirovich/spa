package com.spa.spa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    ConnectivityManager cm =
        (ConnectivityManager) mcontext.getSystemService(
            Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
      mobileData.setChecked(true);
    } else {
      mobileData.setChecked(false);
    }
  }
}
