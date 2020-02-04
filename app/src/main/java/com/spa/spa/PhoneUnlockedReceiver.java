package com.spa.spa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;

public class PhoneUnlockedReceiver extends BroadcastReceiver {

  /**
   * Название файла настроек.
   */
  public static final String APP_PREFERENCES = "mysettings";
  /**
   * Название строки для считывания значения.
   */
  public static final String APP_PREFERENCES_NAME = "brig";
  /**
   * Создаем экземпляр MyService.
   */
  MyService myService = new MyService();

  @Override
  public void onReceive(final Context context, final Intent intent) {
    if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
      //Запускаем таймер на отработку каждые
      // 300 миллисекунд на обнаружение действия
      //разблокировки
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        public void run() {
          //Создает контекст для чтения настроект из SharedPreference файла
          SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCES,
              Context.MODE_PRIVATE);
          //Присваиваем переменной brig1 значение
          // после прочтения файла настройки
          int brig1 = sp.getInt(APP_PREFERENCES_NAME, 0);
          //Устанавливаем считаенное значение для
          // яркости экрана после разблокировки
          //экрана
          Settings.System.putInt(context.getContentResolver(),
              Settings.System.SCREEN_BRIGHTNESS, brig1);
          //Задержка в миллисекундах между интерациями таймера
        }
      }, 300);

    } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
      //Вызываем метод скрытия панели
      myService.onLock();
    }
  }
}
