package com.mindorks.framework.mvp.ui.packageservice


import android.util.Log
import com.mindorks.framework.mvp.ui.base.BasePresenter
import com.mindorks.framework.mvp.utils.rx.SchedulerProvider
import com.mindorks.framework.mvp.data.DataManager
import com.mindorks.framework.mvp.data.network.model.listpakage.PackageRequest
import com.mindorks.framework.mvp.data.network.model.listpakage.PackageResponse
import com.mindorks.framework.mvp.data.network.model.login.LoginResponse
import com.mindorks.framework.mvp.utils.session
import com.mindorks.framework.mvp.utils.username

import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

class PackageServicePresenter<V : PackageServiceMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), PackageServiceMvpPresenter<V> {
    init {
        var packageResquest = PackageRequest()
        packageResquest.user = username
        packageResquest.session = session
        packageResquest.data?.p1 = username + "1"
        compositeDisposable.add(dataManager.doServiceGetPackageApiCalls(packageResquest)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    Log.e("kasjdkfsd ", " " + it.data!!.size)

                }, { throws: Throwable? ->
                    mvpView.onError(throws?.message)
                    mvpView.hideLoading()
                }))

    }
}