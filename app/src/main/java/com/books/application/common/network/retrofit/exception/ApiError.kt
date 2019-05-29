package com.books.application.common.network.retrofit.exception

class ApiError : Exception {
    var mCode: Int = -1
    var mMsg: String? = null

    companion object {
        fun handleException(e: Throwable): ApiError {
            e.printStackTrace()
            return ApiError("网络错误，请检查网络")
        }

        fun handleException(code: Int, msg: String?): ApiError {
            return ApiError(code, msg)
        }
    }

    constructor(msg: String?) : super(msg) {
        mMsg = msg
    }

    constructor(code: Int, msg: String?) : super(msg) {
        mCode = code
        mMsg = msg
    }
}