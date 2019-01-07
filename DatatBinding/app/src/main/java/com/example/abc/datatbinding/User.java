package com.example.abc.datatbinding;

import android.annotation.TargetApi;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;


public class User {

    //    // profile meta fields are ObservableField, will update the UI
//    // whenever a new value is set
    //khoi tao va lang nghe thay doi
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> image = new ObservableField<>();
    public ObservableField<String> edtSoa = new ObservableField<>("");
    public ObservableField<String> edtSob = new ObservableField<>("");

//    public ObservableField<String> getEdtSoa() {
//        return edtSoa;
//    }
//
//    public ObservableField<String> getName() {
//        return name;
//    }
//
//
//    public ObservableField<String> getEmail() {
//        return email;
//    }

    @BindingAdapter({"android:profileImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void afterTextChanged(Editable s) {
            if (!Objects.equals(edtSoa.get(), s.toString())) {
                edtSoa.set(s.toString());
            }
        }
    };

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
            if (!Objects.equals(edtSob.get(), s.toString())) {
                edtSob.set(s.toString());
            }
        }
    };


    ///cach 1

//    private String name;
//    private String email;
//
//    @Bindable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        notifyPropertyChanged(BR.name);
//    }
//
//    @Bindable
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//        notifyPropertyChanged(BR.email);
//    }
}
