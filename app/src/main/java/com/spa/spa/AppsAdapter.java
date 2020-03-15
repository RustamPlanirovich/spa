package com.spa.spa;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Адаптер
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

  private List<AppInfo> apps = new ArrayList<>();
  private Context mContext;

  private SharedPreferences sp;
  public static final String APP_PREFERENCES = "mysettings";
  public static final String APP_PREFERENCES1 = "/data/data/com.spa.spa/shared_prefs/mysettings.xml";
  private Set<String> set;
  private File file;
  private ArrayList arr1;
  private ArrayList sample;

  public void setApps(List<AppInfo> apps) {
    this.apps = apps;
  }

  // В этом методе мы создаем новую ячейку
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    mContext = context;
    LayoutInflater layoutInflater = LayoutInflater.from(context);

    View view = layoutInflater.from(parent.getContext()).inflate(R.layout.view_item_app, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);

    arr1 = new ArrayList<>();
    file = new File(APP_PREFERENCES1);
    sp = context.getSharedPreferences(APP_PREFERENCES,
        Context.MODE_PRIVATE);
    if (file.exists()) {
      set = new HashSet<String>(sp.getStringSet("Apps", null));
    } else {
      set = new HashSet<String>();
    }
    return viewHolder;
  }

  // В этом методе мы привязываем данные к ячейке
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    AppInfo appInfo = apps.get(position);

    holder.nameTv.setText(appInfo.getName());
    holder.versionTv.setText(appInfo.getPackageName());
    holder.iconIv.setImageDrawable(appInfo.getIcon());
    holder.potg.setClickable(true);
    holder.potg.setFocusable(true);
    holder.potg.setTag(position);

    if (file.exists()) {
      sample = new ArrayList(sp.getStringSet("Apps", null));
      for (int a = 0; a <= (sample.size() - 1); a++) {
        String string = (String) sample.get(a);
        String[] parts = string.split("-");
        String part1 = parts[0];
        if (part1.contains(appInfo.getName())) {
          holder.potg.setChecked(true);
        }
      }
    }


    holder.potg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean isChecked = holder.potg.isChecked();
        if (isChecked) {
          arr1.add(appInfo.getName() + "-" + appInfo.getPackageName());
          addApps();
        } else {
          String appdel = (String) appInfo.getName() + "-" + appInfo.getPackageName();
          int a = sample.indexOf(appdel);
          Log.d("Array Value", "PAC" + appdel + " " + a);
          set.remove(appdel);
          SharedPreferences.Editor editor = sp.edit();
          editor.putString("Apps", String.valueOf(set));
          editor.commit();
          addApps();
        }
      }
    });

  }

  // В этом методе мы возвращаем количество элементов списка
  @Override
  public int getItemCount() {
    return apps.size();
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemViewType(int position) {
    return position;
  }

  /**
   * View holder Хранит информацию о ячейке
   */
  static class ViewHolder extends RecyclerView.ViewHolder {

    private final ImageView iconIv;
    private final TextView nameTv;
    private final TextView versionTv;
    private final ToggleButton potg;

    public ViewHolder(View itemView) {
      super(itemView);

      iconIv = itemView.findViewById(R.id.icon_iv);
      nameTv = itemView.findViewById(R.id.name_tv);
      versionTv = itemView.findViewById(R.id.version_tv);
      potg = itemView.findViewById(R.id.potg);

    }
  }

  public void addApps() {
    SharedPreferences.Editor editor = sp.edit();
    set.addAll(arr1);
    editor.putStringSet("Apps", set);
    editor.commit();
  }
}
