package com.books.application.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.books.application.R
import com.books.application.base.BaseFragment
import com.books.application.databinding.FragmentBookStoreBinding
import com.books.application.home.viewmodel.BookStoreViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_book_store.*

class BookStoreFragment : BaseFragment() {
    private lateinit var mBinding: FragmentBookStoreBinding
    private lateinit var mViewModel: BookStoreViewModel

    companion object {
        fun newInstance() = BookStoreFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(BookStoreViewModel::class.java)
        mViewModel.categories.observe(this, Observer {
            showTabs(it)
        })
        mViewModel.loading.observe(this, Observer {
            mLoading.show(it)
        })
        mViewModel.getIndexInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_store, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position ?: 0
                val view = tab?.customView?.findViewById<TextView>(R.id.tv_tab_text)
                view?.setTextColor(ContextCompat.getColor(mContext, R.color.text_E84856))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val view = tab?.customView?.findViewById<TextView>(R.id.tv_tab_text)
                view?.setTextColor(ContextCompat.getColor(mContext, R.color.text_333333))
            }
        })

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = mPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }


    private fun showTabs(it: Array<String>?) {
        tabLayout.removeAllTabs()
        it?.forEach {
            val tabView = newTabView(it)
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabView))
        }
        mPagerAdapter.count = it?.size ?: 0
    }

    private fun newTabView(string: String): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_item, null)
        val tvAll = view.findViewById<TextView>(R.id.tv_tab_text)
        tvAll.text = string
        return view
    }

    private val mPagerAdapter by lazy {
        object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            var dataCount = 0

            fun setCount(count: Int) {
                dataCount = count
                notifyDataSetChanged()
            }

            override fun getItem(position: Int): Fragment {
                return ArticleListFragment.newInstance(position)
            }

            override fun getCount(): Int {
                return dataCount
            }
        }
    }
}