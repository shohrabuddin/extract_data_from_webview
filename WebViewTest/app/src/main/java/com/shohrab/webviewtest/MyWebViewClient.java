package com.shohrab.webviewtest;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by shohrab on 7/6/2016.
 */
public class MyWebViewClient extends WebViewClient {

    private MainActivity mMainActivity;

    MyWebViewClient (MainActivity mainActivity){
        mMainActivity = mainActivity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
       mMainActivity.showProgressBar(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mMainActivity.showProgressBar(View.GONE);
    }
}
