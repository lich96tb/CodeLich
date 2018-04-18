package com.example.lich96tb.mvp.Model;

/**
 * Created by LICH96TB on 4/19/2018.
 */

public class ModelLogin {
    String name1="lich",pass1="123456";
    ModelResponseTopresenterLogin modelResponseTopresenterLogin;

    public ModelLogin(ModelResponseTopresenterLogin modelResponseTopresenterLogin) {
        this.modelResponseTopresenterLogin = modelResponseTopresenterLogin;
    }

    public void handleLogin(String name, String pass){
        if (name.equals(name1)&&pass.equals(pass1)){
            modelResponseTopresenterLogin.loginSuccess();
        }else
            modelResponseTopresenterLogin.loginFailed();
    }
}
