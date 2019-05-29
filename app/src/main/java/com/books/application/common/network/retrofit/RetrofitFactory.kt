package com.books.application.common.network.retrofit

import com.books.application.common.network.OKHttpClientFactory
import com.books.application.common.network.retrofit.api.BaseApi
import com.books.application.common.network.retrofit.api.BooksApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    val mApi by lazy {
        getRetrofit(BaseApi.baseUrl).create(BooksApi::class.java)
    }

    val gson: Gson by lazy {
        val builder = GsonBuilder()
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        builder.create()
    }

    private fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(gson))
            baseUrl(url)
            client(createClient())
        }.build()
    }

    private fun createClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
//        val requestInterceptor = RequestInterceptor()
//        okHttpClientBuilder.addInterceptor(requestInterceptor)
//        okHttpClientBuilder.addInterceptor(logging)
//        return okHttpClientBuilder.build()
        return OKHttpClientFactory.okHttpClient()
    }

    private class RequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var originalRequest = chain.request()
            // response
            val response = chain.proceed(originalRequest)
            var responseString = response.body()?.string() ?: ""
            val mediaType = response.body()?.contentType()
            val responseBody = ResponseBody.create(mediaType, responseString)
            return response.newBuilder().body(responseBody).build()
        }
    }

}
