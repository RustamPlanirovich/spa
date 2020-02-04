package com.spa.spa;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Audio extends AppCompatActivity {

  /**
   * Устанавливаем значение громкости если Switch управления громкостью в положении ON.
   *  @param mcontext mcontext.
   * @param seekBar  seekBar.
   * @param overlayView
   */
  public static void onBrig1(final Context mcontext, final SeekBar seekBar, View overlayView) {

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(final SeekBar seekBar, final int value,
                                    final boolean fromUser) {
        AudioManager audioManager = (AudioManager) mcontext
            .getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0);
      }

      @Override
      public void onStartTrackingTouch(final SeekBar seekBar) {
//Не используется.
      }

      @Override
      public void onStopTrackingTouch(final SeekBar seekBar) {
//Не используется.
      }
    });

  }

  /**
   * Устанавливаем значение громкости если Switch управления громкостью в положении OFF.
   *  @param mcontext mcontext.
   * @param seekBar  seekBar.
   * @param overlayView
   */
  public static void onBrig2(final Context mcontext, final SeekBar seekBar, View overlayView) {

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(final SeekBar seekBar, final int value,
                                    final boolean fromUser) {
        AudioManager audioManager = (AudioManager) mcontext
            .getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, value, 0);

      }

      @Override
      public void onStartTrackingTouch(final SeekBar seekBar) {
//Не используется.
      }

      @Override
      public void onStopTrackingTouch(final SeekBar seekBar) {
//Не используется.
      }
    });

  }

  /**
   * Читаем значение громкости.
   *
   * @param mcontext mcontext.
   * @return z.
   */
  public static int getSoudValue(final Context mcontext) {
    AudioManager audioManager = (AudioManager) mcontext
        .getSystemService(Context.AUDIO_SERVICE);
    int z = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    return z;
  }

  /**
   * Читаем значение громкости.
   *
   * @param mcontext mcontext.
   * @return z.
   */
  public static int getSoudValueRing(final Context mcontext) {
    AudioManager audioManager = (AudioManager) mcontext
        .getSystemService(Context.AUDIO_SERVICE);
    int z = audioManager.getStreamVolume(AudioManager.STREAM_RING);
    return z;
  }

}
