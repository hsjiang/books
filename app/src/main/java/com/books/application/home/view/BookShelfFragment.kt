package com.books.application.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.books.application.base.BaseFragment
import com.books.application.databinding.FragmentBookShelfBinding

class BookShelfFragment : BaseFragment() {
    private lateinit var mBinding: FragmentBookShelfBinding

    companion object {
        @JvmStatic
        fun newInstance() = BookShelfFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentBookShelfBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {

    }
}
