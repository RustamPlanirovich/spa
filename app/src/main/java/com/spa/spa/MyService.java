package com.spa.spa;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Handler;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import java.io.IOException;


public class MyService extends Service implements View.OnClickListener {
  /**
   * Инициализация метода WindowManager.
   */
  private WindowManager.LayoutParams params;
  /**
   * Инициализация метода WindowManager.
   */
  private WindowManager.LayoutParams bottomParams;
  /**
   * Инициализация метода WindowManager.
   */
  private WindowManager.LayoutParams backgroundParams;
  /**
   * Инициализация метода Animation.
   */
  private Animation inAnimation;
  /**
   * Инициализация метода Animation.
   */
  private Animation outAnimation;
  /**
   * Инициализация метода Context.
   */
  private Context mcontext;
  /**
   * Инициализация класса PhoneUnlockedReceiver.
   */
  private PhoneUnlockedReceiver receiver;
  /**
   * Объявляем WindowManage.
   */
  private WindowManager windowManager;
  /**
   * Объявляем overlayView.
   */
  private static View overlayView;
  /**
   * Объявляем overlayBottom.
   */
  private static View overlayBottom;
  /**
   * Объявляем overlayBackground.
   */
  private static View overlayBackground;
  /**
   * Объявляем wifiManager.
   */
  private WifiManager wifiManager;
  /**
   * Объявляем wifiset.
   */
  private Wifiset wifiset;
  /**
   * Объявляем dnd.
   */
  private Dnd dnd;
  /**
   * Объявляем bluetooth.
   */
  private Bluetooth bluetoothClass;
  /**
   * Объявляем mobileDate.
   */
  private mobileDate mobileDate;
  /**
   * Объявляем flash.
   */
  private Flash flash;
  /**
   * Объявляем notificationManager.
   */
  private NotificationManager mnotificationManager;
  /**
   * Объявляем notes.
   */
  private Button notes;
  /**
   * Объявляем book.
   */
  private Button book;
  /**
   * Объявляем date.
   */
  private Button date;
  /**
   * Объявляем plans.
   */
  private Button plans;
  /**
   * Объявляем voice.
   */
  private Button voice;
  /**
   * Объявляем setting.
   */
  private Button settingActivit;
  /**
   * Объявляем converter.
   */
  private Button converter;
  /**
   * Объявляем links.
   */
  private Button links;
  /**
   * Объявляем todo.
   */
  private Button todo;
  /**
   * Объявляем shedule.
   */
  private Button shedule;
  /**
   * Объявляем costs.
   */
  private Button costs;
  /**
   * Объявляем incom.
   */
  private Button incom;
  /**
   * Объявляем wifi.
   */
  private ToggleButton wifi;
  /**
   * Объявляем airPlane.
   */
  private ToggleButton airPlane;
  /**
   * Объявляем bluetooth.
   */
  private ToggleButton bluetooth;
  /**
   * Объявляем dnD
   */
  private ToggleButton dnD;
  /**
   * Объявляем mobileDate.
   */
  private ToggleButton mobileData;
  /**
   * Объявляем autoOrientation.
   */
  private ToggleButton autoOrientation;
  /**
   * Объявляем settingActivity.
   */
  private Button settActivit;
  /**
   * Объявляем flash.
   */
  private ToggleButton flashh;
  /**
   * Объявляем CameraManager.
   */
  CameraManager mmCameraManager;
  /**
   * Объявляем BlackCurtainView.
   */
  private BlackCurtainView blackCurtain;
  /**
   * Объявляем SeekBar.
   */
  private SeekBar black_curtrain_seekbar;
  /**
   * Объявляем ToggleButton.
   */
  private ToggleButton blackMode;
  /**
   * Объявляем TextView уровень заряда батареи
   */
  private TextView textView;
  /**
   * Объявляем TextView текущее время
   */
  private TextView time;
  private timeanddate timeanddate;


  // События обрабатываемые при старте сервиса
  @SuppressLint("InvalidWakeLockTag") // Тег от №1
  @Override
  public void onCreate() {
    super.onCreate();
    mcontext = this;
    initAnimations();
    wifiManager = (WifiManager) getApplicationContext()
        .getSystemService(Context.WIFI_SERVICE);
    mnotificationManager = (NotificationManager) getSystemService(
        Context.NOTIFICATION_SERVICE);
    mmCameraManager = (CameraManager) mcontext
        .getSystemService(CAMERA_SERVICE);
    this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  }

