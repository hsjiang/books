package com.books.application.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.books.application.common.network.OKHttpClientFactory
import com.books.application.common.network.retrofit.api.BaseApi
import com.books.application.common.utils.BaseActivityLifecycleCallback
import com.books.application.common.utils.FrescoUtils
import com.books.application.common.utils.MyActivityManager
import com.facebook.drawee.backends.pipeline.Fresco

class BooksApplication : MultiDexApplication() {
    protected var activityLifecycleCallbacks: BaseActivityLifecycleCallback? = null

    override fun onCreate() {
        super.onCreate()
        gContext = applicationContext
        BaseApi.config(this)
        OKHttpClientFactory.init(this)
        //初始化fresco
        val imagePipelineConfig = FrescoUtils.getImagePipelineConfig(
            this,
            OKHttpClientFactory.okHttpClient()
        )
        Fresco.initialize(this, imagePipelineConfig)


        registerActivityLifecycleCallbacks(BaseActivityLifecycleCallback(activityManager))
    }

    companion object {
        var gContext: Context? = null

        fun getApplicationContext(): Context {
            return gContext!!
        }

        val activityManager: MyActivityManager = MyActivityManager()

    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}