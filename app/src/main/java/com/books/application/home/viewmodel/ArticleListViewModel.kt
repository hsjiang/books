package com.books.application.home.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.books.application.base.BaseViewModel
import com.books.application.constants.BundleKey
import com.books.application.home.model.model.Article
import com.books.application.home.model.repo.BooksRepository

class ArticleListViewModel(application: Application) : BaseViewModel(application) {
    private val repo by lazy { BooksRepository.getInstance(application) }

    var articles = MutableLiveData<MutableList<Article>>()

    private var index: Int = 0

    fun initData(arguments: Bundle?) {
        arguments?.let {
            index = it.getInt(BundleKey.KEY_INDEX)
        }
    }

    fun getArticles() {
        loading.value = true
        add(
            repo.getIndexIndex(0, index)
                .subscribe({
                    loading.value = false
                    articles.value = it.data?.list
                }, {
                    loading.value = false
                })
        )
    }
}