package com.spa.spa;

import android.app.NotificationManager;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;


public class Dnd extends AppCompatActivity {

  //включаем режим не беспокоить после проверки есть ли на это доступ если нет то выводит окно
  //настроек чтобы включить.
  /*Режимы 0 – Если DnD выключен - INTERRUPTION_FILTER_NONE
  1 – Если DnD включен – Приоритет -INTERRUPTION_FILTER_PRIORITY
  2 – Если DnD включен – Общее молчание - INTERRUPTION_FILTER_ALL
  3 – Если включен режим DnD – Только сигналы тревоги - INTERRUPTION_FILTER_ALARMS*/

  /**
   * Проверяем включен ли режим "Не беспокоить"
   * в зависимости от состояния меняем состояние кнопки.
   * @param mnotificationManager mnotificationManager.
   * @param dnD dnD.
   */
  public void reDnd(final NotificationManager mnotificationManager,
                           final ToggleButton dnD) {
    int currentState = mnotificationManager.getCurrentInterruptionFilter();
    if (currentState == 2) {
      dnD.setChecked(true);
    } else {
      dnD.setChecked(false);
    }

  }

  //Включаем режим "Не беспокоить"
  public static void onDnd(final NotificationManager notificationManager) {
    notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
  }

  //Выключаем режим " Не беспокоить"
  public static void offDnd(final NotificationManager notificationManager) {
    notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
  }
}