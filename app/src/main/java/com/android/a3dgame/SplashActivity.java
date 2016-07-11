package com.android.a3dgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.a3dgame.adapter.SplashAdapter;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mLayout;
    private SplashAdapter adapter;
    private List<ImageView> list;
    private int preposition = 0;
    private int[] imgs = {R.drawable.default1, R.drawable.default2, R.drawable.default3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewPager = (ViewPager) findViewById(R.id.splash_vp);
        mLayout = (LinearLayout) findViewById(R.id.llPoints);
        setData();
        setListener();
    }

    private void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLayout.getChildAt(position).setBackgroundResource(R.drawable.dot_enable);
                mLayout.getChildAt(preposition).setBackgroundResource(R.drawable.dot_normal);
                preposition=position;
                if(position==2){
                    ImageView iv=list.get(position);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setData() {
        list = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(imgs[i]);
            list.add(iv);
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.leftMargin = 5;
            view.setBackgroundResource(R.drawable.dot_normal);
            view.setLayoutParams(params);
            mLayout.addView(view);
        }
        mLayout.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);
        adapter = new SplashAdapter(list);
        mViewPager.setAdapter(adapter);
    }
}
