
package com.example.myapplication.getList;


import com.google.gson.annotations.SerializedName;

public class ListData {

    @SerializedName("data")
    private Data mData=new Data();
    @SerializedName("group")
    private String mGroup="B";
    @SerializedName("session")
    private String mSession="2b9c95f0-2174-48c8-b3d0-84cb5838e79e";
    @SerializedName("user")
    private String mUser="003895";

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }

    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        mSession = session;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }
    public class Data {

        @SerializedName("cmd")
        private String mCmd="";
        @SerializedName("p1")
        private String mP1="";
        @SerializedName("p2")
        private String mP2="";
        @SerializedName("p3")
        private String mP3="";
        @SerializedName("p4")
        private String mP4="";
        @SerializedName("type")
        private String mType="cursor";

        public String getCmd() {
            return mCmd;
        }

        public void setCmd(String cmd) {
            mCmd = cmd;
        }

        public String getP1() {
            return mP1;
        }

        public void setP1(String p1) {
            mP1 = p1;
        }

        public String getP2() {
            return mP2;
        }

        public void setP2(String p2) {
            mP2 = p2;
        }

        public String getP3() {
            return mP3;
        }

        public void setP3(String p3) {
            mP3 = p3;
        }

        public String getP4() {
            return mP4;
        }

        public void setP4(String p4) {
            mP4 = p4;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

    }
}
