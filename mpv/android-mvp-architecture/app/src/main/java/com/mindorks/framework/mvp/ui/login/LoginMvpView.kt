package com.mindorks.framework.mvp.ui.login

import com.mindorks.framework.mvp.data.network.model.login.LoginResponse
import com.mindorks.framework.mvp.ui.base.MvpView

interface LoginMvpView : MvpView{
    fun loginSuccess(loginResponse: LoginResponse?)
}