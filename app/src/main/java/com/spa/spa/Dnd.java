package com.spa.spa;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class Dnd extends Activity {
    //включаем режим не беспокоить после проверки есть ли на это доступ если нет то выводит окно
    //настроек чтобы включить.
        /*Режимы 0 – Если DnD выключен - INTERRUPTION_FILTER_NONE
                1 – Если DnD включен – Приоритет -INTERRUPTION_FILTER_PRIORITY
                2 – Если DnD включен – Общее молчание - INTERRUPTION_FILTER_ALL
                3 – Если включен режим DnD – Только сигналы тревоги - INTERRUPTION_FILTER_ALARMS*/

//    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//    public void DndState() {
//        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
//            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//            startActivity(intent);
//        }else {
//        }
//    }

    public void reDnd() {

    }

    public void onDnd(NotificationManager mNotificationManager) {
        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }else {
            mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
        }
    }

    public void offDnd(NotificationManager mNotificationManager) {
        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
    }
}