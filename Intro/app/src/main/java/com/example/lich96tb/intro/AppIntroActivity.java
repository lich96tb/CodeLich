package com.example.lich96tb.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by FRAMGIA\ngo.dinh.ngoc on 25/06/2017.
 */

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(AppIntroFragment.newInstance(getString(R.string.email), getString(R.string.email_hint), R.drawable.email, ContextCompat.getColor(getApplicationContext(), R.color.light_green)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.calender), getString(R.string.calender_hint), R.drawable.calendar, ContextCompat.getColor(getApplicationContext(), R.color.light_purple)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.shopping), getString(R.string.shopping_hint), R.drawable.shopping, ContextCompat.getColor(getApplicationContext(), R.color.light_orange)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.social_network), getString(R.string.social_network_hint), R.drawable.socialnetwork, ContextCompat.getColor(getApplicationContext(), R.color.light_cyan)));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Toast.makeText(this, "ssdfsdfsdfdsf", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Toast.makeText(this, "ssdfsdfsdfdsf", Toast.LENGTH_SHORT).show();
     ///   startActivity(new Intent(AppIntroActivity.this, Main2Activity.class));
      //  this.finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
