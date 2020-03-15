package com.spa.spa;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spa.spa.settings.Settingss;

import java.util.List;

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
  public static final String APP_PREFERENCES = "mysettings";
  public static final String APP_PREFERENCES1 = "/data/data/com.spa.spa/shared_prefs/mysettings.xml";
  private AppManager appManager;
  private RecyclerView recyclerView;
  private Context context;
  private PackageManager packageManager;
  private ToggleButton potg;


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();
    packageManager = getPackageManager();
    this.context = context;

    appManager = new AppManager(this);
    List<AppInfo> installedApps = appManager.getInstalledApps();

    AppsAdapter appsAdapter = new AppsAdapter();

    recyclerView = findViewById(R.id.apps_rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(appsAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this,3));

    appsAdapter.setApps(installedApps);
    appsAdapter.notifyDataSetChanged();

    notificationManager = (NotificationManager)
        getSystemService(Context.NOTIFICATION_SERVICE);
    dndState(notificationManager);
    mactivity = this;
    blackCurtainView = new BlackCurtainView();


    servisesettingbut = (ToggleButton) findViewById(R.id.servisesettingbut);
    reloadService = (Button) findViewById(R.id.reloadService);
    additisetting = (Button) findViewById(R.id.additisetting);
    //appsav.setText(sp.getString("Apps", ""));


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
    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
        (context, recyclerView, new RecyclerItemClickListener.OnItemMotionEventListener() {

          @Override
          public void onItemClick(View view, int position) {
            String string = String.valueOf(installedApps.get(position));
            String[] parts = string.split(",");
            String part1 = parts[0]; // 004
            Intent intent = packageManager.getLaunchIntentForPackage(part1);
            startActivity(intent);
          }

          @Override
          public void onItemLongClick(View view, int position) {
            Log.d("Array Value", "PAC" + "not checked"+installedApps.get(position));
          }
        }));
  }


    /**
     * OVERLAY_REQUEST_CODE.
     */
    public static final int OVERLAY_REQUEST_CODE = 251;


    /**
     * checkDrawOverlayPermission.
     */
    public void checkDrawOverlayPermission () {
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

    private void openFloatingWindow () {
      Intent intent = new Intent(mactivity, MyService.class);
      mactivity.stopService(intent);
      mactivity.startService(intent);
      finish();
    }

    @SuppressWarnings("checkstyle:AvoidNestedBlocks")
    @Override
    public void onActivityResult ( final int requestCode,
    final int resultCode, final Intent data){
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
    public void dndState ( final NotificationManager notificationManager){
      if (!notificationManager.isNotificationPolicyAccessGranted()) {
        Intent intent = new Intent(android.provider.Settings
            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        startActivity(intent);
      }
    }
  }
