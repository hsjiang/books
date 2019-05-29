package com.books.application.common.network.retrofit.transformer

import com.books.application.common.network.retrofit.RetrofitResponse
import com.books.application.common.network.retrofit.exception.ApiError
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

object ResponseTransformer {

    fun <T> transform(): ObservableTransformer<RetrofitResponse<T>, RetrofitResponse<T>> {
        return ObservableTransformer {
            it.onErrorResumeNext(ErrorResumeFunction1<RetrofitResponse<T>>())
                .flatMap(ResponseFunction1())
        }
    }

    private class ErrorResumeFunction1<T> : Function<Throwable, ObservableSource<out T>> {

        @Throws(Exception::class)
        override fun apply(throwable: Throwable): ObservableSource<out T> {
            return Observable.error<T>(ApiError.handleException(throwable))
        }
    }

    private class ResponseFunction1<T> : Function<RetrofitResponse<T>, ObservableSource<RetrofitResponse<T>>> {

        override fun apply(t: RetrofitResponse<T>): ObservableSource<RetrofitResponse<T>> {
            val status = t.status
            return when (status) {
                0 -> {
                    Observable.just(t)
                }
                else -> {
                    Observable.error<RetrofitResponse<T>>(ApiError.handleException(status, ""))
                }
            }
        }
    }
}