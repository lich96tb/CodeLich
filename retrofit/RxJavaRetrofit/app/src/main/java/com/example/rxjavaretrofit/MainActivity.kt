package com.example.rxjavaretrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.DataAdapter
import com.example.rxjavaretrofit.model.Android
import com.example.rxjavaretrofit.network.RequestInterface
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.learn2crack.com/"
    private var mCompositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var disposable = retrofit.create(RequestInterface::class.java).register()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            //.subscribe{i-> Log.e("kdkkddkdk",""+i.size)}
            .subscribe(this::handleResponse, this::handleError, this::handleSuccess)
        mCompositeDisposable?.add(disposable)



        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }


    private fun handleResponse(androidList: List<Android>) {
        var mAndroidArrayList = ArrayList(androidList)
        recyclerView.adapter = DataAdapter(mAndroidArrayList)

    }

    private fun handleError(error: Throwable) {
        Toast.makeText(this, "Error " + error.localizedMessage!!, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess() {
        Toast.makeText(this, "Get data success! ", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
