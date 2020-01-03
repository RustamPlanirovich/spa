package com.spa.spa;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


public class Dnd extends AppCompatActivity {

    //включаем режим не беспокоить после проверки есть ли на это доступ если нет то выводит окно
    //настроек чтобы включить.
        /*Режимы 0 – Если DnD выключен - INTERRUPTION_FILTER_NONE
                1 – Если DnD включен – Приоритет -INTERRUPTION_FILTER_PRIORITY
                2 – Если DnD включен – Общее молчание - INTERRUPTION_FILTER_ALL
                3 – Если включен режим DnD – Только сигналы тревоги - INTERRUPTION_FILTER_ALARMS*/

    //Проверяем включен ли режим "Не беспокоить" в зависимости от состояния меняем состояние кнопки
    public static void reDnd(NotificationManager mNotificationManager, ToggleButton toggleButton3) {
        int currentState = mNotificationManager.getCurrentInterruptionFilter();
        if (currentState == 2) {
            toggleButton3.setChecked(true);
        } else {
            toggleButton3.setChecked(false);
        }

    }

    //Включаем режим "Не беспокоить"
    public static void onDnd(NotificationManager mNotificationManager) {
        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
    }

    //Выключаем режим " Не беспокоить"
    public static void offDnd(NotificationManager mNotificationManager) {
        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
    }
}