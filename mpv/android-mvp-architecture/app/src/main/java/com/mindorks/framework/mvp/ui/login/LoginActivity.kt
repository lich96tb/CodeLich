package com.mindorks.framework.mvp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import com.google.gson.Gson
import com.mindorks.framework.mvp.data.network.model.login.LoginResponse
import com.mindorks.framework.mvp.ui.base.BaseActivity
import com.mindorks.framework.mvp.ui.packageservice.PackageServiceActivity
import com.mindorks.framework.mvp.utils.session
import com.mindorks.framework.mvp.utils.username
import com.rx2androidnetworking.Rx2AndroidNetworking
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginMvpView {
    override fun loginSuccess(loginResponse: LoginResponse?) {
        hideLoading()
        username=edtUser.text.toString().trim()
        session= loginResponse?.data.let {it!!.sid.toString()}
        Log.e("ASDFSD ","dang nhap thanh cong")
        startActivity(Intent(this@LoginActivity,PackageServiceActivity::class.java))
    }

    override fun openActivityOnTokenExpire() {
    }

    @set:Inject
    internal var mPresenter: LoginPresenter<LoginMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mindorks.framework.mvp.R.layout.activity_login)
        activityComponent.inject(this)
        setUnBinder(ButterKnife.bind(this))
        mPresenter!!.onAttach(this@LoginActivity)
        btnLogin.setOnClickListener {
            showLoading()
            mPresenter!!.login(edtUser.text.trim().toString(), edtPassword.text.toString().trim())
        }

    }

    override fun onDestroy() {
        mPresenter!!.onDetach()
        super.onDestroy()
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
}