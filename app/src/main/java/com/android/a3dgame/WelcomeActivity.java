package com.android.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {

    private GifImageView mImageView;
    private Animation mAnimation;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mImageView= (GifImageView) findViewById(R.id.welcome_gif);
        sp=getSharedPreferences("config", Context.MODE_PRIVATE);
        mAnimation=new AlphaAnimation(0,1);
        mAnimation.setDuration(3000);
        mImageView.startAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isFirst=sp.getBoolean("isFirst",true);
                if(isFirst){
                    Intent intent=new Intent(WelcomeActivity.this,SplashActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("isFirst",false);
                    edit.commit();
                    finish();
                }else{
                    Intent intent1=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
