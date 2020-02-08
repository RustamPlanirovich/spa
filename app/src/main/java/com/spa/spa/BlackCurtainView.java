package com.spa.spa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import static android.content.Context.WINDOW_SERVICE;

public class BlackCurtainView {
  /**
   * Инициализация метода WindowManager.
   */
  private WindowManager.LayoutParams backgroundParams = new WindowManager.LayoutParams(
      WindowManager.LayoutParams.MATCH_PARENT,
      WindowManager.LayoutParams.MATCH_PARENT,
      WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
      WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

  private WindowManager windowManager;
  private RelativeLayout ll;


  public void onCurtain(Context mcontext) {
    windowManager = (WindowManager) mcontext.getSystemService(WINDOW_SERVICE);
    ll = (RelativeLayout) ((LayoutInflater) mcontext.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.black_curtain, null,false);
    //ll = (RelativeLayout) LayoutInflater.from(mcontext).inflate(R.layout.black_curtain, null,true);


    ll.findViewById(R.id.ld).setAlpha((float) (0.4));
    backgroundParams.gravity = Gravity.CENTER;
    windowManager.addView(this.ll, backgroundParams);



  }

  public void onBrig3(final SeekBar black_curtrain_seekbar) {

    black_curtrain_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @SuppressLint("ResourceType")
      @Override
      public void onProgressChanged(final SeekBar seekBar, final int value,
                                    final boolean fromUser) {
        ll.findViewById(R.id.ld).setAlpha((float) (value/100.0));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

}
