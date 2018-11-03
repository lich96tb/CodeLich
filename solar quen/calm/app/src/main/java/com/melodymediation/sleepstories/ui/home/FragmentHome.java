package com.melodymediation.sleepstories.ui.home;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.melodymediation.sleepstories.data.general.GeneralData;
import com.melodymediation.sleepstories.data.room.AppDatabase;
import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.service.AlertDialogReceiver;
import com.melodymediation.sleepstories.service.ServiceMedia;
import com.melodymediation.sleepstories.service.doawloadmp3.DownloadMusicFile;
import com.melodymediation.sleepstories.service.doawloadmp3.IDoawnload;
import com.melodymediation.sleepstories.service.evenbust.MessageEvent;
import com.melodymediation.sleepstories.service.evenbust.MessegaeEventService;
import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.ui.MethodStatic;
import com.melodymediation.sleepstories.ui.home.background.CardFragmentPagerAdapter;
import com.melodymediation.sleepstories.ui.home.background.ShadowTransformer;
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModel;
import com.melodymediation.sleepstories.ui.setting.SettingFragment;
import com.melodymediation.sleepstories.utilities.ConstantsKt;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentHome extends Fragment implements View.OnClickListener, IDoawnload {
    private RelativeLayout fmHome, layoutHome, layoutSetBackgroud, helpPinch, helpTouch;
    private int x, y, x1, y1;
    private boolean checkMultiTouch;
    private GestureDetector gestureDetector;
    private int width, height;
    private AudioManager audio;
    private int volumeLevel, maxVolume;
    private ViewSwitcher viewSwitcher;
    private TextView valueVolume, txtSelectBackground, txtNameSong;
    private LinearLayout dialog_display, volume_lv_expose;
    private SeekBar seekbarVolume;
    private ViewPager viewPager;
    private int position;
    private String urlBackgroundCurrent;
    private List<Integer> list;
    private List<Session> backgroundsList;
    private Button btnNext;
    private boolean checklogin, checkTouch = false;
    private ImageView imgSettings;
    private static final String PRE_SETTINGS = "SETTINGS";
    private static final String TIME_REMINDER = "1203";
    private SessionDetailViewModel sessionDetailViewModel;
    private SharedPreferences sharedPreferences;
    private String backgroundIdDefault;
    private Boolean hasLockBackground = false;
    private ProgressDialog dialogLoadMp3;
    private Session session;
    private AppDatabase db;
    private ImageView iplayMp3Background;
    private RelativeLayout layoutPlayBackgroundMp3;
    private Intent intentBroadcastStopService;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        init(view);
        checklogin = GeneralData.shared(getContext());
        addData();

        viewPagerBackground();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gestureDetector = new GestureDetector(getContext(), new Mygestue());

        layoutHome.setOnTouchListener((v, event) -> {
            int pointIndex = event.getActionIndex();
            int maskAction = event.getActionMasked();
            gestureDetector.onTouchEvent(event);
            switch (maskAction) {
                case MotionEvent.ACTION_POINTER_DOWN: {
                    checkMultiTouch = false;
                    if (pointIndex >= 1) {
                        x = (int) Math.abs(event.getX(1) - event.getX(0));
                        y = (int) Math.abs(event.getY(1) - event.getY(0));
                    }
                }
                case MotionEvent.ACTION_MOVE: {
                    for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                        if (i >= 1) {
                            x1 = (int) Math.abs((event.getX(1) - event.getX(0)));
                            y1 = (int) Math.abs(event.getY(1) - event.getY(0));
                            if ((x1 - x > 50 || (y1 - y) > 50) && !checkMultiTouch) {
                                checkMultiTouch = true;
                            } else if (x1 - x < -50 && !checkMultiTouch) {
                                checkMultiTouch = true;
                                viewSwitcher.showNext();
                                helpPinch.setVisibility(View.GONE);
                                MethodStatic.tabLayout.setVisibility(View.GONE);
                                if (MethodStatic.bottomSheetBehavior != null) {
                                    MethodStatic.bottomSheetBehavior.setHideable(true);
                                    MethodStatic.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                }
                                //                      MainFragment.layoutPlayListSmall.setVisibility(View.GONE);

                            }
                        }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:
                    dialog_display.setVisibility(View.GONE);
                    volume_lv_expose.setVisibility(View.GONE);
                    break;
            }
            return true;
        });

        return view;
    }

    private String getBackgroundUrl(String backgroundId) {
        Session session = sessionDetailViewModel.getSessionById(backgroundId);
        String backgroundUrl = "";
        if (session != null) {
            if (session.getUrlBackgroundDist() != null && !session.getUrlBackgroundDist().equals("")) {
                backgroundUrl = session.getUrlBackgroundDist();
            } else {
                backgroundUrl = session.getUrlBackground();
            }
        }
        return backgroundUrl;
    }

    private String getBackgroundIdDefault() {
        String backgroundId = sharedPreferences.getString(ConstantsKt.BACKGROUND_ID_DEFAULT, "");
        if (!backgroundId.equals("")) {
            session = db.sessionDao().getSessionById(backgroundId);
            Log.e("DDDdddD", session.getUrlDist());
            if (!session.getUrlDist().equals(""))
                checkPlayBackground();

        }


        if (backgroundId == null || backgroundId.equals("")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            List<Session> sessions = sessionDetailViewModel.getLessonsByType(ConstantsKt.SESSION_TYPE_BACKGROUND);
            if (sessions.size() > 0) {
                editor.putString(ConstantsKt.BACKGROUND_ID_DEFAULT, sessions.get(0).getSessionId());
                editor.commit();
            }
        }
        backgroundId = sharedPreferences.getString(ConstantsKt.BACKGROUND_ID_DEFAULT, "");
        return backgroundId;
    }

    // Update background default
    private void setBackgroundIdDefault(String backgroundId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantsKt.BACKGROUND_ID_DEFAULT, backgroundId);
        editor.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getContext().getSharedPreferences(PRE_SETTINGS, Context.MODE_PRIVATE);

        String time = preferences.getString(TIME_REMINDER, "");

        //      Log.d("AMBE1203", time + " aaaaaa");

        if (time == null || time.equals("")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TIME_REMINDER, getResources().getString(R.string.time_reminder_default));
            editor.commit();
            String txt = preferences.getString(TIME_REMINDER, "");
            //     Log.d("AMBE1203", txt + " aaaaaa");

            String suffix = txt.substring(txt.length() - 2);
            txt = txt.replace(suffix, "");
            String[] hms = txt.split(":");
            if (suffix.equals("PM") || suffix.equals("pm")) {
                hms[0] = String.valueOf(Integer.parseInt(hms[0]) + 12);
                hms[0] = hms[0].equals("24") ? "12" : hms[0];
            } else if (suffix.equals("AM") || suffix.equals("am")) {
                hms[0] = hms[0].equals("12") ? "00" : hms[0];
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0].trim()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(hms[1].trim()));
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            Intent intent = new Intent(getContext(), AlertDialogReceiver.class);
            PendingIntent mAlarmSender = PendingIntent.getBroadcast(getContext(), 167, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager amgr = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
            amgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mAlarmSender);
            iplayMp3Background.setOnClickListener(this);
        }
    }

    private void addData() {
        if (!checklogin) {
            helpPinch.setVisibility(View.VISIBLE);
            MethodStatic.tabLayout.setVisibility(View.GONE);
        }
    }

    private void viewPagerBackground() {
//        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(), dpToPixels(2, getContext()), list);
        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(), dpToPixels(2, getContext()), backgroundsList);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        int index = 0;
        for (Session session : backgroundsList) {
            if (session.getSessionId().equals(getBackgroundIdDefault())) {
                viewPager.setCurrentItem(index);
                Glide.with(getContext()).load(getBackgroundUrl(getBackgroundIdDefault())).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            layoutSetBackgroud.setBackground(resource);
                        }
                    }
                });
                break;
            }
            index++;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
                String backgroundUrl;
                session = backgroundsList.get(i);
                if (!session.getUrlBackgroundDist().equals("") && session.getUrlBackgroundDist() != null) {
                    backgroundUrl = session.getUrlBackgroundDist();
                } else {
                    backgroundUrl = session.getUrlBackground();
                }

                backgroundIdDefault = session.getSessionId();
                if (!session.isFee() && !session.isBuy()) {
                    hasLockBackground = true;
                } else {
                    hasLockBackground = false;
                }

                // Set background
                Glide.with(getContext()).load(backgroundUrl).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            layoutSetBackgroud.setBackground(resource);
                        }
                    }
                });
