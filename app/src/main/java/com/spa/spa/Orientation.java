package com.spa.spa;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.widget.ToggleButton;

public class Orientation {

    //Включаем автоповорот
    public static void onAutoOrientation(Context mcontext) {
        //Значение для включения автоповорота
        boolean enabled = true;
        //Передаем системепараметр включения автоповорота
        Settings.System.putInt(mcontext.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);
    }

    //Выключаем автоповорот
    public static void offAutoOrientation(Context mcontext) {
        //Значение для выключения автоповорота
        boolean enabled1 = false;
        //Передаем системепараметр выключения автоповорота
        Settings.System.putInt(mcontext.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled1 ? 1 : 0);
    }

    //Проверяем включен ли автоповорот и в соответствии с ответом меняем состояние кнопки
    public static void reAutoOrientation(Context mcontext, ToggleButton toggleButton5) {

        try {
            if (Settings.System.getInt(mcontext.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION) == 1) {
                toggleButton5.setChecked(true);
            } else {
                toggleButton5.setChecked(false);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
}
