package com.books.application.common.network

import android.annotation.SuppressLint
import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object OKHttpClientFactory {

    private lateinit var INSTANCE: OkHttpClient

    fun okHttpClient(): OkHttpClient {
        return INSTANCE
    }

    fun init(application: Application) {
        INSTANCE = okHttpClient(application)
    }

    private fun okHttpClient(application: Application): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout((10 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .readTimeout((10 * 1000).toLong(), TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
//        builder.addInterceptor(CacheStrategyInterceptor(application))
//        val version = AppUtils.getVersionName(application)
//        builder.addNetworkInterceptor(StethoInterceptor())
//        builder.addInterceptor(PTDataSdkInterceptor(application))

        builder.cache(cache(application))
        createSSLSocketFactory()?.let {
            builder.sslSocketFactory(it, TrustAllManager())
        }
        builder.hostnameVerifier(TrustAllHostnameVerifier())

        return builder.build()
    }

    private fun cache(application: Application): Cache {
        val cacheSize = 20 * 1024 * 1024 // 20 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @SuppressLint("TrulyRandom")
    private fun createSSLSocketFactory(): SSLSocketFactory? {

        var sSLSocketFactory: SSLSocketFactory? = null;

        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(TrustAllManager()), SecureRandom())
            sSLSocketFactory = sc.socketFactory;
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sSLSocketFactory
    }

    private class TrustAllManager : X509TrustManager {

        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun getAcceptedIssuers(): Array<X509Certificate>? {
            return arrayOf()
        }
    }

    private class TrustAllHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean {
            return true
        }
    }
}