//                layoutSetBackgroud.setBackgroundResource(list.get(i));
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        iplayMp3Background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessegaeEventService(false, 0));
            }
        });
    }

    private void init(View view) {
        //  Log.e("ADDDDD",""+MethodStatic.timeBackground(getContext()));
        //    MethodStatic.AlarmSoungBackground(getContext(), MethodStatic.timeBackground(getContext()));
        layoutPlayBackgroundMp3 = view.findViewById(R.id.layoutPlayBackgroundMp3);

        txtNameSong = view.findViewById(R.id.txtNameSong);
        iplayMp3Background = view.findViewById(R.id.iplayMp3Background);
        db = AppDatabase.Companion.getInstance(getContext());
        // Init SharedPreferences
        sharedPreferences = getContext().getSharedPreferences(ConstantsKt.SHARED_PREFERENCES_CALM, Context.MODE_PRIVATE);
        // Init view model
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideSessionDetailViewModelFactory(getContext());
        sessionDetailViewModel = ViewModelProviders.of(this, factory).get(SessionDetailViewModel.class);

        layoutHome = view.findViewById(R.id.layoutHome);

        // Set default background home
        backgroundIdDefault = getBackgroundIdDefault();
        urlBackgroundCurrent = getBackgroundUrl(backgroundIdDefault);
        Glide.with(getContext()).load(urlBackgroundCurrent).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layoutHome.setBackground(resource);
                }
            }
        });


        // Get backgrounds
        backgroundsList = sessionDetailViewModel.getLessonsByType(ConstantsKt.SESSION_TYPE_BACKGROUND);
        session = backgroundsList.get(0);

        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        audio = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        fmHome = view.findViewById(R.id.fmHome);
        layoutSetBackgroud = view.findViewById(R.id.layoutSetBackgroud);
        layoutSetBackgroud.setOnClickListener(this);
        viewSwitcher = view.findViewById(R.id.viewSwitcher);
        valueVolume = view.findViewById(R.id.valueVolume);
        dialog_display = view.findViewById(R.id.dialog_display);
        volume_lv_expose = view.findViewById(R.id.volume_lv_expose);
        seekbarVolume = view.findViewById(R.id.seekbarVolume);
        viewPager = view.findViewById(R.id.viewPager);
        txtSelectBackground = view.findViewById(R.id.txtSelectBackground);
        // btnNext = view.findViewById(R.id.btnNext);
        ImageView imageView = view.findViewById(R.id.imgGif);
        ImageView imgGifPinch = view.findViewById(R.id.imgGifPinch);
        helpPinch = view.findViewById(R.id.helpPinch);
        helpTouch = view.findViewById(R.id.helpTouch);
        dialogLoadMp3 = new ProgressDialog(getContext());
        dialogLoadMp3.setCancelable(false);
        dialogLoadMp3.setMessage("Dowload data!");

        Glide.with(getContext())
            .load(R.drawable.touch)
            .into(imageView);
        Glide.with(getContext())
            .load(R.drawable.pinch)
            .into(imgGifPinch);

        imgSettings = view.findViewById(R.id.img_settings);
        list = new ArrayList<>();
        txtSelectBackground.setOnClickListener(this);
        imgSettings.setOnClickListener(this);

        maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekbarVolume.setMax(maxVolume);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSelectBackground:
                if (!hasLockBackground) {
                    if (!checklogin) {
                        checkTouch = true;
                        checklogin = true;
                        GeneralData.shared(getContext(), true);
                    }
                    // Update background default
                    setBackgroundIdDefault(backgroundIdDefault);

                    urlBackgroundCurrent = getBackgroundUrl(backgroundIdDefault);
//                layoutHome.setBackgroundResource(list.get(position));
                    // Set background
                    Glide.with(getContext()).load(urlBackgroundCurrent).into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                layoutHome.setBackground(resource);
                            }
                        }
                    });
                    viewSwitcher.showNext();
                    if (MethodStatic.bottomSheetBehavior != null)
                        MethodStatic.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    MethodStatic.tabLayout.setVisibility(View.VISIBLE);
                    //        MainFragment.layoutPlayListSmall.setVisibility(View.VISIBLE);

                    if (checkTouch) {
                        helpTouch.setVisibility(View.VISIBLE);
                    }
                    if (session.getUrlDist().equals("")) {
                        dialogLoadMp3.show();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", session.getName());
                        bundle.putString("url", session.getUrl());
                        DownloadMusicFile downloadMusicFile = new DownloadMusicFile(this);
                        downloadMusicFile.execute(bundle);
                    } else {
                        checkPlayBackground();
                    }
                }
                break;
            case R.id.layoutSetBackgroud:
                viewSwitcher.showNext();
                if (!checklogin) {
                    helpPinch.setVisibility(View.VISIBLE);
                    MethodStatic.tabLayout.setVisibility(View.GONE);
                    //       MainFragment.layoutPlayListSmall.setVisibility(View.GONE);
                } else
                    MethodStatic.tabLayout.setVisibility(View.VISIBLE);
                //             MainFragment.layoutPlayListSmall.setVisibility(View.VISIBLE);
                break;
            case R.id.img_settings:
                //       Navigation.findNavController(v).navigate(R.id.meditationPlayFragment);
                //  Navigation.findNavController(v).navigate(R.id.settingFragment);

                getFragmentManager().beginTransaction().replace(R.id.fmHome, new SettingFragment()).addToBackStack(null).commit();

                break;
        }
    }


    class Mygestue extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(e2.getX()) - e1.getX() < Math.abs(e2.getY() - e1.getY())) {
                if (e1.getX() < width / 2) {
                    int a = (int) ((e2.getY() - e1.getY()) * (-1));
                    int deltaVolume = (maxVolume * a * 3 / height);
                    int volume = volumeLevel + deltaVolume;
                    Log.e("AAAD", "" + a);
                    if (volume > 15)
                        volume = 15;
                    if (volume < 0)
                        volume = 0;
                    if (volume >= 0 && volume <= maxVolume) {
                        if (checkTouch) {
                            checkTouch = false;
                            helpTouch.setVisibility(View.GONE);
                        }
                        audio.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
                        valueVolume.setText(volume * 100 / maxVolume + "%");
                        seekbarVolume.setProgress(volume);
                        dialog_display.setVisibility(View.VISIBLE);
                        volume_lv_expose.setVisibility(View.VISIBLE);
                    }
                }
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            volumeLevel = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
            return super.onDown(e);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        checklogin = GeneralData.shared(getContext());
//        if (ServiceMedia.session != null) {
//            if (ServiceMedia.session.getType().equals("Background")) {
//                layoutPlayBackgroundMp3.setVisibility(View.VISIBLE);
//            } else {
//                layoutPlayBackgroundMp3.setVisibility(View.GONE);
//            }
//        }

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void Idoawload(String urlDist) {
        db.sessionDao().updateSessionUrlDist(session.getSessionId(), urlDist);
        backgroundsList = sessionDetailViewModel.getLessonsByType(ConstantsKt.SESSION_TYPE_BACKGROUND);
        session = backgroundsList.get(position);
        dialogLoadMp3.dismiss();

         checkPlayBackground();
//        Intent intent = new Intent(getContext(), ServiceMedia.class);
//        intent.putExtra("session", session);
//        getContext().startService(intent);
    }

    private void checkPlayBackground() {
        Intent intent = new Intent(getContext(), ServiceMedia.class);
        if (!MethodStatic.isMyServiceRunning(ServiceMedia.class, getContext())) {
            intent.putExtra("session", session);
            getContext().startService(intent);

        } else if (ServiceMedia.session == null) {
            getContext().stopService(intent);
            intent.putExtra("session", session);
            getContext().startService(intent);
        } else if (ServiceMedia.session.getType().equals("Background") && !session.getUrlDist().equals(ServiceMedia.session.getUrlDist())) {
            getContext().stopService(intent);
            intent.putExtra("session3", session);
            getContext().startService(intent);
        }
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent noteEvent) {
        txtNameSong.setText(noteEvent.nameSong);
        if (noteEvent.isCheckPlay()) {
            iplayMp3Background.setImageResource(R.drawable.ic_pause);
        } else {
            iplayMp3Background.setImageResource(R.drawable.ic_play);
        }
    }
}

