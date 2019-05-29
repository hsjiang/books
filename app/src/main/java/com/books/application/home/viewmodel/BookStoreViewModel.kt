package com.books.application.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.books.application.base.BaseViewModel
import com.books.application.home.model.repo.BooksRepository

class BookStoreViewModel(application: Application) : BaseViewModel(application) {
    val repo by lazy { BooksRepository.getInstance(application) }

    val categories = MutableLiveData<Array<String>>()

    fun getIndexInit() {
        loading.value = true
        add(
            repo.getIndexInit()
                .subscribe({
                    loading.value = false
                    categories.value = it.data?.category
                }, {
                    loading.value = false
                })
        )
    }
}