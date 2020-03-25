package com.spa.spa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoStart extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    if ("android.intent.action.BOOT_COMPLETED".equals(Intent.ACTION_BOOT_COMPLETED)){
      Intent serviceIntent = new Intent(context, MyService.class);
      context.startForegroundService(serviceIntent);
      Log.d("Array Value", "BOOT" + "not checked " + "Start");
    }
  }
}
