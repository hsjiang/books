package com.books.application.common.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.*;

public class BaseWebView extends WebView {

    public BaseWebView(Context context) {
        this(context, null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.loadUrl("about:blank");
            }
        });
        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public void onDestroy() {
        try {
            CookieSyncManager.createInstance(getContext().getApplicationContext());
            CookieManager manager = CookieManager.getInstance();
            manager.removeAllCookie();
            CookieSyncManager.getInstance().sync();

            this.loadUrl("about:blank");
            this.stopLoading();
            this.setWebChromeClient(null);
            this.setWebViewClient(null);
            this.clearCache(true);
            this.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}