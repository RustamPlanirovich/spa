package com.spa.spa;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class Brightness extends AppCompatActivity {

  /**
   * APP_PREFERENCES.
   */
  public static final String APP_PREFERENCES = "mysettings";

  /**
   * Настройка текущей яркости экрана для панели поиска.
   * @param overlayView
   * @param seekbar seekbar.
   * @param brightnes brightnes.
   * @param mcontext mcontext.
   */
  public static void onBrig(View overlayView, final SeekBar seekbar,
                            final int brightnes, final Context mcontext) {
    /**
     * Настройка текущей яркости экрана для панели поискаю
     */
    seekbar.setProgress(brightnes);
    seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(final SeekBar seekBar,
                                    final int brightnes, final boolean b) {
        //Изменение яркости при движении панели поиска.
        Settings.System.putInt(mcontext.getContentResolver(),
            Settings.System.SCREEN_BRIGHTNESS, brightnes);
      }

      @Override
      public void onStartTrackingTouch(final SeekBar seekBar) {
        //Не используется.
      }

      @Override
      public void onStopTrackingTouch(final SeekBar seekBar) {

        SharedPreferences sp = mcontext.getSharedPreferences(APP_PREFERENCES,
            Context.MODE_PRIVATE);

        int see = seekBar.getProgress();
        String appPreferencesName = "brig";
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(appPreferencesName, see);
        editor.apply();
      }
    });
  }


}
