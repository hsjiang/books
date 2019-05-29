package com.books.application.common.utils

import android.content.Context
import com.books.application.common.widgets.AppUtils
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.internal.Supplier
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
import okhttp3.OkHttpClient
import java.io.File

/**
 * Created by horizony on 2017/5/24.
 */

object FrescoUtils {

    fun getImagePipelineConfig(context: Context, okHttpClient: OkHttpClient): ImagePipelineConfig {
        val maxHeapSize = Runtime.getRuntime().maxMemory().toInt()//分配内存
        val MAX_MEMORY_CACHE_SIZE = maxHeapSize / 4//使用的缓存数量
        val bitmapCacheParams = MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // 内存缓存中总图片的最大大小,以字节为单位
                Integer.MAX_VALUE, // 内存缓存中图片的最大数量
                MAX_MEMORY_CACHE_SIZE, // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位
                Integer.MAX_VALUE, // 内存缓存中准备清除的总图片的最大数量
                Integer.MAX_VALUE)// 内存缓存中单个图片的最大大小
        //修改内存图片缓存数量,空间策略
        val mSupplierMemoryCacheParams = Supplier { bitmapCacheParams }
        val diskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(File(AppUtils.getAppCachePath(context)))
                .setBaseDirectoryName("image")
                .build()
        return OkHttpImagePipelineConfigFactory
                .newBuilder(context, okHttpClient)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)
                .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
                .build()
    }
}
