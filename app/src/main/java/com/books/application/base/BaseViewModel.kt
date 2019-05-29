package com.books.application.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.books.application.base.BooksApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val disposables = CompositeDisposable()
    val mApplication by lazy { getApplication<BooksApplication>() }
    val loading = MutableLiveData<Boolean>()

    protected fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun removeAll() {
        disposables.clear()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}