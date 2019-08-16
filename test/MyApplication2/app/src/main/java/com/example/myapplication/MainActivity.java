package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.rxandroidnetworking.RxAndroidNetworking;

import org.json.JSONObject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });

        Gson gson = new Gson();
        LoginTestRequest loginTestRequest = new LoginTestRequest();


        String jsonRequest = gson.toJson(loginTestRequest);
        AndroidNetworking.post("https://webtrade.vps.com.vn/Api/Proxy").setContentType("application/json; charset=utf-8")
               // .addBodyParameter(loginTestRequest)
                .addStringBody(jsonRequest)
                .build()
                .getAsObject(LoginTestRespone.class,new ParsedRequestListener(){

                    @Override
                    public void onResponse(Object response) {
                      LoginTestRespone loginTestRequest1= (LoginTestRespone) response;
                        Log.e("AAAAA "," "+  loginTestRequest1.getData().getName());
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.toString();
                    }
                });
    }

    private void postData() {
        postCricketFansObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {
                        // do anything onComplete
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle error
                    }

                    @Override
                    public void onNext(JSONObject response) {
                        //do anything with response
                        Log.e("AAAAAAdd", " " + response);
                    }
                });
    }

    private void getData() {
        getCricketFansObservable()
                .subscribeOn(Schedulers.io()) // do the network call on another thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        // do anything onComplete
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle error
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e("AAAAAAA", " " + user);
                    }
                });
    }

    private Observable<User> getCricketFansObservable() {
        return RxAndroidNetworking.get("https://reqres.in/api/users/2")
                .build()
                .getObjectObservable(User.class);
    }


    private Observable<JSONObject> postCricketFansObservable() {
        return RxAndroidNetworking.post("https://reqres.in/api/users/")
                .addBodyParameter(new Request())
                .build()
                .getJSONObjectObservable();
    }
}