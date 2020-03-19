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
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;


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
  private WindowManager.LayoutParams backgroundParams1;
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
  private static View overlaySwipFone;
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
  /**
   * Объявляем timeanddate.
   */
  private timeanddate timeanddate;
  /**
   * Объявляем BlackCurtainView.
   */
  private BlackCurtainView blackCurtainView;
  /**
   * Объявляем MyService
   */
  private static MyService mInstance = null;
  private networktype networktype;
  private TextView netType;
  private battary battary;
  private ImageView batt;
  private MyPhoneStateListener myPhoneStateListener;
  private TextView signal;
  private View viewbl;
  private TextView dateView;
  private ToggleButton timerbtn;
  private LinearLayout timpanel;
  private View blk_seek;
  private ToggleButton f5minuts;
  private ToggleButton f10minuts;
  private ToggleButton f15minuts;
  private ToggleButton f20minuts;
  private ToggleButton f25minuts;
  private ToggleButton uset_minuts;
  private TextView textTimer;
  private timers timers;
  private Vibrator vibrator;

  private AppManagerShared appManager;
  private RecyclerView recyclerView;
  private Context context;
  private PackageManager packageManager;

  private SharedPreferences sp;
  public static final String APP_PREFERENCES = "mysettings";
//  private GestureDetectorCompat lSwipeDetector;
//  private LinearLayout swipefone;
//  private static final int SWIPE_MIN_DISTANCE = 300;
//  private static final int SWIPE_MAX_DISTANCE = 150;
//  private static final int SWIPE_MIN_VELOCITY = 200;
//
//  int i = 0;

  public static boolean isServiceCreated() {
    try {
      // If instance was not cleared but the service was destroyed an Exception will be thrown
      return mInstance != null && mInstance.ping();
    } catch (NullPointerException e) {
      // destroyed/not-started
      return false;
    }
  }

  /**
   * Просто возвращает true. Если служба все еще активна, этот метод будет доступен.
   *
   * @return
   */
  private boolean ping() {
    return true;
  }


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
    mInstance = this;
    vibrator = (Vibrator) mcontext.getSystemService(Context.VIBRATOR_SERVICE);
    context = this;
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
    timeanddate = new timeanddate();
    blackCurtainView = new BlackCurtainView();
    networktype = new networktype();
    netType = (TextView) overlayView.findViewById(R.id.set);
    battary = new battary();
    batt = (ImageView) overlayView.findViewById(R.id.imageBat);
    signal = (TextView) overlayView.findViewById(R.id.signal);
    viewbl = (View) overlayView.findViewById(R.id.viewbl);
    myPhoneStateListener = new MyPhoneStateListener();
    timers = new timers();

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
    time = (TextView) overlayView.findViewById(R.id.timme);
    dateView = (TextView) overlayView.findViewById(R.id.dateview);
    timerbtn = (ToggleButton) overlayView.findViewById(R.id.timer_tgl);
    timpanel = (LinearLayout) overlayView.findViewById(R.id.timpanel);
    f5minuts = (ToggleButton) overlayView.findViewById(R.id.f5minut);
    f10minuts = (ToggleButton) overlayView.findViewById(R.id.f10minut);
    f15minuts = (ToggleButton) overlayView.findViewById(R.id.f15minut);
    f20minuts = (ToggleButton) overlayView.findViewById(R.id.f20minut);
    f25minuts = (ToggleButton) overlayView.findViewById(R.id.f25minut);
    uset_minuts = (ToggleButton) overlayView.findViewById(R.id.usetMinut);
    textTimer = (TextView) overlayView.findViewById(R.id.textTimer);

