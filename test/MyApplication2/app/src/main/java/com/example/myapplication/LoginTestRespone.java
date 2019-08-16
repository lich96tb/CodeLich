package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginTestRespone {

    public class Data {

        @SerializedName("user")
        @Expose
        private String user;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("sid")
        @Expose
        private String sid;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("defaultAcc")
        @Expose
        private String defaultAcc;
        @SerializedName("iFlag")
        @Expose
        private Integer iFlag;
        @SerializedName("CountLoginFail")
        @Expose
        private Integer countLoginFail;
        @SerializedName("AuthenType")
        @Expose
        private String authenType;
        @SerializedName("IP")
        @Expose
        private String iP;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDefaultAcc() {
            return defaultAcc;
        }

        public void setDefaultAcc(String defaultAcc) {
            this.defaultAcc = defaultAcc;
        }

        public Integer getIFlag() {
            return iFlag;
        }

        public void setIFlag(Integer iFlag) {
            this.iFlag = iFlag;
        }

        public Integer getCountLoginFail() {
            return countLoginFail;
        }

        public void setCountLoginFail(Integer countLoginFail) {
            this.countLoginFail = countLoginFail;
        }

        public String getAuthenType() {
            return authenType;
        }

        public void setAuthenType(String authenType) {
            this.authenType = authenType;
        }

        public String getIP() {
            return iP;
        }

        public void setIP(String iP) {
            this.iP = iP;
        }

    }


        @SerializedName("cmd")
        @Expose
        private String cmd;
        @SerializedName("oID")
        @Expose
        private String oID;
        @SerializedName("rc")
        @Expose
        private Integer rc;
        @SerializedName("rs")
        @Expose
        private String rs;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getOID() {
            return oID;
        }

        public void setOID(String oID) {
            this.oID = oID;
        }

        public Integer getRc() {
            return rc;
        }

        public void setRc(Integer rc) {
            this.rc = rc;
        }

        public String getRs() {
            return rs;
        }

        public void setRs(String rs) {
            this.rs = rs;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

}
