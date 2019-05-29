package com.books.application.home.model.repo

import android.app.Application
import com.books.application.base.BaseRepository
import com.books.application.common.network.retrofit.ParamsBuilder
import com.books.application.common.network.retrofit.RetrofitFactory
import com.books.application.common.network.retrofit.RetrofitResponse
import com.books.application.common.network.retrofit.api.BaseApi
import com.books.application.common.network.retrofit.transformer.ResponseTransformer
import com.books.application.common.network.retrofit.transformer.SchedulerTransformer
import com.books.application.constants.ParamsKey
import com.books.application.home.model.model.Articles
import com.books.application.home.model.model.Categories
import io.reactivex.Observable

class BooksRepository(application: Application) : BaseRepository(application) {
    companion object {
        @Volatile
        private var instance: BooksRepository? = null

        fun getInstance(application: Application) = instance ?: synchronized(this) {
            instance ?: BooksRepository(application).also { instance = it }
        }
    }

    fun getIndexInit(): Observable<RetrofitResponse<Categories>> {
        val map = ParamsBuilder.with(mApplication).apply {
            put(ParamsKey.KEY_R, BaseApi.PARAM.INDEX_INIT)
            put(ParamsKey.KEY_UUID, "E40D3A76-8F9F-4897-A470-F01B7643F022")
        }
        return RetrofitFactory.mApi.getIndexInit(map)
            .compose(SchedulerTransformer.applySchedulers())
            .compose(ResponseTransformer.transform())
    }

    fun getIndexIndex(offset: Int, index: Int): Observable<RetrofitResponse<Articles>> {
        val map = ParamsBuilder.with(mApplication).apply {
            put(ParamsKey.KEY_R, BaseApi.PARAM.INDEX_INDEX)
            put(ParamsKey.KEY_FIRST, offset.toString())
            put(ParamsKey.KEY_INDEX, index.toString())
        }
        return RetrofitFactory.mApi.getIndexIndex(map)
            .compose(SchedulerTransformer.applySchedulers())
            .compose(ResponseTransformer.transform())
    }

}