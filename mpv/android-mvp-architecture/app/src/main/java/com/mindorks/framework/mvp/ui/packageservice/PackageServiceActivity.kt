package com.mindorks.framework.mvp.ui.packageservice

import android.os.Bundle

import javax.inject.Inject

import com.mindorks.framework.mvp.ui.base.BaseActivity


import com.mindorks.framework.mvp.R

class PackageServiceActivity : BaseActivity(), PackageServiceMvpView {
    override fun openActivityOnTokenExpire() {

    }

    @set:Inject
    internal var mPresenter: PackageServicePresenter<PackageServiceMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_service)
        activityComponent.inject(this)
        mPresenter!!.onAttach(this@PackageServiceActivity)
    }

    override fun onDestroy() {
        mPresenter!!.onDetach()
        super.onDestroy()
    }
}