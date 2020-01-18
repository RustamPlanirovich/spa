package com.spa.spa;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Brightness extends AppCompatActivity {

    public static void onBrig(View overlayView, SeekBar seekbar, int Brightnes, final Context mcontext) {



        //Получение текущей яркости экрана.
        Brightnes = Settings.System.getInt(mcontext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);

        //Настройка текущей яркости экрана для панели поиска;
        seekbar.setProgress(Brightnes);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                //Изменение яркости при движении панели поиска.

                Settings.System.putInt(mcontext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, i
                );
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
