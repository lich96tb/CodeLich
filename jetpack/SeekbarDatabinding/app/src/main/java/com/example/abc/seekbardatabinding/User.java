package com.example.abc.seekbardatabinding;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.SeekBar;

import java.util.Objects;

public class User {
    public ObservableField<String> position = new ObservableField<>();

    public void onValueChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
        if (fromUser)
            Log.e("ASDFasd", " " + progresValue);
        // seekBarValue.set(progresValue + "");
    }


    public TextWatcher watcherEdtb = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void afterTextChanged(Editable s) {
            Log.e("ASDFasd", " " + s);
        }
    };
}
