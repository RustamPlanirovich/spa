package com.spa.spa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.ToggleButton;

public class MobileData {

    //Включение мобильных даных (открывает окно настроек)
    public static void onData(Context mcontext) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    //Включение мобильных даных (открывает окно настроек)
    public static void offData(Context mcontext) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    //Проверка включен ли мобильный интернет
    public static void reData(Context mcontext, ToggleButton toggleButton4) {
        ConnectivityManager cm =
                (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            toggleButton4.setChecked(true);
        }else {
            toggleButton4.setChecked(false);
        }
    }
}
