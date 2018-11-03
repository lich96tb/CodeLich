package com.melodymediation.sleepstories.data.remote

import io.reactivex.Observable
import retrofit2.http.GET

interface VersionService {

    @GET(Endpoint.VERSION)
    fun getVersionsObservable(): Observable<List<VersionResponse>>
}
