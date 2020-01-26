package com.spa.spa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;

public class PhoneUnlockedReceiver extends BroadcastReceiver {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "brig";

MyService myService = new MyService();
    @Override
    public void onReceive(final Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCES,
                            Context.MODE_PRIVATE);

                    int brig1 = sp.getInt(APP_PREFERENCES_NAME, 0);

                    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brig1);

                }
            }, 500);

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

        }
    }
}