//    lSwipeDetector = new GestureDetectorCompat(mcontext, new MyGestureListener());
//
//    overlaySwipFone.setOnTouchListener(new View.OnTouchListener() {
//      @Override
//      public boolean onTouch(View v, MotionEvent event) {
//        return lSwipeDetector.onTouchEvent(event);
//      }
//    });

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
    timerbtn.setOnClickListener(this);
    f5minuts.setOnClickListener(this);
    f10minuts.setOnClickListener(this);
    f15minuts.setOnClickListener(this);
    f20minuts.setOnClickListener(this);
    f25minuts.setOnClickListener(this);
    uset_minuts.setOnClickListener(this);
    return super.onStartCommand(intent, flags, startId);
  }

  /**
   * Данный класс, чтобы сервис не умирал, когда закрываем основное окно программы.
   */
  private void startForeground() {

    String NOTIF_ID = "1";
    String NOTIF_CHANNEL_ID = "1234";
    NotificationChannel chan = new NotificationChannel(NOTIF_CHANNEL_ID, NOTIF_ID, NotificationManager.IMPORTANCE_NONE);
    chan.setLightColor(Color.BLUE);
    chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    assert manager != null;
    manager.createNotificationChannel(chan);

    Intent intentHide = new Intent(this, StopServiceReceiver.class);
    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
    PendingIntent pendingIntent1 = PendingIntent.getActivity(getApplicationContext(), 1, intent1, 0);
    PendingIntent hide = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intentHide, PendingIntent.FLAG_CANCEL_CURRENT);
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIF_CHANNEL_ID);
    Notification notification1 = notificationBuilder.setOngoing(true)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("Сервис запущен.")
        .setPriority(NotificationManager.IMPORTANCE_MIN)
        .setCategory(Notification.CATEGORY_SERVICE)
        .addAction(R.drawable.ic_back, "Открыть", pendingIntent1)
        .addAction(R.drawable.ic_back, "Закрыть", hide)
        .build();
    startForeground(2, notification1);
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
    blk_seek = ((LayoutInflater) getSystemService(
        Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.blk_seek, null);

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

    //Фон для свайпа - открытие панели
//    backgroundParams1 = new WindowManager.LayoutParams(
//        WindowManager.LayoutParams.MATCH_PARENT,
//        WindowManager.LayoutParams.MATCH_PARENT,
//        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
//            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//        PixelFormat.TRANSLUCENT);
//    backgroundParams1.gravity = Gravity.CENTER;
//    overlaySwipFone = ((LayoutInflater) getSystemService(
//        Context.LAYOUT_INFLATER_SERVICE)).inflate(
//            R.layout.swip_fone, null);
//    windowManager.addView(overlaySwipFone,backgroundParams1);


    Handler handler = new Handler();
    handler.postDelayed(() -> windowManager.addView(overlayBottom, bottomParams), 300);
    windowManager.addView(overlayView, params);

    overlayView.setOnTouchListener(new OnTouchListener() {
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
            //Если расскоментировать панель будет подниматься выше чем ее размеры
            //params.y = initialY - (int) (motionEvent.getRawY() - initialTouchY);
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

    overlayBottom.setOnTouchListener(new OnTouchListener() {

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

              sp = context.getSharedPreferences(APP_PREFERENCES,
                  Context.MODE_PRIVATE);
              appManager = new AppManagerShared(context);
              packageManager = getPackageManager();
              List<AppInfo> installedApps = appManager.getInstalledApps();

              AppsAdapterShared appsAdapter = new AppsAdapterShared();

              recyclerView = overlayView.findViewById(R.id.apps_rv);
              recyclerView.setLayoutManager(new LinearLayoutManager(context));
              recyclerView.setAdapter(appsAdapter);
              recyclerView.setLayoutManager(new GridLayoutManager(context,6));

              appsAdapter.setApps(installedApps);


              recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                  (context, recyclerView, new RecyclerItemClickListener.OnItemMotionEventListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                      String string = String.valueOf(installedApps.get(position));
                      String[] parts = string.split(",");
                      String part1 = parts[0]; // 004
                      Intent intent = packageManager.getLaunchIntentForPackage(part1);
                      startActivity(intent);
                      onLock();
                      appsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                      Log.d("Array Value", "PAC" + "not checked"+installedApps.get(position));
                    }
                  }));

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
//              //Создаем экземпляр black_curtrain_seekbar
              black_curtrain_seekbar = (SeekBar) blk_seek.findViewById(R.id.black_curtrain_seekbar);
              //Вызваем класс включения затемнения и передаем ему экземпляр black_curtrain_seekbar
//              blackCurtain.onBrig3();
            }
            //Проверяем включенность кнопки
            boolean onBlack = ((blackMode).isChecked());
            if (onBlack) {
              //Если включена - делаем полосу настройки затемненности доступной
              black_curtrain_seekbar.setEnabled(true);
            } else {
              //Если нет - делаем не доступной
              if (black_curtrain_seekbar != null) {
                black_curtrain_seekbar.setEnabled(false);
              } else {
              }
            }
            timeanddate.timeAndDate(time, dateView);
            networktype.speed(mcontext, netType);
            myPhoneStateListener.onSignal(signal, mcontext);
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
      unregisterReceiver(receiver);
      unregisterReceiver(mBatInfoReceiver);
      blackCurtainView.offCurtain();
      mInstance = null;
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
          black_curtrain_seekbar.setVisibility(View.VISIBLE);
          viewbl.setVisibility(View.VISIBLE);
          timerbtn.setEnabled(false);
        } else {
          //Отключаем затемнение экрана
          blackCurtain.offCurtain();
          //Делаем не доступной полосу настройки затемненности
          black_curtrain_seekbar.setEnabled(false);
          black_curtrain_seekbar.setVisibility(View.GONE);
          viewbl.setVisibility(View.GONE);
          //Устанавливаем значение полосы затемнения на 0
          black_curtrain_seekbar.setProgress(0);
          timerbtn.setEnabled(true);
        }
      case R.id.timer_tgl:
        boolean onTimer = ((timerbtn).isChecked());
        if (onTimer) {
          timpanel.setVisibility(View.VISIBLE);
          viewbl.setVisibility(View.VISIBLE);
          blackMode.setEnabled(false);
        } else {
          timpanel.setVisibility(View.GONE);
          viewbl.setVisibility(View.GONE);
          blackMode.setEnabled(true);
        }


        f5minuts.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            boolean onTomer5 = ((f5minuts).isChecked());
            if (onTomer5) {
              timers.onTimer_5(textTimer,mcontext,vibrator);
              timers.onStartTimer_5();
              timerbtn.setEnabled(false);
              f10minuts.setVisibility(View.GONE);
              f15minuts.setVisibility(View.GONE);
              f20minuts.setVisibility(View.GONE);
              f25minuts.setVisibility(View.GONE);
              textTimer.setVisibility(View.VISIBLE);
            } else {
              timers.offTimer_5(textTimer,vibrator);
              timerbtn.setEnabled(true);
              f10minuts.setVisibility(View.VISIBLE);
              f15minuts.setVisibility(View.VISIBLE);
              f20minuts.setVisibility(View.VISIBLE);
              f25minuts.setVisibility(View.VISIBLE);
              textTimer.setVisibility(View.GONE);
            }
          }
        });
        f10minuts.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            boolean onTomer10 = ((f10minuts).isChecked());
            if (onTomer10) {
              timers.onTimer_10(textTimer,mcontext,vibrator);
              timers.onStartTimer_10();
              timerbtn.setEnabled(false);
              f5minuts.setVisibility(View.GONE);
              f15minuts.setVisibility(View.GONE);
              f20minuts.setVisibility(View.GONE);
              f25minuts.setVisibility(View.GONE);
              textTimer.setVisibility(View.VISIBLE);
            } else {
              timers.offTimer_10(textTimer,vibrator);
              timerbtn.setEnabled(true);
              f5minuts.setVisibility(View.VISIBLE);
              f15minuts.setVisibility(View.VISIBLE);
              f20minuts.setVisibility(View.VISIBLE);
              f25minuts.setVisibility(View.VISIBLE);
              textTimer.setVisibility(View.GONE);
            }
          }
        });
        f15minuts.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            boolean onTomer15 = ((f15minuts).isChecked());
            if (onTomer15) {
              timers.onTimer_15(textTimer,mcontext,vibrator);
              timers.onStartTimer_15();
              timerbtn.setEnabled(false);
              f5minuts.setVisibility(View.GONE);
              f10minuts.setVisibility(View.GONE);
              f20minuts.setVisibility(View.GONE);
              f25minuts.setVisibility(View.GONE);
              textTimer.setVisibility(View.VISIBLE);
            } else {
              timers.offTimer_15(textTimer,vibrator);
              timerbtn.setEnabled(true);
              f5minuts.setVisibility(View.VISIBLE);
              f10minuts.setVisibility(View.VISIBLE);
              f20minuts.setVisibility(View.VISIBLE);
              f25minuts.setVisibility(View.VISIBLE);
              textTimer.setVisibility(View.GONE);
            }
          }
        });
        f20minuts.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            boolean onTomer20 = ((f20minuts).isChecked());
            if (onTomer20) {
              timers.onTimer_20(textTimer,mcontext,vibrator);
              timers.onStartTimer_20();
              timerbtn.setEnabled(false);
              f5minuts.setVisibility(View.GONE);
              f10minuts.setVisibility(View.GONE);
              f15minuts.setVisibility(View.GONE);
              f25minuts.setVisibility(View.GONE);
              textTimer.setVisibility(View.VISIBLE);
            } else {
              timers.offTimer_20(textTimer,vibrator);
              timerbtn.setEnabled(true);
              f5minuts.setVisibility(View.VISIBLE);
              f10minuts.setVisibility(View.VISIBLE);
              f15minuts.setVisibility(View.VISIBLE);
              f25minuts.setVisibility(View.VISIBLE);
              textTimer.setVisibility(View.GONE);
            }
          }
        });
        f25minuts.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            boolean onTomer25 = ((f25minuts).isChecked());
            if (onTomer25) {
              timers.onTimer_25(textTimer,mcontext,vibrator);
              timers.onStartTimer_25();
              timerbtn.setEnabled(false);
              f5minuts.setVisibility(View.GONE);
              f10minuts.setVisibility(View.GONE);
              f15minuts.setVisibility(View.GONE);
              f20minuts.setVisibility(View.GONE);
              textTimer.setVisibility(View.VISIBLE);
            } else {
              timers.offTimer_25(textTimer,vibrator,dateView);
              timerbtn.setEnabled(true);
              f5minuts.setVisibility(View.VISIBLE);
              f10minuts.setVisibility(View.VISIBLE);
              f15minuts.setVisibility(View.VISIBLE);
              f20minuts.setVisibility(View.VISIBLE);
              textTimer.setVisibility(View.GONE);
            }
          }
        });
        textTimer.setOnClickListener(new View.OnClickListener() {
          boolean tick = false;
          int click = 0;
          @Override
          public void onClick(View v) {
            if (f5minuts.isChecked()){
              if (click == 0) {
                tick=true;
                timers.setTimer5(tick, textTimer, mcontext, vibrator);
                click++;
              } else {
                click--;
                tick = false;
                timers.setTimer5(tick, textTimer, mcontext, vibrator);
              }
            }else if (f10minuts.isChecked()){
              if (click == 0) {
                tick=true;
                timers.setTimer10(tick, textTimer, mcontext, vibrator);
                click++;
              } else {
                click--;
                tick = false;
                timers.setTimer10(tick, textTimer, mcontext, vibrator);
              }
            }else if (f15minuts.isChecked()){
              if (click == 0) {
                tick=true;
                timers.setTimer15(tick, textTimer, mcontext, vibrator);
                click++;
              } else {
                click--;
                tick = false;
                timers.setTimer15(tick, textTimer, mcontext, vibrator);
              }
            }else if (f20minuts.isChecked()){
              if (click == 0) {
                tick=true;
                timers.setTimer20(tick, textTimer, mcontext, vibrator);
                click++;
              } else {
                click--;
                tick = false;
                timers.setTimer20(tick, textTimer, mcontext, vibrator);
              }
            }else if (f25minuts.isChecked()){
              if (click == 0) {
                tick=true;
                timers.setTimer25(tick, textTimer, mcontext, vibrator);
                click++;
              } else {
                click--;
                tick = false;
                timers.setTimer25(tick, textTimer, mcontext, vibrator);
              }
            }

          }
        });
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

      int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
      boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
          status == BatteryManager.BATTERY_STATUS_FULL;
      String a = String.valueOf(isCharging);
      battary.setimg(mcontext, level, batt, a);
    }
  };

//  private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
//    @Override
//    public boolean onDown(MotionEvent e) {
//      return true;
//    }
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
//      if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_DISTANCE)
//       return false;
//      if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
//        MyService.overlayView.setVisibility(View.VISIBLE);
//        MyService.overlayBackground.setVisibility(View.GONE);
//        MyService.overlayBottom.setVisibility(View.GONE);
//      }
//      return false;
//    }
//  }
}
