package com.books.application.common.network.retrofit.api

import com.books.application.common.network.retrofit.RetrofitResponse
import com.books.application.home.model.model.Articles
import com.books.application.home.model.model.Categories
import io.reactivex.Observable
import retrofit2.http.*
import java.util.*

interface BooksApi {

    @GET(BaseApi.URL.COMMON_EMPTY_URL)
    fun getIndexInit(@QueryMap map: HashMap<String, String>): Observable<RetrofitResponse<Categories>>

    @GET(BaseApi.URL.COMMON_EMPTY_URL)
    fun getIndexIndex(@QueryMap map: HashMap<String, String>): Observable<RetrofitResponse<Articles>>
}