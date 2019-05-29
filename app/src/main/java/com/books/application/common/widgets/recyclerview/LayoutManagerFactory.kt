package com.books.application.common.widgets.recyclerview

import android.content.Context
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

object LayoutManagerFactory {
    fun horizontalLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    fun verticalLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    fun gridLayoutManager(context: Context, spanCount: Int): GridLayoutManager {
        return GridLayoutManager(context, spanCount)
    }

    fun gridLayoutManager(context: Context, spanCount: Int, orientation: Int,
                          reverseLayout: Boolean): GridLayoutManager {
        return GridLayoutManager(context, spanCount, orientation, reverseLayout)
    }

    fun staggeredManager(spanCount: Int, orientation: Int): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(spanCount, orientation)
    }

    fun horizontalDivider(context: Context, size: Int = 0, @ColorInt color: Int = 0, margin: Int = 0): RecyclerView.ItemDecoration {
        return HorizontalDividerItemDecoration.Builder(context)
                .size(size)
                .color(color)
                .margin(margin, margin)
                .build()
    }

    fun verticalDivider(context: Context, size: Int = 0, @ColorInt color: Int = 0, margin: Int = 0): RecyclerView.ItemDecoration {
        return VerticalDividerItemDecoration.Builder(context)
                .size(size)
                .color(color)
                .margin(margin, margin)
                .build()
    }
}