
package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class User {

    @SerializedName("data")
    private Datas mData;

    public Datas getData() {
        return mData;
    }

    public void setData(Datas data) {
        mData = data;
    }

}
