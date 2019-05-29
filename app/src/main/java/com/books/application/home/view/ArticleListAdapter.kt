package com.books.application.home.view

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.books.application.R
import com.books.application.common.widgets.recyclerview.BaseAdapter
import com.books.application.common.widgets.recyclerview.BaseViewHolder
import com.books.application.databinding.LayoutArticleListItemBinding
import com.books.application.home.model.model.Article

class ArticleListAdapter(mContext: Context, items: MutableList<Article>?) :
    BaseAdapter<Article, ArticleListAdapter.ItemViewHolder>(mContext, items) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, item: Article?) {
        holder.binding.data = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<LayoutArticleListItemBinding>(
            mLayoutInflater,
            R.layout.layout_article_list_item, parent, false
        )
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(binding: LayoutArticleListItemBinding) :
        BaseViewHolder<LayoutArticleListItemBinding>(binding)
}