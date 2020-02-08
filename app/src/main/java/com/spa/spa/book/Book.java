package com.spa.spa.book;

import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.R;

public class Book extends AppCompatActivity {
  private WakeLock mWakeLock;
  private Window window;
  //@SuppressLint("InvalidWakeLockTag") // Тег от №1
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);
    getSupportActionBar().hide();

    // №1 Код для отображения активити поверх экрана блокировки
    /*PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
    mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "My Tag");
    mWakeLock.acquire();
    window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
    window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);*/
  }
}
