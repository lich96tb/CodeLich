package com.example.lich96tb.mvp.presenter;

import com.example.lich96tb.mvp.Model.ModelLogin;
import com.example.lich96tb.mvp.Model.ModelResponseTopresenterLogin;

/**
 * Created by LICH96TB on 4/19/2018.
 */

public class PresenterLogin implements ModelResponseTopresenterLogin {

    PresenterLoginResponseToView presenterLoginResponseToView;

    public PresenterLogin(PresenterLoginResponseToView presenterLoginResponseToView) {
        this.presenterLoginResponseToView = presenterLoginResponseToView;
    }

    public void receivedHenle(String name, String pass) {
        ModelLogin modelLogin = new ModelLogin(this);
        modelLogin.handleLogin(name, pass);
    }

    @Override
    public void loginSuccess() {
        presenterLoginResponseToView.onLoginSuccess();
    }

    @Override
    public void loginFailed() {
        presenterLoginResponseToView.onloginFailed();
    }
}
