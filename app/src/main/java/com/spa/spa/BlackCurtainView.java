package com.spa.spa;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Создаем параметры слоя для отображения.
 */
public class BlackCurtainView {
  /**
   * Инициализация метода WindowManager для слоя black_curtain
   */
  private WindowManager.LayoutParams backgroundParams = new WindowManager.LayoutParams(
      WindowManager.LayoutParams.MATCH_PARENT,
      WindowManager.LayoutParams.MATCH_PARENT,
      WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
      WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

  private WindowManager windowManager;
  private RelativeLayout ll;
  private MyService myService;

  /**
   * Класс создает экземпляры необходим служб и запускает отрисову слоя.
   *
   * @param mcontext mcontext.
   * @param blacint
   */
  public void onCurtain(Context mcontext, int blacint) {
    //Экземпляр windowManager
    windowManager = (WindowManager) mcontext.getSystemService(WINDOW_SERVICE);
    //Инициализируем слой
    ll = (RelativeLayout) ((LayoutInflater) mcontext.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.black_curtain, null, false);
    //Устанавливаем начальное значение альфа прозрачности
    ll.findViewById(R.id.ld).setAlpha((float) (blacint));
    backgroundParams.gravity = Gravity.CENTER;
    //Отрисовывем слой
    windowManager.addView(this.ll, backgroundParams);
    myService = new MyService();
  }
  public void offCurtain() {
    windowManager.removeView(this.ll);
    ll.findViewById(R.id.ld).setAlpha((float) (0.0));
  }

  /**
   * Слой управляет прозрачностью слоя через seekbar.
   *
   * @param black_curtrain_seekbar
   */
  public void onBrig3(SeekBar black_curtrain_seekbar) {

    black_curtrain_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
        //Устанавливаем значение альфа считывая seekbar
        ll.findViewById(R.id.ld).setAlpha((float) (value / 100.0));
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
