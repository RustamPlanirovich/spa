package com.spa.spa;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Audio extends AppCompatActivity {

    //Устанавливаем значение громкости если Switch управления громкостью в положении ON
    public static void onBrig1(final Context mcontext, SeekBar seekbar_audio, View overlayView) {


            seekbar_audio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                    AudioManager audioManager = (AudioManager) mcontext.getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

    }

    //Устанавливаем значение громкости если Switch управления громкостью в положении OFF
    public static void onBrig2(final Context mcontext, SeekBar seekbar_audio, View overlayView) {

        seekbar_audio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                AudioManager audioManager = (AudioManager) mcontext.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, value, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //Читаем значение громкости
    public static int getSoudValue(Context mcontext) {
        AudioManager audioManager = (AudioManager) mcontext.getSystemService(Context.AUDIO_SERVICE);
        int z = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return z;
    }

    //Читаем значение громкости
    public static int getSoudValueRing(Context mcontext) {
        AudioManager audioManager = (AudioManager) mcontext.getSystemService(Context.AUDIO_SERVICE);
        int z = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        return z;
    }
}
