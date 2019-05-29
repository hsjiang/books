package com.books.application.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.books.application.common.widgets.Loading

open class BaseFragment : Fragment() {
    lateinit var mContext: BaseActivity
    var isVisibleToUser = false

    val mLoading by lazy { Loading.build(mContext) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as BaseActivity
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
    }
}