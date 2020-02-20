package com.spa.spa;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.settings.Settingss;

public class MainActivity extends AppCompatActivity {

  /**
   * Activity.
   */
  private Activity mactivity;
  /**
   * notificationManager.
   */
  NotificationManager notificationManager;
  BlackCurtainView blackCurtainView;
  private ToggleButton servisesettingbut;
  private Button reloadService;
  private Button additisetting;


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();
    notificationManager = (NotificationManager)
        getSystemService(Context.NOTIFICATION_SERVICE);
    dndState(notificationManager);
    mactivity = this;
    blackCurtainView = new BlackCurtainView();




    servisesettingbut = (ToggleButton) findViewById(R.id.servisesettingbut);
    reloadService = (Button) findViewById(R.id.reloadService);
    additisetting = (Button) findViewById(R.id.additisetting);

    if (MyService.isServiceCreated()) {
      //Запущен
      servisesettingbut.setChecked(true);
    } else {
      //Не запущен
      servisesettingbut.setChecked(false);
    }

    servisesettingbut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {
        boolean onServiceSettingBut = ((servisesettingbut).isChecked());
        if (onServiceSettingBut) {
          checkDrawOverlayPermission();
        } else {
          blackCurtainView.offCurtain();
          stopService(new Intent(getApplication(), MyService.class));
        }
      }
    });

    reloadService.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {

        try {
          stopService(new Intent(getApplication(), MyService.class));
          Thread.sleep(4000);
          checkDrawOverlayPermission();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    });

    additisetting.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Settingss.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });

    //Запрос на предоставление записи настроек системы
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (!Settings.System.canWrite(getApplicationContext())) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
            Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 200);
      }
    }
  }


  /**
   * OVERLAY_REQUEST_CODE.
   */
  public static final int OVERLAY_REQUEST_CODE = 251;


  /**
   * checkDrawOverlayPermission.
   */
  public void checkDrawOverlayPermission() {
    if (Build.VERSION.SDK_INT >= 23) {
      if (!Settings.canDrawOverlays(mactivity)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_REQUEST_CODE);
      } else {
        openFloatingWindow();
        finish();
      }
    } else {
      openFloatingWindow();
      finish();
    }
  }

  private void openFloatingWindow() {
    Intent intent = new Intent(mactivity, MyService.class);
    mactivity.stopService(intent);
    mactivity.startService(intent);
    finish();
  }

  @SuppressWarnings("checkstyle:AvoidNestedBlocks")
  @Override
  public void onActivityResult(final int requestCode,
                               final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {
      case OVERLAY_REQUEST_CODE: {
        if (Build.VERSION.SDK_INT >= 23) {
          if (Settings.canDrawOverlays(mactivity)) {
            openFloatingWindow();
          }
        } else {
          openFloatingWindow();
        }
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + requestCode);
    }

  }

  /**
   * dndState.
   *
   * @param notificationManager notificationManager.
   */
  public void dndState(final NotificationManager notificationManager) {
    if (!notificationManager.isNotificationPolicyAccessGranted()) {
      Intent intent = new Intent(android.provider.Settings
          .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
      startActivity(intent);
    }
  }
}
