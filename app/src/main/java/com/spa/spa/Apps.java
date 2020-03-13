package com.spa.spa;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apps extends ListActivity implements View.OnClickListener {
  private PackageManager packageManager = null;
  private List applist = null;
  private AppAdapter listadapter = null;
  private String appNames;
  private String appPreferencesName;
  public static final String APP_PREFERENCES = "mysettings";
  private TextView appset;
  public int appind = 0;
  private ArrayList arr1;
  private SharedPreferences sp;
  private Set<String> set;
  public static final String APP_PREFERENCES1 = "/data/data/com.spa.spa/shared_prefs/mysettings.xml";
  private File file;
  private int cli;
  private ToggleButton settt;
  private int pos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_apps);
    packageManager = getPackageManager();
    new LoadApplications().execute();
    file = new File(APP_PREFERENCES1);
    sp = getSharedPreferences(APP_PREFERENCES,
        Context.MODE_PRIVATE);
    appset = (TextView) findViewById(R.id.app_name);
    arr1 = new ArrayList<>();
    if (file.exists()) {
      set = new HashSet<String>(sp.getStringSet("Apps", null));
    } else {
      set = new HashSet<String>();
    }
    settt = (ToggleButton) findViewById(R.id.satt);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    ApplicationInfo app = (ApplicationInfo) applist.get(position);
    pos = position;
    settt = (ToggleButton) findViewById(R.id.satt);
    if (1 == cli) {
      Log.d("Array Value", "CLI" + cli);
      boolean yes = set.contains(app.loadLabel(packageManager) + "-" + app.packageName);
      String yes1 = app.loadLabel(packageManager) + "-" + app.packageName;
      if (yes) {
        Log.d("Array Value", "CLI" + "yes");
        int a = Arrays.asList(set).indexOf(yes1);
        set.remove(yes1);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("Apps", set);
        editor.commit();
      }
    } else {
      try {
        Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);
        appNames = app.packageName;
        appPreferencesName = (String) app.loadLabel(packageManager);
        arr1.add(app.loadLabel(packageManager) + "-" + app.packageName);
        addApps();
        if (1 == cli) {
          cli--;
        } else {
          cli++;
        }
        if (intent != null) {
          appind++;
          //startActivity(intent);
        }
      } catch (ActivityNotFoundException e) {
        Toast.makeText(Apps.this, e.getMessage(), Toast.LENGTH_LONG).show();
      } catch (Exception e) {
        Toast.makeText(Apps.this, e.getMessage(), Toast.LENGTH_LONG).show();
      }
    }
  }


  private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {

    ArrayList appList = new ArrayList();

    for (ApplicationInfo info : list) {
      try {
        if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
          appList.add(info);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return appList;
  }

  @Override
  public void onClick(View v) {
    ToggleButton tb = (ToggleButton)v;

    if (tb.isChecked()) {
      Log.d("Array Value", "POS" + "yes");
    } else {
      Log.d("Array Value", "POS" + "no");
    }
  }


//  @Override
//  public void onClick(View v) {
//    settt = (ToggleButton) view.findViewById(R.id.satt);
//    settt.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        settt.getTag(position);
//        boolean clik = settt.isChecked();
//        if (clik) {
//          Toast.makeText(Apps.this, "Enable", Toast.LENGTH_LONG).show();
//          Log.d("Array Value", "EN" + clik);
//        } else {
//          Toast.makeText(Apps.this, "Disable", Toast.LENGTH_LONG).show();
//          Log.d("Array Value", "EN" + clik);
//        }
//      }
//    });
//  }


  private class LoadApplications extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progress = null;

    @Override
    protected Void doInBackground(Void... params) {

      applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

      listadapter = new AppAdapter(Apps.this, R.layout.list_item, applist);
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
      progress = ProgressDialog.show(Apps.this, null, "Loading apps info...");
      super.onPreExecute();
    }

  }


  public void addApps() {
    SharedPreferences.Editor editor = sp.edit();
    set.addAll(arr1);
    editor.putStringSet("Apps", set);
    editor.commit();

  }
}