  // Класс отвечает за команды перед обработкой команд управления панелью
  @Override
  public int onStartCommand(final Intent intent,
                            final int flags, final int startId) {
    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    addOverlayView(MotionEvent.ACTION_UP);
    startForeground();
    //Слушатель блокировки - разблокировки экрана
    receiver = new PhoneUnlockedReceiver();
    IntentFilter filter = new IntentFilter();
    //Действие выполняется если экран разблокирован
    filter.addAction(Intent.ACTION_USER_PRESENT);
    //Действие выполняется если экран заблокирован
    filter.addAction(Intent.ACTION_SCREEN_OFF);
    registerReceiver(receiver, filter);
    wifiset = new Wifiset();
    dnd = new Dnd();
    bluetoothClass = new Bluetooth();
    mobileDate = new mobileDate();
    flash = new Flash();
    blackCurtain = new BlackCurtainView();
    textView = (TextView) overlayView.findViewById(R.id.textView2);
    time = (TextView) overlayView.findViewById(R.id.timme);
    timeanddate = new timeanddate();
    //Инициализация кнопок управления основной системой
    notes = (Button) overlayView.findViewById(R.id.notesActivity);
    book = (Button) overlayView.findViewById(R.id.bookActivity);
    date = (Button) overlayView.findViewById(R.id.dateActivity);
    plans = (Button) overlayView.findViewById(R.id.plansActivity);
    voice = (Button) overlayView.findViewById(R.id.voiceActivity);
    settingActivit = (Button) overlayView.findViewById(R.id.settingActivit);
    converter = (Button) overlayView.findViewById(R.id.converterActivity);
    links = (Button) overlayView.findViewById(R.id.linksActivity);
    todo = (Button) overlayView.findViewById(R.id.todoActivity);
    shedule = (Button) overlayView.findViewById(R.id.sheduleActivity);
    costs = (Button) overlayView.findViewById(R.id.costsActivity);
    incom = (Button) overlayView.findViewById(R.id.incomActivity);
    wifi = (ToggleButton) overlayView.findViewById(R.id.wifi);
    airPlane = (ToggleButton) overlayView.findViewById(R.id.airPlane);
    bluetooth = (ToggleButton) overlayView.findViewById(R.id.bluetooth);
    dnD = (ToggleButton) overlayView.findViewById(R.id.dnD);
    mobileData = (ToggleButton) overlayView.findViewById(R.id.mobileData);
    autoOrientation = (ToggleButton) overlayView.findViewById(R.id.autoOrientation);
    settActivit = (Button) overlayView.findViewById(R.id.settActivit);
    flashh = (ToggleButton) overlayView.findViewById(R.id.flashh);
    blackMode = (ToggleButton) overlayView.findViewById(R.id.blackMode);
    //Вешаем на все эти кнопки слушателя клика
    notes.setOnClickListener(this);
    book.setOnClickListener(this);
    date.setOnClickListener(this);
    plans.setOnClickListener(this);
    voice.setOnClickListener(this);
    settingActivit.setOnClickListener(this);
    converter.setOnClickListener(this);
    links.setOnClickListener(this);
    todo.setOnClickListener(this);
    shedule.setOnClickListener(this);
    costs.setOnClickListener(this);
    incom.setOnClickListener(this);
    wifi.setOnClickListener(this);
    airPlane.setOnClickListener(this);
    bluetooth.setOnClickListener(this);
    dnD.setOnClickListener(this);
    mobileData.setOnClickListener(this);
    autoOrientation.setOnClickListener(this);
    settActivit.setOnClickListener(this);
    flashh.setOnClickListener(this);
    blackMode.setOnClickListener(this);

    return super.onStartCommand(intent, flags, startId);
  }

