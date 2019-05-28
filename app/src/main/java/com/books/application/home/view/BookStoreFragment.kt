package com.books.application.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.books.application.R
import com.books.application.base.BaseFragment
import com.books.application.databinding.FragmentBookStoreBinding
import kotlinx.android.synthetic.main.fragment_book_store.*

class BookStoreFragment : BaseFragment() {
    private lateinit var mBinding: FragmentBookStoreBinding

    companion object {
        fun newInstance() = BookStoreFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentBookStoreBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        tvAll.isSelected = true
        tvAll.setOnClickListener {
            tvAll.isSelected = true
            tvAll.setTextColor(ContextCompat.getColor(mContext, R.color.text_E84856))
            tvEnrollmentStrategy.isSelected = false
            tvEnrollmentStrategy.setTextColor(ContextCompat.getColor(mContext, R.color.text_333333))
        }
        tvEnrollmentStrategy.setOnClickListener {
            tvAll.isSelected = false
            tvAll.setTextColor(ContextCompat.getColor(mContext, R.color.text_333333))
            tvEnrollmentStrategy.isSelected = true
            tvEnrollmentStrategy.setTextColor(ContextCompat.getColor(mContext, R.color.text_E84856))
        }
    }
}