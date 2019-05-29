package com.books.application.home.view


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.books.application.R
import com.books.application.base.BaseFragment
import com.books.application.common.widgets.recyclerview.LayoutManagerFactory
import com.books.application.constants.BundleKey
import com.books.application.databinding.FragmentArticleListBinding
import com.books.application.home.viewmodel.ArticleListViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticleListFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(index: Int) =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putInt(BundleKey.KEY_INDEX, index)
                }
            }
    }

    private lateinit var mBinding: FragmentArticleListBinding
    private lateinit var mViewModel: ArticleListViewModel

    private val mAdapter by lazy { ArticleListAdapter(mContext, null) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        mViewModel.initData(arguments)

        mViewModel.loading.observe(this, Observer {
            if (isVisibleToUser)
                mLoading.show(it)
        })
        mViewModel.articles.observe(this, Observer {
            mAdapter.replaceAll(it)
        })
        mViewModel.getArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val layoutManager = LayoutManagerFactory.verticalLinearLayoutManager(mContext)
        rvArticle.layoutManager = layoutManager
        rvArticle.adapter = mAdapter
        mAdapter.setOnItemClickListener { v, position ->
            val url = mAdapter.getItem(position)?.url
            val title = mAdapter.getItem(position)?.category
            startActivity(Intent(mContext, WebViewActivity::class.java).apply {
                putExtra(BundleKey.KEY_URL, url)
                putExtra(BundleKey.KEY_TITLE, title)
            })
        }
    }
}
