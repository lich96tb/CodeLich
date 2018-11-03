package com.melodymediation.sleepstories.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.data.room.AppDatabase;
import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.ui.MainActivity;
import com.melodymediation.sleepstories.ui.MainActivityMain;
import com.melodymediation.sleepstories.ui.MethodStatic;
import com.melodymediation.sleepstories.ui.lesson.LessonFragment;
import com.melodymediation.sleepstories.service.evenbust.MessageEvent;
import com.melodymediation.sleepstories.service.evenbust.MessegaeEventService;
import com.melodymediation.sleepstories.utilities.ConstantsKt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

public class ServiceMedia extends Service {
    private MediaPlayer mediaPlayer;
    private RemoteViews contentView;
    public static Session session;
    private String url;
    public static String id;
    private long timeDuration;
    Handler handler = new Handler();
    public static boolean checkPlay;
    public static long timeAlar = -1;
    public static int minutes=-1;
    private AppDatabase db;
    private SharedPreferences sharedPreferences;
    private Intent intentBroadcastStopService;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessegaeEventService event) {

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_pause);
            handler.postDelayed(onEverySecond, 1);
        } else {
            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_play);
            mediaPlayer.pause();
        }
        mBuilder.setContent(contentView);
        //cho phep mo rong notification
        Notification notification = mBuilder.build();
        notification.bigContentView = contentView;
        startForeground(1, notification);

        Intent intent = new Intent();
        intent.setAction("broadcastSen");
        intent.putExtra("check", mediaPlayer.isPlaying());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initView();

        try {
            if ((Session) intent.getSerializableExtra("session") != null) {
                session = (Session) intent.getSerializableExtra("session");
                url = session.getUrlDist();
                id = session.getSessionId();
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
                    timeDuration = mediaPlayer.getDuration();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mp.start();
                        handler.postDelayed(onEverySecond, 0);
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override

                    public void onCompletion(MediaPlayer mp) {
                        if (mp.getCurrentPosition() > timeDuration - 100) {
                            if (timeAlar != -1) {
                                mediaPlayer.start();
                                handler.postDelayed(onEverySecond, 0);
                            } else {
                                sharedPreferences = getSharedPreferences(ConstantsKt.SHARED_PREFERENCES_CALM, Context.MODE_PRIVATE);
                                String backgroundId = sharedPreferences.getString(ConstantsKt.BACKGROUND_ID_DEFAULT, "");
                                db = AppDatabase.Companion.getInstance(getApplicationContext());
                                session = db.sessionDao().getSessionById(backgroundId);
                                mediaPlayer.stop();
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(session.getUrlDist()));
                                mediaPlayer.start();
                                Calendar calendar = Calendar.getInstance();
                                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + MethodStatic.timeBackground(getApplicationContext()), pendingIntent);
                            }
                        }

                    }
                });
                LocalBroadcastManager.getInstance(this).registerReceiver(mRefreshReceiver, new IntentFilter("My Broadcast"));
                IntentFilter filter = new IntentFilter();
                filter.addAction("My Broadcast");
                createNotification(this, "a", "b");

                PhoneStateListener phoneStateListener = new PhoneStateListener() {
                    @Override
                    public void onCallStateChanged(int state, String incomingNumber) {
                        if (state == TelephonyManager.CALL_STATE_RINGING) {
                            //Incoming call: Pause music
                            mediaPlayer.pause();
                        } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                            //Not in call: Play music
                            mediaPlayer.start();
                        } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                            //A call is dialing, active or on hold
                            mediaPlayer.pause();

                        }
                        super.onCallStateChanged(state, incomingNumber);
                    }
                };
                TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                if (mgr != null) {
                    mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        } catch (Exception e) {
        }
        if (session != null && !session.getType().equals("Background")) {
            Log.e("ADDDASDDhnjh", "huy roi");
            Intent intenta = new Intent(this, BroadcastStopServiceSongBackground.class);
            PendingIntent pendingIntenta = PendingIntent.getBroadcast(this, 0, intenta, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntenta);
        }
        return START_STICKY;
    }

    private void initView() {
        intentBroadcastStopService = new Intent(this, BroadcastStopService.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intentBroadcastStopService, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private BroadcastReceiver mRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mediaPlayer.isPlaying()){
                int progress = intent.getIntExtra("progress", 0);
                mediaPlayer.seekTo(progress);
            }

        }
    };

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        EventBus.getDefault().unregister(this);
    }


    //////////////////////////////////


    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public void createNotification(Context mContext, String title, String message) {
        Intent resultIntent = new Intent(mContext, MainActivityMain.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
            0 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(this);
        contentView = new RemoteViews(mContext.getPackageName(), R.layout.notification);
        mBuilder.setCustomContentView(contentView);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(null)
            .setContentIntent(resultPendingIntent);
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        Intent button_intent = new Intent("button");
        button_intent.putExtra("id", "lichddd");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, button_intent, 0);
        contentView.setOnClickPendingIntent(R.id.imgPlay, pendingIntent);
        contentView.setTextViewText(R.id.txtNameNTF, LessonFragment.Companion.getNameCategory());
        contentView.setTextViewText(R.id.txtNameMTF, session.getName());
        contentView.setTextViewText(R.id.txtTime, session.getDuration());
        if (!session.getUrlBackgroundDist().equals("")) {
            Bitmap bitmap = BitmapFactory.decodeFile(session.getUrlBackgroundDist());
            contentView.setImageViewBitmap(R.id.imgbackgroundNotification, bitmap);
        } else {
            contentView.setImageViewResource(R.id.imgbackgroundNotification, R.drawable.image);
        }
        mBuilder.setContent(contentView);
        //cho phep mo rong notification
        Notification notification = mBuilder.build();
        notification.bigContentView = contentView;
        startForeground(1, notification);
    }

    private Runnable onEverySecond = new Runnable() {
        @Override
        public void run() {
            checkPlay = mediaPlayer.isPlaying();
            if (mediaPlayer.isPlaying()) {
                handler.postDelayed(onEverySecond, 100);
                EventBus.getDefault().postSticky(new MessageEvent(session.getUrl(), "dd", session.getName(), true, mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration()));
            } else
                EventBus.getDefault().postSticky(new MessageEvent(session.getUrl(), "dd", session.getName(), false, mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration()));
        }
    };

}
