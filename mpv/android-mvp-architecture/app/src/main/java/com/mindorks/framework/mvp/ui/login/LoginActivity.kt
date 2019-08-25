package com.mindorks.framework.mvp.ui.login

import android.os.Bundle

import javax.inject.Inject

import butterknife.ButterKnife

import com.mindorks.framework.mvp.ui.base.BaseActivity

import android.content.Intent
import android.content.Context
import android.os.PersistableBundle

import com.mindorks.framework.mvp.R

class LoginActivity : BaseActivity(), LoginMvpView {
    override fun openActivityOnTokenExpire() {
    }

    @set:Inject
    internal var mPresenter: LoginPresenter<LoginMvpView>? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)

        activityComponent.inject(this)
        setUnBinder(ButterKnife.bind(this))
        mPresenter!!.onAttach(this@LoginActivity)

    }

    override fun onDestroy() {
        mPresenter!!.onDetach()
        super.onDestroy()
    }

    override fun setUp() {

    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
}