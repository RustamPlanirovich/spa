package com.spa.spa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends ArrayAdapter {
  private List appList = null;
  private Context context;
  private PackageManager packageManager;
  public static final String APP_PREFERENCES = "mysettings";
  private ArrayList sample;
  private SharedPreferences sp;
  public static final String APP_PREFERENCES1 = "/data/data/com.spa.spa/shared_prefs/mysettings.xml";
  private File file;
  private ToggleButton settt;

  public AppAdapter(Context context, int resource,
                    List objects) {
    super(context, resource, objects);

    this.context = context;
    this.appList = objects;
    packageManager = context.getPackageManager();
  }

  @Override
  public int getCount() {
    return ((null != appList) ? appList.size() : 0);
  }

  @Override
  public ApplicationInfo getItem(int position) {
    return ((null != appList) ? (ApplicationInfo) appList.get(position) : null);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    file = new File(APP_PREFERENCES1);
    LayoutInflater layoutInflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = layoutInflater.inflate(R.layout.list_item, null);

    sp = context.getSharedPreferences(APP_PREFERENCES,
        Context.MODE_PRIVATE);
    if (file.exists()) {
      sample = new ArrayList(sp.getStringSet("Apps", null));
    }
    ApplicationInfo data = (ApplicationInfo) appList.get(position);
    if (file.exists()) {
      for (int a = 0; a <= (sample.size() - 1); a++) {
        String string = (String) sample.get(a);
        String[] parts = string.split("-");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556

        if (part1.contains(data.loadLabel(packageManager))) {
          TextView appName = (TextView) view.findViewById(R.id.app_name);
          TextView packageName = (TextView) view.findViewById(R.id.app_package);
          ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);
          settt = (ToggleButton) view.findViewById(R.id.satt);
          //holder.settt.setOnClickListener(new Apps());
          settt.setTag(position);

          appName.setText(data.loadLabel(packageManager));
          packageName.setText(data.packageName);
          iconView.setImageDrawable(data.loadIcon(packageManager));
          appName.setTextColor(Color.RED);
//            if (appName.getTextColors().equals(Color.RED)) {
//
//            } else {
//              appName.setTextColor(Color.RED);
//            }

        } else {
          TextView appName = (TextView) view.findViewById(R.id.app_name);
          TextView packageName = (TextView) view.findViewById(R.id.app_package);
          ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);
          settt = (ToggleButton) view.findViewById(R.id.satt);
          //holder.settt.setOnClickListener(new Apps());
          settt.setTag(position);

          appName.setTextColor(Color.WHITE);
          appName.setText(data.loadLabel(packageManager));
          packageName.setText(data.packageName);
          iconView.setImageDrawable(data.loadIcon(packageManager));
        }
      }
      return view;
    } else {
      TextView appName = (TextView) view.findViewById(R.id.app_name);
      TextView packageName = (TextView) view.findViewById(R.id.app_package);
      ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);
      settt = (ToggleButton) view.findViewById(R.id.satt);
      //holder.settt.setOnClickListener(new Apps());
      settt.setTag(position);

      appName.setText(data.loadLabel(packageManager));
      packageName.setText(data.packageName);
      iconView.setImageDrawable(data.loadIcon(packageManager));
    }
    settt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (settt.isChecked()) {
          Log.d("Array Value", "POS" + "yes");
        } else {
          Log.d("Array Value", "POS" + "no");
        }
      }
    });
    return view;
  }
}

