package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.Subscriber
import rx.Observable.OnSubscribe as OnSubscribe1
import rx.functions.Action1
import rx.functions.Func1
import rx.functions.Func0


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxView.clicks(txtName).subscribe { v -> Toast.makeText(this@MainActivity, "Kkk", Toast.LENGTH_LONG).show() }

        mainFunctionRX()


//        Observable.just(1)
//            .map(Func1<Int, String> { integer ->
//                Log.e("KKKKKKKKKKK1", Thread.currentThread().name)
//                integer.toString()
//            })
//            .subscribe(Action1<String> { Log.e("KKKKKKKKKKK2", Thread.currentThread().name) })
    }


    private fun mainFunctionRX() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).observeOn(Schedulers.io())
            ?.subscribeOn(AndroidSchedulers.mainThread())
            ?.filter { i -> i % 2 == 0 }
            ?.subscribe(this::handleResponse, this::handleError, this::handleSuccess)
//            ?.subscribe(object : Observer<Int> {
//
//                override fun onError(e: Throwable?) {
//
//                }
//
//                override fun onNext(t: Int?) {
//                    Log.e("LLLLLLLLLL", "Even Numbers = [ $t]")
//                }
//
//                override fun onCompleted() {
//                }
//            })


        //in ra cac so chan
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter { i -> i % 2 == 0 }

            .subscribe { i -> Log.e("Result", "Even Numbers = [ $i ]") }

        //duyt het phan tu trong list
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .forEach { i -> Log.e("Iterating", "" + i!!) }

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .groupBy<Any> { i -> i!! % 2 == 0 }
            .subscribe { grouped ->
                grouped.toList()
                    .subscribe { integers -> Log.e("AAA", (integers + "Even(" + grouped.key + ")").toString()) }
            }

        //Chọn N phần tử đầu tiên
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).take(5)
            .subscribe { i -> Log.e("BBB", "" + i!!) }

        //lay pha tu last
        Observable.just(1, 2, 3, 4, 5, 6)
            .last().subscribe { i -> Log.e("CCCC", "" + i!!) }


        //lay cac phan tu khac nhau Distinct
        Observable.just(1, 2, 6, 1, 2, 3, 4, 4, 8).distinct()
            .subscribe { i -> Log.e("DDDD", "" + i!!) }

        Observable.just(1, 2, 3, 4)
            .map<Any> { i -> i!! * i }
            //   .map<Any> { i -> Math.sqrt(i as Double) }
            .subscribe { i -> Log.e("FFF", "" + i) }
    }


    private fun handleResponse(t: Int) {
        Log.e("LLLLLLLLLL", "Even Numbers = [ $t]")

    }


    private fun handleError(error: Throwable) {
        Toast.makeText(this, "Error " + error.localizedMessage!!, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess() {
        Toast.makeText(this, "Get data success! ", Toast.LENGTH_SHORT).show()
    }
}
