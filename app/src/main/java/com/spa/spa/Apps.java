package com.spa.spa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Apps extends AppCompatActivity {
  private AppManager appManager;
  private RecyclerView recyclerView;
  private Context context;
  private PackageManager packageManager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_apps);
    getSupportActionBar().hide();
    packageManager = getPackageManager();
    appManager = new AppManager(this);
    List<AppInfo> installedApps = appManager.getInstalledApps();

    AppsAdapter appsAdapter = new AppsAdapter();

    recyclerView = findViewById(R.id.apps_rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(appsAdapter);
    //recyclerView.setLayoutManager(new GridLayoutManager(this,3));

    appsAdapter.setApps(installedApps);
    appsAdapter.notifyDataSetChanged();

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
}
