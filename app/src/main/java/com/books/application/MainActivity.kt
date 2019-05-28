package com.books.application

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.books.application.base.BaseActivity
import com.books.application.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
