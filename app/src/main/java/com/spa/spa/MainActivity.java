package com.spa.spa;

import android.app.Activity;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.spa.spa.settings.Settingss;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends ListActivity {

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
  private String appPreferencesName = "AppNamed";
  private TextView appsav;
  private SharedPreferences sp;
  private String load;

  private PackageManager packageManager = null;
  private List applist = null;
  private AppAdapter listadapter = null;
  private File file;
  private ArrayList appList;


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //getSupportActionBar().hide();
    notificationManager = (NotificationManager)
        getSystemService(Context.NOTIFICATION_SERVICE);
    dndState(notificationManager);
    mactivity = this;
    blackCurtainView = new BlackCurtainView();

    sp = getSharedPreferences(APP_PREFERENCES,
        Context.MODE_PRIVATE);

    servisesettingbut = (ToggleButton) findViewById(R.id.servisesettingbut);
    reloadService = (Button) findViewById(R.id.reloadService);
    additisetting = (Button) findViewById(R.id.additisetting);
    appsav = (TextView) findViewById(R.id.appSave);
    //appsav.setText(sp.getString("Apps", ""));

    file = new File(APP_PREFERENCES1);

    if (file.exists()) {
      Set<String> set = new HashSet<String>(sp.getStringSet("Apps", null));
      appsav.setText(String.valueOf(set));
      ArrayList<String> sample = new ArrayList<String>(sp.getStringSet("Apps", null));
      if (sample.size() > 0) {
        for (int i = (sample.size() - 1); i >= 0; i--) {
          Log.d("Array Value", "Array Value" + sample.get(i));
        }
      }
    } else {
      appsav.setText("false");
    }


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

    packageManager = getPackageManager();
    new LoadApplications().execute();
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


  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);

    ApplicationInfo app = (ApplicationInfo) applist.get(position);

    try {
      Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

      if (intent != null) {
        startActivity(intent);
      }
    } catch (ActivityNotFoundException e) {
      Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
  }

  private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
//    if (file.exists()) {
      appList = new ArrayList();
      ArrayList sample = new ArrayList(sp.getStringSet("Apps", null));
      if (sample.size() > 0) {
        for (int i = 0; i <= (sample.size() - 1); i++) {
          String string = (String) sample.get(i);
          String[] parts = string.split("-");
          String part1 = parts[0]; // 004
          String part2 = parts[1]; // 034556
          load = part2;
          for (ApplicationInfo info : list) {
            try {
              if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                String lap = info.packageName;
                if (load.equals(lap)) {
                  appList.add(info);
                }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
      return appList;
//    }
//    return appList;
  }

  private class LoadApplications extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progress = null;

    @Override
    protected Void doInBackground(Void... params) {

      applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

      listadapter = new AppAdapter(MainActivity.this, R.layout.list_item, applist);

      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      setListAdapter(listadapter);
      progress.dismiss();
      super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
      progress = ProgressDialog.show(MainActivity.this, null, "Loading apps info...");
      super.onPreExecute();
    }
  }
}
