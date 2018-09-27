package com.example.solar.viewmodelfragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

public class VMD extends AndroidViewModel {
    private MutableLiveData<Integer> model=new MutableLiveData<>();
    public VMD(@NonNull Application application) {
        super(application);
        model.setValue(0);
    }
    public MutableLiveData<Integer> laydulieu(){

        return model;
    }
    public void getData(int a){
         model.setValue(a);
    }
}
