package com.books.application.home.view

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.books.application.R
import com.books.application.base.BaseActivity
import com.books.application.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private val bookStoreFragment = BookStoreFragment.newInstance()
    private val bookShelfFragment = BookShelfFragment.newInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        tvBookStore.setOnClickListener { onItemSelected(0) }
        tvBookShelf.setOnClickListener { onItemSelected(1) }
        supportFragmentManager.beginTransaction().add(R.id.flFragmentContainer, bookStoreFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flFragmentContainer, bookShelfFragment).commit()
        onItemSelected(0)

    }

    private fun onItemSelected(item: Int) {
        when (item) {
            0 -> {
                tvBookStore.setTextColor(ContextCompat.getColor(this, R.color.text_E84856))
                tvBookShelf.setTextColor(ContextCompat.getColor(this, R.color.text_333333))
                switchFragment(0)
            }
            1 -> {
                tvBookStore.setTextColor(ContextCompat.getColor(this, R.color.text_333333))
                tvBookShelf.setTextColor(ContextCompat.getColor(this, R.color.text_E84856))
                switchFragment(1)
            }
        }
    }

    private fun switchFragment(position: Int) {
        when (position) {
            0 -> {
                if (!bookShelfFragment.isDetached) {
                    supportFragmentManager.beginTransaction().hide(bookShelfFragment).commit()
                }
                supportFragmentManager.beginTransaction().show(bookStoreFragment).commit()
            }
            1 -> {
                if (!bookStoreFragment.isDetached) {
                    supportFragmentManager.beginTransaction().hide(bookStoreFragment).commit()
                }
                supportFragmentManager.beginTransaction().show(bookShelfFragment).commit()
            }
        }
    }
}
