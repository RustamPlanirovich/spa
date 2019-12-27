package com.spa.spa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.ToggleButton;

@SuppressLint("Registered")
public class Wifiset extends Activity {
    private View overlayView;

    //Отрабатываем включение Wifi - - параметром получаем экземпляр WifiManager
    public void Wifienable(WifiManager wifiManager) {
        wifiManager.setWifiEnabled(true);
    }

    //Отрабатываем выключение Wifi - - параметром получаем экземпляр WifiManager
    public void Wifidisable(WifiManager wifiManager) {
        wifiManager.setWifiEnabled(false);
    }

    //Периодическое обновление состояния Wifi и обновление состояния кнопки
    public void WifiRe(ToggleButton toggleButton, WifiManager wifiManager) {
        // Проверка Адаптер Wi-Fi включен
        if (wifiManager.isWifiEnabled()) {
            // Адаптер Wi-Fi включен
            toggleButton.setChecked(true);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            /*if( wifiInfo.getNetworkId() == -1 ){
            return false; // Не подключен к точке доступа
            }
            return true; // Подключение к точке доступа*/
        } else { // Адаптер Wi-Fi выключен
            //return false;
            toggleButton.setChecked(false);
        }

    }

}