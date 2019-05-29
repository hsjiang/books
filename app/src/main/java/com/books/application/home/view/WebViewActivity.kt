package com.books.application.home.view

import android.os.Bundle
import com.books.application.R
import com.books.application.base.BaseActivity
import com.books.application.constants.BundleKey
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {
    private var url: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        url = intent.extras?.getString(BundleKey.KEY_URL)
        title = intent.extras?.getString(BundleKey.KEY_TITLE)

        webView.loadUrl(url)
        tvTitle.text = title
        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.onDestroy()
    }
}
