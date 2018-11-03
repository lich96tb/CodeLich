package com.melodymediation.sleepstories.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SessionService {
    @GET(Endpoint.SESSION)
    fun getSessions(): Single<List<SessionResponse>>

    @GET(Endpoint.SESSION)
    fun getSessionsObservable(): Observable<List<SessionResponse>>

    @GET(Endpoint.SESSION)
    fun getSessionsByCategory(@Query("categoryId") categoryId: String): Observable<List<SessionResponse>>

    @GET(Endpoint.SESSION)
    fun getSessionsByType(@Query("type") type: String): Observable<List<SessionResponse>>

    @GET(Endpoint.SESSION)
    fun getSessionsByParentId(@Query("parentId") parentId: String): Observable<List<SessionResponse>>
}
