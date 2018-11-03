package com.melodymediation.sleepstories.ui.home.background;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.ui.lesson.LessonViewModel;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.melodymediation.sleepstories.ui.lesson.LessonViewModel;


public class CardFragment extends Fragment {

    private CardView cardView;
    private ImageView imageView;
    private TextView tvBackgroundName;
    private RelativeLayout rlLock;
    private LessonViewModel lessonViewModel;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        // Init view model
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideLessonViewModelFactory(getContext());
        lessonViewModel = ViewModelProviders.of(this, factory).get(LessonViewModel.class);

        Bundle bundle=getArguments();
//        int k = (int) bundle.getSerializable("list");
        String sessionId = bundle.get("list").toString();
        Session session = lessonViewModel.getLessonById(sessionId);

        imageView=view.findViewById(R.id.imgBackground);
        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
        cardView.setRadius(20f);

        // Set background name
        tvBackgroundName = view.findViewById(R.id.tv_background_name);
        tvBackgroundName.setText(session.getName());

        // Set lock background
        rlLock = view.findViewById(R.id.relativeLayoutLock);
        if (!session.isFee() && !session.isBuy()) {
            rlLock.setVisibility(View.VISIBLE);
        }

        // Set background image
        String urlBackground;
        if (!session.getUrlBackgroundDist().equals("") && session.getUrlBackgroundDist() != null) {
            urlBackground = session.getUrlBackgroundDist();
        } else {
            urlBackground = session.getUrlBackground();
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
        Glide.with(getContext())
            .load(urlBackground)
            .apply(requestOptions)
            .into(imageView);
//        Glide.with(getContext())
//            .load(k)
//            .into(imageView);
        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
