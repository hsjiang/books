package com.books.application.common.network.retrofit.api

import android.content.Context
import com.books.application.common.widgets.AppUtils

object BaseApi {

    val baseUrl = "http://lanxiyuedu.com"
//    val baseUrl = "http://api.51xuetang.com/"

    lateinit var deviceId: String
    lateinit var versionName: String

    fun config(context: Context) {
        deviceId = AppUtils.deviceId(context)
        versionName = AppUtils.versionName(context)

    }

    object URL {
        const val COMMON_EMPTY_URL = "/"
    }

    object PARAM {
        const val INDEX_INIT = "index/init"
        const val INDEX_INDEX = "index/index"

    }
}