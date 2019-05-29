package com.books.application.common.widgets.recyclerview

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    protected val mContext: Context = binding.root.context
}
