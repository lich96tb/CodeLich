package com.mindorks.framework.mvp.ui.login


import android.util.Log
import com.mindorks.framework.mvp.ui.base.BasePresenter
import com.mindorks.framework.mvp.utils.rx.SchedulerProvider
import com.mindorks.framework.mvp.data.DataManager
import com.mindorks.framework.mvp.data.network.model.login.LoginResponse
import com.mindorks.framework.mvp.data.network.model.login.LoginResquest

import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception
import java.util.ArrayList

import javax.inject.Inject

class LoginPresenter<V : LoginMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), LoginMvpPresenter<V> {
    override fun login(user: String, password: String) {
        var loginResquest = LoginResquest()
        loginResquest.data!!.p1=user
        loginResquest.data!!.p2=password
        compositeDisposable.add(dataManager.doServiceLoginApiCalls(loginResquest)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ spRespone : LoginResponse? ->
                    mvpView.loginSuccess(spRespone)


                },{ throws: Throwable? ->
                    mvpView.onError(throws?.message)
                    mvpView.hideLoading()
                }))
    }
}