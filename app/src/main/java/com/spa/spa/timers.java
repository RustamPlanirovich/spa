package com.spa.spa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Locale;

public class timers {
  CountDownTimer timer;
  CountDownTimer timer10;
  CountDownTimer timer15;
  CountDownTimer timer20;
  CountDownTimer timer25;
  private MediaPlayer mediaPlayer;
  private Thread thread;
  private Thread thread10;
  private Thread thread15;
  private Thread thread20;
  private Thread thread25;
  private int pausedMillis;
  private static final String TAG = "MyApp";
  private Animation animation;

  public void onStartTimer_5() {
    timer.start();
  }

  public void onStartTimer_10() {
    timer10.start();
  }

  public void onStartTimer_15() {
    timer15.start();
  }

  public void onStartTimer_20() {
    timer20.start();
  }

  public void onStartTimer_25() {
    timer25.start();
  }

  public void onTimer_5(TextView textTimer, Context mcontext, Vibrator vibrator) {
    thread = new Thread(thread);
    thread.start();
    Log.i(TAG, String.valueOf(thread.getState()));
    long[] pattern = {0, 2000, 1000, 2000};
    mediaPlayer = new MediaPlayer();
    // 5 минут = 300 секунд = 300000 миллисекунд
    timer = new CountDownTimer(300000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        int five = (int) (millisUntilFinished / 60000);
        int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
        pausedMillis = (int) millisUntilFinished;
        Log.i("Array Value", "Yess" + " " + Thread.currentThread().getName());
        String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
        textTimer.setText(timeletfFormated);
        Log.i(TAG, timeletfFormated);
      }

      @Override
      public void onFinish() {
        mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        vibrator.vibrate(pattern, 1);

      }
    };
  }

  public void offTimer_5(TextView textTimer, Vibrator vibrator) {
    timer.cancel();
    vibrator.cancel();
    textTimer.setText("00:00");
    if (mediaPlayer.isPlaying()) {
      mediaPlayer.stop();
    }
    thread.interrupt();
    Log.i(TAG, String.valueOf(thread.getState()));
    textTimer.setTextColor(Color.WHITE);
    textTimer.setTypeface(Typeface.DEFAULT);
    textTimer.setTextSize(36);
  }

  public void onTimer_10(TextView textTimer, Context mcontext, Vibrator vibrator) {
    thread10 = new Thread(thread10);
    thread10.start();
    Log.i(TAG, String.valueOf(thread10.getState()));
    long[] pattern = {0, 2000, 1000, 2000};
    mediaPlayer = new MediaPlayer();
    // 5 минут = 300 секунд = 300000 миллисекунд
    timer10 = new CountDownTimer(600000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        int five = (int) (millisUntilFinished / 60000);
        int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
        pausedMillis = (int) millisUntilFinished;
        String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
        textTimer.setText(timeletfFormated);
        Log.i(TAG, timeletfFormated);
      }

      @Override
      public void onFinish() {
        mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        vibrator.vibrate(pattern, 1);

      }
    };
  }

  public void offTimer_10(TextView textTimer, Vibrator vibrator) {
    timer10.cancel();
    vibrator.cancel();
    textTimer.setText("00:00");
    if (mediaPlayer.isPlaying()) {
      mediaPlayer.stop();
    }
    thread10.interrupt();
    Log.i(TAG, String.valueOf(thread10.getState()));
    textTimer.setTextColor(Color.WHITE);
    textTimer.setTypeface(Typeface.DEFAULT);
    textTimer.setTextSize(36);
  }

  public void onTimer_15(TextView textTimer, Context mcontext, Vibrator vibrator) {
    thread15 = new Thread(thread15);
    thread15.start();
    Log.i(TAG, String.valueOf(thread15.getState()));
    long[] pattern = {0, 2000, 1000, 2000};
    mediaPlayer = new MediaPlayer();
    // 5 минут = 300 секунд = 300000 миллисекунд
    timer15 = new CountDownTimer(900000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        int five = (int) (millisUntilFinished / 60000);
        int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
        pausedMillis = (int) millisUntilFinished;
        String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
        textTimer.setText(timeletfFormated);
        Log.i(TAG, timeletfFormated);
      }

      @Override
      public void onFinish() {
        mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        vibrator.vibrate(pattern, 1);

      }
    };
  }

  public void offTimer_15(TextView textTimer, Vibrator vibrator) {
    timer15.cancel();
    vibrator.cancel();
    textTimer.setText("00:00");
    if (mediaPlayer.isPlaying()) {
      mediaPlayer.stop();
    }
    thread15.interrupt();
    Log.i(TAG, String.valueOf(thread15.getState()));
    textTimer.setTextColor(Color.WHITE);
    textTimer.setTypeface(Typeface.DEFAULT);
    textTimer.setTextSize(36);
  }

  public void onTimer_20(TextView textTimer, Context mcontext, Vibrator vibrator) {
    thread20 = new Thread(thread20);
    thread20.start();
    Log.i(TAG, String.valueOf(thread20.getState()));
    long[] pattern = {0, 2000, 1000, 2000};
    mediaPlayer = new MediaPlayer();
    // 5 минут = 300 секунд = 300000 миллисекунд
    timer20 = new CountDownTimer(1200000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        int five = (int) (millisUntilFinished / 60000);
        int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
        pausedMillis = (int) millisUntilFinished;
        String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
        textTimer.setText(timeletfFormated);
        Log.i(TAG, timeletfFormated);
      }

      @Override
      public void onFinish() {
        mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        vibrator.vibrate(pattern, 1);

      }
    };
  }

  public void offTimer_20(TextView textTimer, Vibrator vibrator) {
    timer20.cancel();
    vibrator.cancel();
    textTimer.setText("00:00");
    if (mediaPlayer.isPlaying()) {
      mediaPlayer.stop();
    }
    thread20.interrupt();
    Log.i(TAG, String.valueOf(thread20.getState()));
    textTimer.setTextColor(Color.WHITE);
    textTimer.setTypeface(Typeface.DEFAULT);
    textTimer.setTextSize(36);
  }

  public void onTimer_25(TextView textTimer, Context mcontext, Vibrator vibrator) {
    thread25 = new Thread(thread25);
    thread25.start();
    Log.i(TAG, String.valueOf(thread25.getState()));
    long[] pattern = {0, 2000, 1000, 2000};
    mediaPlayer = new MediaPlayer();
    // 5 минут = 300 секунд = 300000 миллисекунд
    timer25 = new CountDownTimer(1500000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        int five = (int) (millisUntilFinished / 60000);
        int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
        pausedMillis = (int) millisUntilFinished;
        String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
        textTimer.setText(timeletfFormated);
        Log.i(TAG, timeletfFormated);
      }

      @Override
      public void onFinish() {
        mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        Log.i(TAG, String.valueOf(mediaPlayer.isPlaying()));

        vibrator.vibrate(pattern, 1);

      }
    };
  }

  public void offTimer_25(TextView textTimer, Vibrator vibrator, TextView dateView) {
    timer25.cancel();
    vibrator.cancel();
    textTimer.setText("00:00");
    if (mediaPlayer.isPlaying()) {
      mediaPlayer.stop();
    }
    thread25.interrupt();
    Log.i(TAG, String.valueOf(thread25.getState()));
    textTimer.setTextColor(Color.WHITE);
    textTimer.setTypeface(Typeface.DEFAULT);
    textTimer.setTextSize(36);
  }

  public void setTimer5(boolean tick, TextView textTimer, Context mcontext, Vibrator vibrator) {
    if (tick) {
      timer.cancel();
      Log.i(TAG, String.valueOf(pausedMillis));
      animation = AnimationUtils.loadAnimation(mcontext, R.anim.blink);
      textTimer.startAnimation(animation);
      textTimer.setTextColor(Color.RED);
      textTimer.setTypeface(textTimer.getTypeface(), Typeface.BOLD);
      textTimer.setTextSize(40);
    } else {
      textTimer.clearAnimation();
      textTimer.setTextColor(Color.WHITE);
      textTimer.setTypeface(Typeface.DEFAULT);
      textTimer.setTextSize(36);
      long[] pattern = {0, 2000, 1000, 2000};
      mediaPlayer = new MediaPlayer();
      // 5 минут = 300 секунд = 300000 миллисекунд
      timer = new CountDownTimer(pausedMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
          int five = (int) (millisUntilFinished / 60000);
          int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
          pausedMillis = (int) millisUntilFinished;
          String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
          textTimer.setText(timeletfFormated);
          Log.i(TAG, timeletfFormated);
        }

        @Override
        public void onFinish() {
          mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
          mediaPlayer.start();
          vibrator.vibrate(pattern, 1);
        }
      }.start();
    }
  }

  public void setTimer10(boolean tick, TextView textTimer, Context mcontext, Vibrator vibrator) {
    if (tick) {
      timer10.cancel();
      Log.i(TAG, String.valueOf(pausedMillis));
      animation = AnimationUtils.loadAnimation(mcontext, R.anim.blink);
      textTimer.startAnimation(animation);
      textTimer.setTextColor(Color.RED);
      textTimer.setTypeface(textTimer.getTypeface(), Typeface.BOLD);
      textTimer.setTextSize(40);
    } else {
      textTimer.clearAnimation();
      textTimer.setTextColor(Color.WHITE);
      textTimer.setTypeface(Typeface.DEFAULT);
      textTimer.setTextSize(36);
      long[] pattern = {0, 2000, 1000, 2000};
      mediaPlayer = new MediaPlayer();
      // 5 минут = 300 секунд = 300000 миллисекунд
      timer10 = new CountDownTimer(pausedMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
          int five = (int) (millisUntilFinished / 60000);
          int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
          pausedMillis = (int) millisUntilFinished;
          String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
          textTimer.setText(timeletfFormated);
          Log.i(TAG, timeletfFormated);
        }

        @Override
        public void onFinish() {
          mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
          mediaPlayer.start();
          vibrator.vibrate(pattern, 1);
        }
      }.start();
    }
  }

  public void setTimer15(boolean tick, TextView textTimer, Context mcontext, Vibrator vibrator) {
    if (tick) {
      timer15.cancel();
      Log.i(TAG, String.valueOf(pausedMillis));
      animation = AnimationUtils.loadAnimation(mcontext, R.anim.blink);
      textTimer.startAnimation(animation);
      textTimer.setTextColor(Color.RED);
      textTimer.setTypeface(textTimer.getTypeface(), Typeface.BOLD);
      textTimer.setTextSize(40);
    } else {
      textTimer.clearAnimation();
      textTimer.setTextColor(Color.WHITE);
      textTimer.setTypeface(Typeface.DEFAULT);
      textTimer.setTextSize(36);
      long[] pattern = {0, 2000, 1000, 2000};
      mediaPlayer = new MediaPlayer();
      // 5 минут = 300 секунд = 300000 миллисекунд
      timer15 = new CountDownTimer(pausedMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
          int five = (int) (millisUntilFinished / 60000);
          int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
          pausedMillis = (int) millisUntilFinished;
          String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
          textTimer.setText(timeletfFormated);
          Log.i(TAG, timeletfFormated);
        }

        @Override
        public void onFinish() {
          mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
          mediaPlayer.start();
          vibrator.vibrate(pattern, 1);
        }
      }.start();
    }
  }

  public void setTimer20(boolean tick, TextView textTimer, Context mcontext, Vibrator vibrator) {
    if (tick) {
      timer20.cancel();
      Log.i(TAG, String.valueOf(pausedMillis));
      animation = AnimationUtils.loadAnimation(mcontext, R.anim.blink);
      textTimer.startAnimation(animation);
      textTimer.setTextColor(Color.RED);
      textTimer.setTypeface(textTimer.getTypeface(), Typeface.BOLD);
      textTimer.setTextSize(40);
    } else {
      textTimer.clearAnimation();
      textTimer.setTextColor(Color.WHITE);
      textTimer.setTypeface(Typeface.DEFAULT);
      textTimer.setTextSize(36);
      long[] pattern = {0, 2000, 1000, 2000};
      mediaPlayer = new MediaPlayer();
      // 5 минут = 300 секунд = 300000 миллисекунд
      timer20 = new CountDownTimer(pausedMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
          int five = (int) (millisUntilFinished / 60000);
          int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
          pausedMillis = (int) millisUntilFinished;
          String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
          textTimer.setText(timeletfFormated);
          Log.i(TAG, timeletfFormated);
        }

        @Override
        public void onFinish() {
          mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
          mediaPlayer.start();
          vibrator.vibrate(pattern, 1);
        }
      }.start();
    }
  }

  public void setTimer25(boolean tick, TextView textTimer, Context mcontext, Vibrator vibrator) {
    if (tick) {
      timer25.cancel();
      Log.i(TAG, String.valueOf(pausedMillis));
      animation = AnimationUtils.loadAnimation(mcontext, R.anim.blink);
      textTimer.startAnimation(animation);
      textTimer.setTextColor(Color.RED);
      textTimer.setTypeface(textTimer.getTypeface(), Typeface.BOLD);
      textTimer.setTextSize(40);
    } else {
      textTimer.clearAnimation();
      textTimer.setTextColor(Color.WHITE);
      textTimer.setTypeface(Typeface.DEFAULT);
      textTimer.setTextSize(36);
      long[] pattern = {0, 2000, 1000, 2000};
      mediaPlayer = new MediaPlayer();
      // 5 минут = 300 секунд = 300000 миллисекунд
      timer25 = new CountDownTimer(pausedMillis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
          int five = (int) (millisUntilFinished / 60000);
          int fiveSec = (int) (millisUntilFinished - (five * 60000)) / 1000;
          pausedMillis = (int) millisUntilFinished;
          String timeletfFormated = String.format(Locale.getDefault(), "%02d:%02d", five, fiveSec);
          textTimer.setText(timeletfFormated);
          Log.i(TAG, timeletfFormated);
        }

        @Override
        public void onFinish() {
          mediaPlayer = MediaPlayer.create(mcontext, R.raw.circuit);
          mediaPlayer.start();
          vibrator.vibrate(pattern, 1);
        }
      }.start();
    }
  }
}
