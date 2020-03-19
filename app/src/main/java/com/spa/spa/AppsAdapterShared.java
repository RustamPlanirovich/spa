package com.spa.spa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер
 */
public class AppsAdapterShared extends RecyclerView.Adapter<AppsAdapterShared.ViewHolder> {

  private List<AppInfo> apps = new ArrayList<>();

  AppInfo appInfo;
  ViewHolder viewHolder;
  View view;

  public void setApps(List<AppInfo> apps) {
    this.apps = apps;
  }

  // В этом методе мы создаем новую ячейку
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();

    LayoutInflater layoutInflater = LayoutInflater.from(context);

    view = layoutInflater.from(parent.getContext()).inflate(R.layout.item_panel, parent, false);
    viewHolder = new ViewHolder(view);


    return viewHolder;
  }

  // В этом методе мы привязываем данные к ячейке
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    boolean exists = apps.size() > position;
    if (exists) {
      appInfo = apps.get(position);
    }
    holder.potg.setChecked(true);
    holder.nameTv.setText(appInfo.getName());
    holder.versionTv.setText(appInfo.getPackageName());
    holder.iconIv.setImageDrawable(appInfo.getIcon());
    holder.potg.setClickable(true);
    holder.potg.setFocusable(true);

    holder.potg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
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

    private ImageView iconIv;
    private TextView nameTv;
    private TextView versionTv;
    private ToggleButton potg;
    private CardView cardItem;

    public ViewHolder(View itemView) {
      super(itemView);
      iconIv = itemView.findViewById(R.id.icon_iv);
      nameTv = itemView.findViewById(R.id.name_tv);
      versionTv = itemView.findViewById(R.id.version_tv);
      potg = itemView.findViewById(R.id.potg);
      cardItem = itemView.findViewById(R.id.cardItem);

    }
  }
}
