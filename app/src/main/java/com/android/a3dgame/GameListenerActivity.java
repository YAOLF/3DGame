package com.android.a3dgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class GameListenerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;
    private TextView mTextView;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_listener);
        mToolbar= (Toolbar) findViewById(R.id.imagelistener_activity_tb);
        mWebView= (WebView) findViewById(R.id.imagelistener_activity_wb);
        mTextView= (TextView) findViewById(R.id.imagelistener_activity_tv);
        mImageView= (ImageView) findViewById(R.id.imagelistener_activity_iv);
        mTextView.setText("游戏详情");
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
