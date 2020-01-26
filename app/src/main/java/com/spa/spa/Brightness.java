package com.spa.spa;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Brightness extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";

    Context mcontext;
    View overlayView;


    public static void onBrig(final View overlayView, SeekBar seekbar, int Brightnes, final Context mcontext) {

        //Получение текущей яркости экрана.
        //Brightnes = Settings.System.getInt(mcontext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);

        //Настройка текущей яркости экрана для панели поиска;
        seekbar.setProgress(Brightnes);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int Brightnes, boolean b) {
                //Изменение яркости при движении панели поиска.
                Settings.System.putInt(mcontext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, Brightnes);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                SharedPreferences sp = mcontext.getSharedPreferences(APP_PREFERENCES,
                        Context.MODE_PRIVATE);

                int see = seekBar.getProgress();
                String APP_PREFERENCES_NAME = "brig";
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(APP_PREFERENCES_NAME, see);
                editor.apply();
            }
        });
    }


}
