package com.melodymediation.sleepstories.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.http.Query


interface CategoryService {
    @GET(Endpoint.CATEGORY)
    fun getCategories(): Single<List<CategoryResponse>>

    @GET(Endpoint.CATEGORY)
    fun getCategoriesCall(): Observable<List<CategoryResponse>>

    @GET(Endpoint.CATEGORY)
    fun getCategoriesByParentIdCall(@Query("parentId") parentId: String): Observable<List<CategoryResponse>>
}
