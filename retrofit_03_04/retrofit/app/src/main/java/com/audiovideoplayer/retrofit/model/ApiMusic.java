package com.audiovideoplayer.retrofit.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
@Entity
public class ApiMusic {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String id;
    @SerializedName("duration")
    private String duration;

    @SerializedName("isBuy")
    private boolean isBuy;

    @SerializedName("name")
    private String name;


    @SerializedName("url")
    private String url;

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public boolean isIsBuy() {
        return isBuy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    @Override
    public String toString() {
        return
                "Response{" +
                        "duration = '" + duration + '\'' +
                        ",isBuy = '" + isBuy + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}