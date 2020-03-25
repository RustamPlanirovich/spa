package com.spa.spa;

public class codeVersion {

  public String versionInfo() {
    String s = ("Имя версии: " + BuildConfig.VERSION_NAME + " | Версия кода: " + String.valueOf(BuildConfig.VERSION_CODE));
    return s;
  }

}
