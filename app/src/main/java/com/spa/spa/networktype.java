package com.spa.spa;

import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class networktype {


  public void speed(Context mcontext, TextView netType) {
    Handler mainLoopHandler = new Handler();
    mainLoopHandler.postDelayed(new Runnable() {
      public void run() {

        String types = getNetworkType(mcontext);
        netType.setText(types);
        mainLoopHandler.postDelayed(this, 1000);
      }
    }, 1000);
  }

  public static String getNetworkType(Context mcontext) {

    TelephonyManager teleMan = (TelephonyManager) mcontext.getSystemService(Context.TELEPHONY_SERVICE);
    int networkType = teleMan.getNetworkType();
    switch ( networkType ) {
      case TelephonyManager.NETWORK_TYPE_UNKNOWN:
        return "Unknown network";
      case TelephonyManager.NETWORK_TYPE_GSM:
        return "GSM";
      case TelephonyManager.NETWORK_TYPE_CDMA:
      case TelephonyManager.NETWORK_TYPE_1xRTT:
      case TelephonyManager.NETWORK_TYPE_IDEN:
        return "2G";
      case TelephonyManager.NETWORK_TYPE_GPRS:
        return "G";
      case TelephonyManager.NETWORK_TYPE_EDGE:
        return "E";
      case TelephonyManager.NETWORK_TYPE_UMTS:
      case TelephonyManager.NETWORK_TYPE_EVDO_0:
      case TelephonyManager.NETWORK_TYPE_EVDO_A:
      case TelephonyManager.NETWORK_TYPE_EVDO_B:
        return "3G";
      case TelephonyManager.NETWORK_TYPE_HSPA:
      case TelephonyManager.NETWORK_TYPE_HSDPA:
      case TelephonyManager.NETWORK_TYPE_HSUPA:
        return "H";
      case TelephonyManager.NETWORK_TYPE_EHRPD:
      case TelephonyManager.NETWORK_TYPE_HSPAP:
      case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
        return "H+";
      case TelephonyManager.NETWORK_TYPE_LTE:
      case TelephonyManager.NETWORK_TYPE_IWLAN:
        return "4G";
      default:
        return "-";
    }
  }
}
