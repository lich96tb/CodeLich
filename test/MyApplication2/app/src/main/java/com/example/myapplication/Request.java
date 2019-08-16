
package com.example.myapplication;

import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class Request {

    @SerializedName("job")
    private String mJob="Kdkdkkd";
    @SerializedName("name")
    private String mName="kdjdkjfksdk";

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
