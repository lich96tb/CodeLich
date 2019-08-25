/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.mindorks.framework.mvp.data.network;

import com.google.gson.Gson;
import com.mindorks.framework.mvp.data.network.model.BlogResponse;
import com.mindorks.framework.mvp.data.network.model.LogoutResponse;
import com.mindorks.framework.mvp.data.network.model.OpenSourceResponse;
import com.mindorks.framework.mvp.data.network.model.listpakage.PackageRequest;
import com.mindorks.framework.mvp.data.network.model.listpakage.PackageResponse;
import com.mindorks.framework.mvp.data.network.model.login.LoginResponse;
import com.mindorks.framework.mvp.data.network.model.login.LoginResquest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<LoginResponse> doServiceLoginApiCalls(LoginResquest request) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        JSONObject requestJson = null;
        try {
            requestJson = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Rx2AndroidNetworking.post("https://webtrade.vps.com.vn/Api/Proxy").setContentType("application/json; charset=utf-8")
                .addJSONObjectBody(requestJson).build().getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<PackageResponse> doServiceGetPackageApiCalls(PackageRequest request) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        JSONObject requestJson = null;
        try {
            requestJson = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Rx2AndroidNetworking.post("https://webtrade.vps.com.vn/Api/Proxy").setContentType("application/json; charset=utf-8")
                .addJSONObjectBody(requestJson).build().getObjectSingle(PackageResponse.class);
    }


    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }
}

