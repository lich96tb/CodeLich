package com.melodymediation.sleepstories.utilities;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by AMBE on 10/3/2018 at 3:06 PM.
 */
public class ImageAnim {
    public static void animShake(View view) {

        int dur1 = 70 + (int) (Math.random() * 30);
        int dur2 = 70 + (int) (Math.random() * 30);

// Create an animation instance
        Animation an = new RotateAnimation(-3, 3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

// Set the animation's parameters
        an.setDuration(dur1);               // duration in ms
        an.setRepeatCount(-1);                // -1 = infinite repeated
        an.setRepeatMode(Animation.REVERSE);
        an.setFillAfter(true);               // keep rotation after animation


// Create an animation instance
        Animation an2 = new TranslateAnimation(-TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            -TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            TranslateAnimation.RELATIVE_TO_SELF, 0.02f);

// Set the animation's parameters
        an2.setDuration(dur2);               // duration in ms
        an2.setRepeatCount(-1);                // -1 = infinite repeated
        an2.setRepeatMode(Animation.REVERSE);
        an2.setFillAfter(true);               // keep rotation after animation

        AnimationSet s = new AnimationSet(false);//false means don't share interpolators
        s.addAnimation(an);
        s.addAnimation(an2);

// Apply animation to image view
        view.setAnimation(s);
    }

}
