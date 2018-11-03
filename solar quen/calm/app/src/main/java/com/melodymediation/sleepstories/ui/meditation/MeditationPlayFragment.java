package com.melodymediation.sleepstories.ui.meditation;


import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.databinding.FragmentMeditationPlayBinding;
import com.melodymediation.sleepstories.service.ServiceMedia;
import com.melodymediation.sleepstories.ui.MethodStatic;
import com.melodymediation.sleepstories.ui.lesson.LessonAdapter;
import com.melodymediation.sleepstories.service.doawloadmp3.DownloadMusicFile;
import com.melodymediation.sleepstories.service.doawloadmp3.IDoawnload;
import com.melodymediation.sleepstories.service.evenbust.MessageEvent;
import com.melodymediation.sleepstories.service.evenbust.MessegaeEventService;
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModel;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeditationPlayFragment extends Fragment implements IDoawnload {

    private FragmentMeditationPlayBinding binding;
    private String sessionId = "";
    private Session session;
    private MediaPlayer mMediaPlayer;
    private Boolean check;
    private ProgressDialog dialog;
    private SessionDetailViewModel viewModel;
    private Intent intent;
    private Boolean checkPlayMedia = false;
    private int timeCurrent;


    public MeditationPlayFragment() {
        // Required empty public constructor
    }

    public static MeditationPlayFragment newInstance() {
        MeditationPlayFragment meditationPlayFragment = new MeditationPlayFragment();
        return meditationPlayFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meditation_play, container, false);
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideSessionDetailViewModelFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(SessionDetailViewModel.class);
        intent = new Intent(getContext(), ServiceMedia.class);
        getContext().stopService(intent);

        binding.setViewModel(viewModel);
        binding.imgBackMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressMeditation.setStart(false);
                getFragmentManager().popBackStack();
        //        Navigation.findNavController(v).navigateUp();
            }
        });

        binding.imgControlMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPauseVideo();
            }
        });


        sessionId += getArguments().getString(LessonAdapter.SESSION_ID);
        if (sessionId != null && !sessionId.equals("")) {
            session = viewModel.getSessionById(sessionId);

            Log.d("AMBE1203", new Gson().toJson(session));
            check = MethodStatic.isMyServiceRunning(ServiceMedia.class, getContext());

            binding.txtNameMeditation.setText(session.getName());
            if (session.getUrlDist() == null || session.getUrlDist().equals("")) {
                // download nhac ve may
                if (check) {
                    if (!sessionId.equals(ServiceMedia.id)) {
                        getContext().stopService(intent);
                        checkDownLoadMp3();
                    }
                } else {
                    checkDownLoadMp3();
                }
            } else {
                checkDownLoadMp3();


            }

            //      play(getContext(), R.raw.test);


        }

        return binding.getRoot();
    }

    private void checkDownLoadMp3() {
        if (session.getUrlDist().equals("")) {
            dialog = new ProgressDialog(getContext());
            Bundle bundle = new Bundle();
            bundle.putString("name", session.getName());
            bundle.putString("url", session.getUrl());
            DownloadMusicFile downloadMusicFile = new DownloadMusicFile(this);
            downloadMusicFile.execute(bundle);
            dialog.setCancelable(false);
            dialog.setMessage(getContext().getResources().getString(R.string.download_song));
            dialog.show();
        } else {
            mMediaPlayer = MediaPlayer.create(getContext(), Uri.parse(session.getUrlDist()));
            intent.putExtra("session", session);
            getContext().startService(intent);

            binding.progressMeditation.setStart(true);
            binding.txtTimeMeditation.post(mUpdateTime);

        }
    }

    private void onPauseVideo() {

        if (checkPlayMedia == true) {
            EventBus.getDefault().postSticky(new MessegaeEventService(false, 0));
            checkPlayMedia = false;
            binding.imgControlMeditation.setImageResource(R.drawable.ic_play);
            binding.progressMeditation.setStart(false);


        } else {
            EventBus.getDefault().postSticky(new MessegaeEventService(true, 0));
            binding.progressMeditation.setStart(true);
            binding.imgControlMeditation.setImageResource(R.drawable.ic_pause);
            binding.txtTimeMeditation.post(mUpdateTime);


        }
//        if (mMediaPlayer.isPlaying()) {
//            getContext().stopService(intent);
//            binding.imgControlMeditation.setImageResource(R.drawable.ic_play);
//            binding.progressMeditation.setStart(false);
//        } else {
//            mMediaPlayer.start();
//            binding.imgControlMeditation.setImageResource(R.drawable.ic_pause);
//            binding.progressMeditation.setStart(true);
//            binding.txtTimeMeditation.post(mUpdateTime);
//
//
//        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        timeCurrent = messageEvent.getTimeCurrent();
        checkPlayMedia = messageEvent.checkPlay;

        if (messageEvent.checkPlay) {
            binding.imgControlMeditation.setImageResource(R.drawable.ic_pause);
            binding.txtTimeMeditation.post(mUpdateTime);
            binding.progressMeditation.setStart(true);

        } else {
            binding.imgControlMeditation.setImageResource(R.drawable.ic_play);
            binding.progressMeditation.setStart(false);


        }

    }

    private Runnable mUpdateTime = new Runnable() {
        public void run() {
            if (checkPlayMedia) {
                updatePlayer(timeCurrent);
                binding.txtTimeMeditation.postDelayed(this, 0);
            } else {
                binding.txtTimeMeditation.removeCallbacks(this);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        EventBus.getDefault().unregister(this);

    }

    private void updatePlayer(int currentDuration) {
        // binding.txtTimeMeditation.setText("" + milliSecondsToTimer((long) currentDuration));
        binding.txtTimeMeditation.setText("" + convertTime((long) currentDuration));
    }

    private String convertTime(long milliseconds) {
        return String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        );
    }

    /**
     * Function to convert milliseconds time to Timer Format
     * Hours:Minutes:Seconds
     */
    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }



    @Override
    public void Idoawload(String urlDist) {
        if (urlDist != null) {
            dialog.dismiss();
            viewModel.updateSessionUrlDist(sessionId, urlDist);
            session = viewModel.getSessionById(sessionId);
            checkDownLoadMp3();
        }

    }
}
