package com.example.abc.viewmodel_databinding_livedata;

public class CheckPassWord {
    private String nameCheck;
    private String emailCheck;

    public String getNameCheck() {
        return nameCheck;
    }

    public void setNameCheck(String nameCheck) {
        this.nameCheck = nameCheck;
    }

    public String getEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(String emailCheck) {
        this.emailCheck = emailCheck;
    }

    public CheckPassWord(String nameCheck, String emailCheck) {
        this.nameCheck = nameCheck;
        this.emailCheck = emailCheck;
    }

    public CheckPassWord() {
    }
}
