package com.spa.spa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.ToggleButton;

import java.io.IOException;

public class Airplane extends Activity {


    //Включаем режим полета Root
    public static void onAirplayRoot() throws IOException {
        String commandToRun = "adb shell settings put global airplane_mode_on 1&adb shell am broadcast -a android.intent.action.AIRPLANE_MODE";
        Runtime.getRuntime().exec(commandToRun);
    }

    //Выключаем режим самолета Root
    public static void offAirplayRoot() throws IOException {
        String commandToRun = "adb shell settings put global airplane_mode_on 0&adb shell am broadcast -a android.intent.action.AIRPLANE_MODE";
        Runtime.getRuntime().exec(commandToRun);
    }

    //Включаем режим самолета буз Root
    public static void onAirplayNotRoot(Context mcontext) {
        Intent intent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    //Выключаем режим самолета буз Root
    public static void offAirplayNotRoot(Context mcontext) {
        Intent intent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);

    }

    //Получаем текущее состояние Airplane mode
    static boolean isAirplaneModeOn(Context mcontext) {

        return Settings.System.getInt(mcontext.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

    //Обновление состояния кнопки в зависимости от состояния Airplane mode
    public static void AirRe(ToggleButton toggleButton1, Context mcontext) {
        if (isAirplaneModeOn(mcontext)) {
            toggleButton1.setChecked(true);
        } else {
            toggleButton1.setChecked(false);
        }
    }
}
