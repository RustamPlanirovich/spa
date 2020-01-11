package com.spa.spa;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import java.io.IOException;

/**
 * Created by VuDuc on 8/7/2017.
 */

public class MyService extends Service {

    WindowManager.LayoutParams params;
    WindowManager.LayoutParams bottomParams;
    WindowManager.LayoutParams backgroundParams;
    Animation inAnimation;
    Animation outAnimation;
    Context mcontext;
    private WindowManager windowManager;
    private View overlayView;
    private View overlayBottom;
    private View overlayBackground;
    ToggleButton toggleButton;
    ToggleButton toggleButton1;
    ToggleButton toggleButton2;
    ToggleButton toggleButton3;
    WifiManager wifiManager;
    Wifiset wifiset;
    NotificationManager mNotificationManager;

    // События обрабатываемые при старте сервиса
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
        initAnimations();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    // Класс отвечает за команды перед обработкой команд управления панелью
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        addOverlayView();
        startForeground();
        return super.onStartCommand(intent, flags, startId);
    }

    // Данный класс, чтобы сервис не умирал, когда закрываем основное окно программы
    private void startForeground() {
        String NOTIF_ID = "1";
        String NOTIF_CHANNEL_ID = "1234";
        NotificationChannel chan = new NotificationChannel(NOTIF_CHANNEL_ID, NOTIF_ID, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIF_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Сервис запущен.")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    // Данный класс, для управления появлением и скрытием панели
    private void addOverlayView() {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.95f);
        params = new WindowManager.LayoutParams(
                width,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER | Gravity.BOTTOM;
        params.x = 0;
        params.y = 20;

        overlayView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view, null);

        //Phần dưới màn hình Нижняя часть экрана
        bottomParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        bottomParams.gravity = Gravity.BOTTOM;
        overlayBottom = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view2, null);

        //Cho phần backgound Для фона
        backgroundParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        backgroundParams.gravity = Gravity.CENTER;
        overlayBackground = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view3, null);

        windowManager.addView(overlayBackground, backgroundParams);
        windowManager.addView(overlayBottom, bottomParams);
        windowManager.addView(overlayView, params);

        overlayView.setOnTouchListener(new View.OnTouchListener() {
            long startTime = System.currentTimeMillis();
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private float startY;

            private Rect rect;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        startY = motionEvent.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        float endY = motionEvent.getY();
                        if (endY > startY && endY - startY > 150) {
                            //Move down
                            overlayView.startAnimation(outAnimation);
                            overlayView.setVisibility(View.GONE);
                            overlayBackground.setVisibility(View.GONE);
                            overlayBottom.setVisibility(View.VISIBLE);
                        }

                        params.y = 20;
                        windowManager.updateViewLayout(overlayView, params);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX - (int) (motionEvent.getRawX() - initialTouchX);
                        params.y = initialY - (int) (motionEvent.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(overlayView, params);
                        break;
                }
                params.x = 0;
                windowManager.updateViewLayout(overlayView, params);
                return false;
            }
        });

        overlayBottom.setOnTouchListener(new View.OnTouchListener() {

            private float starty;

            @SuppressLint("CutPasteId")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        starty = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP: {
                        float endY = motionEvent.getY();
                        if (endY < starty) {
                            //Move up
                            overlayView.startAnimation(inAnimation);
                            overlayView.setVisibility(View.VISIBLE);
                            overlayBackground.setVisibility(View.VISIBLE);
                            overlayBottom.setVisibility(View.GONE);
                            //Включение тактильной вибрации при открытии панели
                            RelativeLayout rl = view.findViewById(R.id.rl);
                            rl.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                            //Инициализация кнопки Wifi
                            toggleButton = (ToggleButton) overlayView.findViewById(R.id.toggleButton);
                            toggleButton1 = (ToggleButton) overlayView.findViewById(R.id.toggleButton1);
                            //Инициализация кнопки Bluetoth
                            toggleButton2 = (ToggleButton) overlayView.findViewById(R.id.toggleButton2);
                            //Инициализация кнопки "Не беспокоить"
                            toggleButton3 = (ToggleButton) overlayView.findViewById(R.id.toggleButton3);
                            //Создаем экземпляр класса Wifiset
                            wifiset = new Wifiset();
                            //Обновляем состояние кнопки "Не беспокоить"
                            Dnd.reDnd(mNotificationManager, toggleButton3);
                            //Обновляем состояние кнопки Wifi
                            wifiset.WifiRe(toggleButton, wifiManager);
                            //Обновляем состояние кнопки Bluetooth
                            Bluetooth.setBluetooth(toggleButton2);
                            Airplane.AirRe(toggleButton1, mcontext);

                            boolean air = Airplane.isAirplaneModeOn(mcontext);
                            int currentState = mNotificationManager.getCurrentInterruptionFilter();
                            String rt = String.valueOf(air);
                            TextView textView = (TextView) overlayView.findViewById(R.id.text);
                            textView.setText(rt);
                        }
                    }
                }
                return false;
            }
        });

        overlayBackground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        overlayView.startAnimation(outAnimation);
                        overlayView.setVisibility(View.GONE);
                        overlayBackground.setVisibility(View.GONE);
                        overlayBottom.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
    }

    // Данный класс отвечает за действия при остановке сервиса
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(overlayBackground);
            wm.removeView(overlayView);
            wm.removeView(overlayBottom);
        }
    }

    // Данный класс отвечает за анимацию появления/скрытия нижней панели
    private void initAnimations() {
        inAnimation = AnimationUtils.loadAnimation(mcontext, R.anim.in_animation);
        outAnimation = AnimationUtils.loadAnimation(mcontext, R.anim.out_animation);
    }

    // Действия при нажатии кнопки включения Wifi
    @SuppressLint("ClickableViewAccessibility")
    public void onToggleClicked(View view) {
        // включена ли кнопка
        boolean on = ((ToggleButton) view).isChecked();
        //Создаем WifiManager
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //Включает Wifi
        if (on) {
            // действия если включена - параметром передаем экземпляр WifiManager
            wifiset.Wifienable(wifiManager);
        } else { //Выключает Wifi - - параметром передаем экземпляр WifiManager
            wifiset.Wifidisable(wifiManager);
        }
    }

    // Действия при нажатии кнопки включения Airplane
    public void onToggleClicked1(View view) throws IOException {
        boolean on = ((ToggleButton) view).isChecked();
        //Включает режим полета
        if (on) {
            boolean rooted = Root.RootUtil.isDeviceRooted();
            if (rooted) {
                Airplane.onAirplayRoot();
            } else {
                Airplane.onAirplayNotRoot(mcontext.getApplicationContext());
            }
        } else {
            boolean rooted = Root.RootUtil.isDeviceRooted();
            if (!rooted) {
                Airplane.offAirplayNotRoot(mcontext.getApplicationContext());
            } else {
                Airplane.offAirplayRoot();
            }
        }
    }


    // Действие при нажатии кнопки включения Bluetooth
    public void onToggleClicked2(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        //Включает Bluetooth
        if (on) {
            Bluetooth.onBluetooth();
        } else {
            Bluetooth.offBluetooth();
        }
    }

    // Действие при нажатии кнопки включения Dnd(Не беспокоить)
    public void onToggleClicked3(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        //Включает режим Не беспокоить
        if (on) {
            Dnd.onDnd(mNotificationManager);
        } else {
            Dnd.offDnd(mNotificationManager);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
