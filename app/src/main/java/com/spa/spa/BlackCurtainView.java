package com.spa.spa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
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
      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
      PixelFormat.TRANSLUCENT);
  private WindowManager.LayoutParams backgroundParams1 = new WindowManager.LayoutParams(
      WindowManager.LayoutParams.WRAP_CONTENT,
      WindowManager.LayoutParams.WRAP_CONTENT,
      WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
      WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
      PixelFormat.TRANSLUCENT);

  private WindowManager windowManager;
  private RelativeLayout ll;
  private RelativeLayout blkseek;
  private MyService myService;
  private SeekBar black_curtrain_seekbar;

  /**
   * Класс создает экземпляры необходим служб и запускает отрисову слоя.
   *
   * @param mcontext mcontext.
   * @param blacint
   */
  @SuppressLint("RtlHardcoded")
  public void onCurtain(Context mcontext, int blacint) {
    //Экземпляр windowManager
    windowManager = (WindowManager) mcontext.getSystemService(WINDOW_SERVICE);
    //Инициализируем слой
    this.ll = (RelativeLayout) ((LayoutInflater) mcontext.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.black_curtain, null, false);
    //Устанавливаем начальное значение альфа прозрачности
    this.ll.findViewById(R.id.ld).setAlpha((float) (blacint));
    backgroundParams.gravity = Gravity.CENTER;

    this.blkseek = (RelativeLayout) ((LayoutInflater) mcontext.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.blk_seek, null, false);
    backgroundParams1.gravity = Gravity.BOTTOM;
    backgroundParams1.gravity = Gravity.RIGHT;

    //Отрисовывем слой
    windowManager.addView(this.ll, backgroundParams);
    windowManager.addView(this.blkseek, backgroundParams1);
    myService = new MyService();
    black_curtrain_seekbar = (SeekBar) blkseek.findViewById(R.id.black_curtrain_seekbar);


  /**
   * Слой управляет прозрачностью слоя через seekbar.
   *
   */


    black_curtrain_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

  {
    @Override
    public void onProgressChanged (SeekBar seekBar,int value, boolean fromUser){
    //Устанавливаем значение альфа считывая seekbar
    ll.findViewById(R.id.ld).setAlpha((float) (value / 100.0));
  }

    @Override
    public void onStartTrackingTouch (SeekBar seekBar){
  }

    @Override
    public void onStopTrackingTouch (SeekBar seekBar){
  }
  });
}

  public void offCurtain() {
    //Проверка если слой существует тогда производим закрытие
    if (ll != null) {
      ll.findViewById(R.id.ld).setAlpha((float) (0.0));
      windowManager.removeView(this.ll);
      windowManager.removeView(blkseek);
    }else {
      //Если слоя нет - ничего не происходит
    }
  }
}
