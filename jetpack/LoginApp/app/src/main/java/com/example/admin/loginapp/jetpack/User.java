package com.example.admin.loginapp.jetpack;

public class User {
    private String name,mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
}
