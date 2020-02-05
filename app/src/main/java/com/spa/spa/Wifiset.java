package com.spa.spa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.ToggleButton;

@SuppressLint("Registered")
public class Wifiset extends Activity {
  /**
   * View.
   */
  private View overlayView;

  /**
   * Отрабатываем включение Wifi - - параметром получаем экземпляр WifiManager.
   * @param wifiManager
   */
  public void wifienable(final WifiManager wifiManager) {
    wifiManager.setWifiEnabled(true);
  }

  /**
   * Отрабатываем выключение Wifi - - параметром получаем экземпляр WifiManager.
   * @param wifiManager
   */
  public void wifidisable(final WifiManager wifiManager) {
    wifiManager.setWifiEnabled(false);
  }

  /**
   * Периодическое обновление состояния Wifi и обновление состояния кнопки.
   *
   * @param wifi wifi.
   * @param wifiManager  wifiManager.
   */
  public void wifiRe(final ToggleButton wifi,
                     final WifiManager wifiManager) {
    // Проверка Адаптер Wi-Fi включен
    if (wifiManager.isWifiEnabled()) {
      // Адаптер Wi-Fi включен
      wifi.setChecked(true);
      //WifiInfo wifiInfo = wifiManager.getConnectionInfo();

      /*if( wifiInfo.getNetworkId() == -1 ){
      return false; // Не подключен к точке доступа
      }
      return true; // Подключение к точке доступа*/
    } else { // Адаптер Wi-Fi выключен
      //return false;
      wifi.setChecked(false);
    }

  }

}