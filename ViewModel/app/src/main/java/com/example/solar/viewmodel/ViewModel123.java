package com.example.solar.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.Editable;

public class ViewModel123 extends AndroidViewModel {
    private MutableLiveData<Integer> soa = new MutableLiveData<>();

    public ViewModel123(@NonNull Application application) {
        super(application);
        soa.setValue(0);
    }

    public MutableLiveData<Integer> getA() {

        return soa;
    }

    public void chuyenvao(int a) {
        soa.setValue(soa.getValue() + a);
    }
}
