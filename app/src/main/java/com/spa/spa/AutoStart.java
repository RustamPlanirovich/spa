package com.spa.spa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStart extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
      Intent serviceIntent = new Intent(context, MyService.class);
      context.startService(serviceIntent);
    }
  }
}
