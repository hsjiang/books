package com.books.application.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.books.application.common.widgets.Loading

open class BaseActivity:AppCompatActivity(){

    val mLoading by lazy { Loading.build(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}