package com.melodymediation.sleepstories.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.ui.view.GifView;
import com.melodymediation.sleepstories.ui.view.MyAlertDialog;

import butterknife.BindView;

/**
 * Created by AMBE on 10/5/2018 at 4:48 PM.
 */
public class NoInternetDialog extends MyAlertDialog {
    @BindView(R.id.img_no_internet)
    GifView imgNoInternet;

    public NoInternetDialog(Context context) {
        super(context);
    }

    @Override
    protected int provideLayout() {
        return R.layout.dialog_no_internet;
    }

    @Override
    protected void setupViews() {

//        Glide.with(getContext())
//            .load(R.drawable.nointernet)
//            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
//            .into(imgNoInternet).clearOnDetach();

// ImageView from layout
// create AnimatedDrawable
        imgNoInternet.setImageResource(R.drawable.nointernet);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.MATCH_PARENT;

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(width, height);


    }

    @Override
    protected void setAnimation() {

    }
}
