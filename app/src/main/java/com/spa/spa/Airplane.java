package com.spa.spa;

import android.provider.Settings;

import java.io.IOException;

public class Airplane {


    //Включаем режим полета
    public static void onAirplay() throws IOException {
        String commandToRun = "adb shell settings put global airplane_mode_on 1&adb shell am broadcast -a android.intent.action.AIRPLANE_MODE";
        Runtime.getRuntime().exec(commandToRun);
    }

    //Выключаем режим самолета
    public static void offAirplay() throws IOException {
        String commandToRun = "adb shell settings put global airplane_mode_on 0&adb shell am broadcast -a android.intent.action.AIRPLANE_MODE";
        Runtime.getRuntime().exec(commandToRun);
    }
}
