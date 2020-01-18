package com.spa.spa;

import android.content.Context;
import android.media.AudioManager;

public class Audio {

    //Устанавливаем значение громкости
    public static void setSoundValue(Context context, int value){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, value, 0);
    }

    //Читаем значение громкости
    public static int getSoudValue(Context context){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(AudioManager.STREAM_RING);
    }
}
