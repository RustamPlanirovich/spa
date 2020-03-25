package com.spa.spa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, получающий список пакетов
 */
public class AppManagerShared {

  private final PackageManager packageManager;
  private ArrayList sample;
  private SharedPreferences sp;
  private File file;
  public static final String APP_PREFERENCES1 = "/data/data/com.spa.spa/shared_prefs/favoriteapp.xml";
  public static final String APP_PREFERENCES = "favoriteapp";
  List<AppInfo> installedApps;

  public AppManagerShared(Context context) {
    packageManager = context.getPackageManager();
    file = new File(APP_PREFERENCES1);
    sp = context.getSharedPreferences(APP_PREFERENCES,
        Context.MODE_PRIVATE);
  }

  public List<AppInfo> getInstalledApps() {
    if (file.exists()) {
      sample = new ArrayList(sp.getStringSet("Apps", null));

      installedApps = new ArrayList<>();

      List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);

      for (PackageInfo installedPackage : installedPackages) {
        if (packageManager.getLaunchIntentForPackage(installedPackage.packageName) != null) {
          if (file.exists()) {
            for (int a = 0; a <= (sample.size() - 1); a++) {
              String string = (String) sample.get(a);
              String[] parts = string.split("-");
              String part1 = parts[0];
              AppInfo appInfo = new AppInfo(
                  installedPackage.packageName, // Имя пакета
                  installedPackage.versionCode, // Код версии
                  installedPackage.versionName, // Имя версии
                  installedPackage.applicationInfo.loadLabel(packageManager).toString(), // Имя приложения
                  installedPackage.applicationInfo.loadIcon(packageManager)// Иконка приложения
              );
              if (part1.equals(appInfo.getName())) {
                installedApps.add(appInfo);
              } else {
              }

            }
          }
        }
      }
    }
      return installedApps;
    }
}
