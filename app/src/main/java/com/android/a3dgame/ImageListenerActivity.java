package com.android.a3dgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageListenerActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private WebView mWebView;
    private TextView mTextView;
    private ImageView mImageView;
    private Button mBtn1, mBtn2;
    private String num;
    private  String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_listener);
        mToolbar = (Toolbar) findViewById(R.id.imagelistener_activity_tb);
        mWebView = (WebView) findViewById(R.id.imagelistener_activity_wb);
        mTextView = (TextView) findViewById(R.id.imagelistener_activity_tv);
        mImageView = (ImageView) findViewById(R.id.imagelistener_activity_iv);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mTextView.setText("文章内容");
        Intent intent = getIntent();
         url = intent.getStringExtra("url");
        num=intent.getStringExtra("num");
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                WebSettings settings = mWebView.getSettings();
                settings.setBuiltInZoomControls(true);
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
                mWebView.loadUrl(url);
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        Toast.makeText(ImageListenerActivity.this,"aaa",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                break;
            case R.id.btn2:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("num",num);
                startActivity(intent);
                break;
        }
    }
}