  /**
   * Данный класс, чтобы сервис не умирал, когда закрываем основное окно программы.
   */
  private void startForeground() {

    Intent intentHide = new Intent(this, StopServiceReceiver.class);
    PendingIntent hide = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intentHide, PendingIntent.FLAG_CANCEL_CURRENT);
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    String CHANNEL_ID = "MCDANIEL";
    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_LOW);
    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
    Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
        .setContentText("Нажмите здесь, чтобы перейти к приложению")
        .setContentTitle("Сервис запущен")
        .setContentIntent(pendingIntent)
        .addAction(R.drawable.ic_back, "Открыть", pendingIntent)
        .addAction(R.drawable.ic_back, "Закрыть", hide)
        .setChannelId(CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .build();
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.createNotificationChannel(notificationChannel);
    notificationManager.notify(1, notification);

  }

  /**
   * Данный класс, для управления появлением и скрытием панели.
   *
   * @param actionUp действие нажатия.
   */
  @SuppressLint("ClickableViewAccessibility")
  public void addOverlayView(final int actionUp) {
    DisplayMetrics metrics = this.getResources().getDisplayMetrics();
    final float nine = 0.95f;
    int width = (int) (metrics.widthPixels * nine);
    params = new WindowManager.LayoutParams(
        width,
        //Изменение высоты самой панели
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);

    params.gravity = Gravity.CENTER | Gravity.BOTTOM;
    final int para = 0;
    final int par = 0;
    params.x = para;
    params.y = par;

    overlayView = ((LayoutInflater) getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view, null);

    //Нижняя часть экрана
    bottomParams = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
    bottomParams.gravity = Gravity.BOTTOM;
    overlayBottom = ((LayoutInflater) getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.floating_view2, null);

    //Cho phần backgound Для фона
    backgroundParams = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
    backgroundParams.gravity = Gravity.CENTER;
    overlayBackground = ((LayoutInflater) getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(
        R.layout.floating_view3, null);

    windowManager.addView(overlayBackground, backgroundParams);
    Handler handler = new Handler();
    handler.postDelayed(() -> windowManager.addView(overlayBottom, bottomParams), 300);
    windowManager.addView(overlayView, params);

    overlayView.setOnTouchListener(new View.OnTouchListener() {
      private long startTime = System.currentTimeMillis();
      private int initialX;
      private int initialY;
      private float initialTouchX;
      private float initialTouchY;
      private float startY;


      private Rect rect;


      @Override
      public boolean onTouch(final View view, final MotionEvent motionEvent) {

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
          default:
            throw new IllegalStateException(
                "Unexpected value: " + motionEvent.getAction());
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
      public boolean onTouch(final View view, final MotionEvent motionEvent) {
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
              //Обновление состояния wifi
              wifiset.wifiRe(wifi, wifiManager);
              //Обновление состояния DND
              dnd.reDnd(mnotificationManager, dnD);
              //Обновление состояния bluetooth
              bluetoothClass.setBluetooth(bluetooth);
              //Обновление состояния mobileData
              mobileDate.reData(mcontext, mobileData);
              turnOnFlashLight();
              //Включение тактильной вибрации при открытии панели
              RelativeLayout rl = view.findViewById(R.id.rl);
              rl.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
              //Начальное значение ползунка яркости
              int brightnes = Settings.System.getInt(
                  mcontext.getContentResolver(),
                  Settings.System.SCREEN_BRIGHTNESS, 0);
              //Инициализация ползунка яркости
              SeekBar seekbar = (SeekBar)
                  overlayView.findViewById(R.id.seebarBrightness);
              //Инициализация Switch управления автояркостью
              Switch imgAutoBright = (Switch)
                  overlayView.findViewById(R.id.imgAutoBright);
              // Передача в управляющий класс.
              // управления яркостью необходимых параметров.
              Brightness.onBrig(overlayView, seekbar, brightnes, mcontext);
              //Инициализируем ползунок громкости звонка
              SeekBar seekbarAudio = (SeekBar)
                  overlayView.findViewById(R.id.audioSeekBar);
              //Инициализация Switch управления громкостью
              Switch switch1 = (Switch) overlayView.findViewById(R.id.notificationSwitch);
              //Чтения значения Switch управления громкостью
              boolean onn = switch1.isChecked();
              if (onn) {
                //Значение ползунка после изменения системно
                int val = Audio.getSoudValue(mcontext);
                //Изменение положения ползунка
                seekbarAudio.setProgress(val);
                Audio.onBrig1(mcontext, seekbarAudio, overlayView);

              } else {
                //Значение ползунка после изменения системно
                int val = Audio.getSoudValueRing(mcontext);
                //Изменение положения ползунка
                seekbarAudio.setProgress(val);
                Audio.onBrig2(mcontext, seekbarAudio, overlayView);
              }
              //При вызове панели проверяе текущее значение автоповорота
              Orientation.reAutoOrientation(mcontext, autoOrientation);
              //Создаем экземпляр black_curtrain_seekbar
              black_curtrain_seekbar = (SeekBar) overlayView.findViewById(R.id.black_curtrain_seekbar);
              //Вызваем класс включения затемнения и передаем ему экземпляр black_curtrain_seekbar
              blackCurtain.onBrig3(black_curtrain_seekbar);
            }
            //Проверяем включенность кнопки
            boolean onBlack = ((blackMode).isChecked());
            if (onBlack) {
              //Если включена - делаем полосу настройки затемненности доступной
              black_curtrain_seekbar.setEnabled(true);
            } else {
              //Если нет - делаем не доступной
              black_curtrain_seekbar.setEnabled(false);
            }
            timeanddate.timeAndDate(time);
          }
          break;
        }
        return false;
      }
    });
    overlayBackground.setOnTouchListener((view, motionEvent) -> {
      switch (motionEvent.getAction()) {
        case MotionEvent.ACTION_DOWN:
          overlayView.startAnimation(outAnimation);
          overlayView.setVisibility(View.GONE);
          overlayBackground.setVisibility(View.GONE);
          overlayBottom.setVisibility(View.VISIBLE);
          break;
      }
      return false;
    });

  }

  /**
   * Метод сворачивающий панель при блокировке экрана.
   */
  public void onLock() {
    MyService.overlayView.setVisibility(View.GONE);
    MyService.overlayBackground.setVisibility(View.GONE);
    MyService.overlayBottom.setVisibility(View.VISIBLE);
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

  /**
   * Данный класс отвечает за анимацию появления/скрытия нижней панели.
   */
  private void initAnimations() {
    inAnimation = AnimationUtils.loadAnimation(mcontext, R.anim.in_animation);
    outAnimation = AnimationUtils.loadAnimation(mcontext, R.anim.out_animation);
  }

  /**
   * Дейтвие при нажатии Switch автояркость.
   *
   * @param view v.
   */
  public void onSwitchClicked4(final View view) {
    boolean on = ((Switch) view).isChecked();
    final ContentResolver resolver = getContentResolver();
    int mode1 = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    int mode2 = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;

    if (on) {
      //Включаем автояркость
      Settings.System.putInt(resolver,
          Settings.System.SCREEN_BRIGHTNESS_MODE, mode1);
    } else {
      //Выключаем автояркость
      Settings.System.putInt(resolver,
          Settings.System.SCREEN_BRIGHTNESS_MODE, mode2);
    }
  }

  /**
   * Действия при нажатии Switch управлеия громкостью.
   *
   * @param view v.
   */
  public void onSwitchClicked5(final View view) {
    boolean on = ((Switch) view).isChecked();
    //Инициализируем ползунок громкости звонка
    SeekBar seekbarAudio = (SeekBar) overlayView.findViewById(R.id.audioSeekBar);

    if (on) {
      //Значение ползунка после измнения пользователем
      int value = 0;
      //Вызываем управляющий класс Audio и передача параметров
      Audio.onBrig1(mcontext, seekbarAudio, overlayView);

      //Значение ползунка после изменения системно
      int val = Audio.getSoudValue(mcontext);
      //Изменение положения ползунка
      seekbarAudio.setProgress(val);

    } else {
      //Значение ползунка после измнения пользователем
      int value = 0;
      //Вызываем управляющий класс Audio и передача параметров
      Audio.onBrig2(mcontext, seekbarAudio, overlayView);

      //Значение ползунка после изменения системно
      int val = Audio.getSoudValueRing(mcontext);
      //Изменение положения ползунка
      seekbarAudio.setProgress(val);

//            //Установка иконки на SeekBar
//            int[] foren = {
//                    R.drawable.ic_notifications_1
//            };
//            Drawable z = mcontext.getDrawable(foren[0]);
//
//            seekbarAudio.setForeground(z);
    }
  }

  /**
   * Обработчик нажатий для запуска активити основной системы.
   *
   * @param v v.
   */
  public void onClick(final View v) {
    switch (v.getId()) {
      case R.id.notesActivity:
        OpenActivity.notess(mcontext);
        onLock();
        break;
      case R.id.bookActivity:
        OpenActivity.bookk(mcontext);
        onLock();
        break;
      case R.id.dateActivity:
        OpenActivity.datee(mcontext);
        onLock();
        break;
      case R.id.plansActivity:
        OpenActivity.planss(mcontext);
        onLock();
        break;
      case R.id.voiceActivity:
        OpenActivity.voicee(mcontext);
        onLock();
        break;
      case R.id.settingActivit:
        OpenActivity.settingg(mcontext);
        onLock();
        break;
      case R.id.converterActivity:
        OpenActivity.currencyy(mcontext);
        onLock();
        break;
      case R.id.linksActivity:
        OpenActivity.linkss(mcontext);
        onLock();
        break;
      case R.id.todoActivity:
        OpenActivity.todoo(mcontext);
        onLock();
        break;
      case R.id.sheduleActivity:
        OpenActivity.shedulee(mcontext);
        onLock();
        break;
      case R.id.costsActivity:
        OpenActivity.costss(mcontext);
        onLock();
        break;
      case R.id.incomActivity:
        OpenActivity.incomm(mcontext);
        onLock();
        break;
      case R.id.wifi:
        boolean onWifi = ((wifi).isChecked());
        //Создаем WifiManager
        wifiManager = (WifiManager) getApplicationContext()
            .getSystemService(Context.WIFI_SERVICE);
        //Включает Wifi
        if (onWifi) {
          // действия если включена - параметром передаем экземпляр WifiManager
          wifiset.wifienable(wifiManager);
        } else { //Выключает Wifi - - параметром передаем экземпляр WifiManager
          wifiset.wifidisable(wifiManager);
        }
        break;
      case R.id.airPlane:
        boolean onAirPlane = ((airPlane).isChecked());
        //Включает режим полета
        if (onAirPlane) {
          boolean rooted = Root.RootUtil.isDeviceRooted();
          if (rooted) {
            try {
              Airplane.onAirplayRoot();
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            Airplane.onAirplayNotRoot(mcontext.getApplicationContext());
          }
        } else {
          boolean rooted = Root.RootUtil.isDeviceRooted();
          if (!rooted) {
            Airplane.offAirplayNotRoot(mcontext.getApplicationContext());
          } else {
            try {
              Airplane.offAirplayRoot();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        break;
      case R.id.bluetooth:
        boolean onBluetooth = ((bluetooth).isChecked());
        //Включает Bluetooth.
        if (onBluetooth) {
          Bluetooth.onBluetooth();
        } else {
          Bluetooth.offBluetooth();
        }
        break;
      case R.id.dnD:
        boolean onDnd = ((dnD).isChecked());
        //Включает режим Не беспокоить
        if (onDnd) {
          Dnd.onDnd(mnotificationManager);
        } else {
          Dnd.offDnd(mnotificationManager);
        }
        break;
      case R.id.mobileData:
        boolean onMobileData = ((mobileData).isChecked());
        //Включает Mobile Data
        if (onMobileData) {
          mobileDate.onData(mcontext.getApplicationContext());
        } else {
          mobileDate.offData(mcontext.getApplicationContext());
        }
        break;
      case R.id.autoOrientation:
        boolean onAutoOrientation = ((autoOrientation).isChecked());
        if (onAutoOrientation) {
          //Включение автоповорота
          Orientation.onAutoOrientation(mcontext);
        } else {
          //Выключение автоповорота
          Orientation.offAutoOrientation(mcontext);
        }
        break;
      case R.id.settActivit:
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
        onLock();
        break;
      case R.id.flashh:
        boolean onFlashh = ((flashh).isChecked());
        flash = new Flash();
        flash.flashEnable(mcontext);
        if (onFlashh) {
          //Включение фонарика
          flash.switchFlashLight(mcontext, onFlashh);
        } else {
          //Выключение фонарика
          flash.switchFlashLight(mcontext, onFlashh);
        }
        break;
      case R.id.blackMode:
        boolean onBlack = ((blackMode).isChecked());
        int blacint = black_curtrain_seekbar.getProgress();
        if (onBlack) {
          //Включаем затемнение экрана
          blackCurtain.onCurtain(mcontext, blacint);
          //Делаем доступной полосу настройки затемненности
          black_curtrain_seekbar.setEnabled(true);
        } else {
          //Отключаем затемнение экрана
          blackCurtain.offCurtain();
          //Делаем не доступной полосу настройки затемненности
          black_curtrain_seekbar.setEnabled(false);
          //Устанавливаем значение полосы затемнения на 0
          black_curtrain_seekbar.setProgress(0);
        }
    }
  }

  @Override
  public IBinder onBind(final Intent intent) {
    return null;
  }

  /**
   * Проверяем состояние фонарика и исходя из этого переключаем кнопку
   */
  private final CameraManager.TorchCallback enableTorchCallback = new CameraManager.TorchCallback() {
    @Override
    public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
      super.onTorchModeChanged(cameraId, enabled);
      boolean b = enabled;
      if (b) {
        flashh.setChecked(true);
      } else {
        flashh.setChecked(false);
      }
    }
  };

  /**
   * Регистрируем колбек для проверки состояния фонарика
   *
   * @param torchCallback
   */
  private void fireOnTorchModeChanged(CameraManager.TorchCallback torchCallback) {
    mmCameraManager.registerTorchCallback(torchCallback, null);
    //mCameraManager.unregisterTorchCallback(torchCallback);
  }

  /**
   * Вызываем класс регистрации коллбека для проверки состояния камеры
   */
  private void turnOnFlashLight() {
    fireOnTorchModeChanged(enableTorchCallback);
  }

  private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context ctxt, Intent intent) {
      int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
      textView.setText(level + "%");
    }
  };

}
