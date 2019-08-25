package com.mindorks.framework.mvp.ui.login


import com.mindorks.framework.mvp.ui.base.BasePresenter
import com.mindorks.framework.mvp.utils.rx.SchedulerProvider
import com.mindorks.framework.mvp.data.DataManager

import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

class LoginPresenter<V : LoginMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), LoginMvpPresenter<V> {
    companion object {

        private val TAG = "LoginPresenter"
    }
}