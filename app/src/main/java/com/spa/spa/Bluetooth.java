package com.spa.spa;

import android.bluetooth.BluetoothAdapter;
import android.widget.ToggleButton;


class Bluetooth {

  /**
   * BluetoothAdapter.
   */
  private static BluetoothAdapter bluetoothAdapter = BluetoothAdapter
      .getDefaultAdapter();

  /**
   * Проверяем состояние при открытии шторки и меняем состояние кнопки.
   * @param bluetooth bluetooth.
   */
  public void setBluetooth(final ToggleButton bluetooth) {
    boolean isEnabled = bluetoothAdapter.isEnabled();
    if (isEnabled == true) {
      bluetooth.setChecked(true);
      //bluetoothAdapter.enable();
    } else {
      bluetooth.setChecked(false);
      //bluetoothAdapter.disable();
    }
    // No need to change bluetooth state
  }

  // Включаем Bluetooth
  static void onBluetooth() {
    bluetoothAdapter.enable();
  }

  // Выключаем Bluetooth
  static void offBluetooth() {
    bluetoothAdapter.disable();
  }


}
