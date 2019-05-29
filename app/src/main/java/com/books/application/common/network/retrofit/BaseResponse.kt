package com.books.application.common.network.retrofit

data class RetrofitResponse<T>(var status: Int, var data: T?)