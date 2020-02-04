package com.spa.spa;

import android.view.View;
import android.widget.TextView;

class CodeVersion {

  //Получаем название версии и версию кода приложения
  public static void versionInfo(final View overlayView) {

    //Получаем имя версии
    String versionName = BuildConfig.VERSION_NAME;
    //Получаем версию кода
    int versionCode = BuildConfig.VERSION_CODE;
    String v = String.valueOf(versionCode);
    TextView textView = (TextView) overlayView.findViewById(R.id.text);
    textView.setText("Имя версии: " + versionName + " |Версия кода: " + v);
  }
}
