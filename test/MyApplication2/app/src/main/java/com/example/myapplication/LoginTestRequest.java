package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginTestRequest {

    public static class Data {

        @SerializedName("type")
        @Expose
        private String type= "string";
        @SerializedName("cmd")
        @Expose
        private String cmd="Web.sCheckLogin";
        @SerializedName("p1")
        @Expose
        private String p1="003895";
        @SerializedName("p2")
        @Expose
        private String p2="1234567";
        @SerializedName("p3")
        @Expose
        private String p3="S";
        @SerializedName("p4")
        @Expose
        private String p4="127.0.0.1";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getP3() {
            return p3;
        }

        public void setP3(String p3) {
            this.p3 = p3;
        }

        public String getP4() {
            return p4;
        }

        public void setP4(String p4) {
            this.p4 = p4;
        }

    }


        @SerializedName("group")
        @Expose
        private String group="L";
        @SerializedName("user")
        @Expose
        private String user="null";
        @SerializedName("session")
        @Expose
        private String session="null";
        @SerializedName("data")
        @Expose
        private Data data=new Data();

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

}
