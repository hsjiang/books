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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        tvBookStore.setOnClickListener { onItemSelected(0) }
        tvBookShelf.setOnClickListener { onItemSelected(1) }
        onItemSelected(0)
    }

    private fun onItemSelected(item: Int) {
        when (item) {
            0 -> {
                tvBookStore.setTextColor(ContextCompat.getColor(this, R.color.text_E84856))
                tvBookShelf.setTextColor(ContextCompat.getColor(this, R.color.text_333333))
                switchFragment(BookStoreFragment.newInstance())
            }
            1 -> {
                tvBookStore.setTextColor(ContextCompat.getColor(this, R.color.text_333333))
                tvBookShelf.setTextColor(ContextCompat.getColor(this, R.color.text_E84856))
                switchFragment(BookShelfFragment.newInstance())
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentContainer, fragment, "").commit()
    }
}
