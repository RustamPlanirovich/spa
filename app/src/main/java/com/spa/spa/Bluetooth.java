package com.spa.spa;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.widget.ToggleButton;

public class Bluetooth {

    private static BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    static View view;

    //Проверяем состояние при открытии шторки и меняем состояние кнопки
    public static boolean setBluetooth(ToggleButton toggleButton) {
        boolean isEnabled = bluetoothAdapter.isEnabled();
        if (isEnabled == true) {
            toggleButton.setChecked(true);
            return bluetoothAdapter.enable();
        } else if (isEnabled == false) {
            toggleButton.setChecked(false);
            return bluetoothAdapter.disable();
        }
        // No need to change bluetooth state
        return true;
    }

    // Включаем Bluetooth
    public static void onBluetooth() {
        bluetoothAdapter.enable();
    }

    // Выключаем Bluetooth
    public static void offBluetooth() {
        bluetoothAdapter.disable();
    }


}
