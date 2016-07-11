package com.android.a3dgame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.a3dgame.fragment.ArticleFragment;
import com.android.a3dgame.fragment.LunTanFragment;
import com.android.a3dgame.gamefragment.GameFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

private FragmentManager mManager;
    private RadioGroup rg;
    private RadioButton mButton1,mButton2,mButton3;
    private Toolbar mToolbar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar= (Toolbar) findViewById(R.id.main_tb);
        tv= (TextView) findViewById(R.id.main_tv);
        mToolbar.setLogo(R.drawable.xiaoji);
        tv.setText("文章");
        initView();

        mManager=getSupportFragmentManager();
        Fragment fragment=new ArticleFragment();
        FragmentTransaction transaction=mManager.beginTransaction();
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commit();
        setListener();

    }

    private void setListener() {
        rg.setOnCheckedChangeListener(this);
    }


    private void initView() {

        rg= (RadioGroup) findViewById(R.id.main_bottom_rg);
        mButton1= (RadioButton) findViewById(R.id.main_bottom_rb01);
        mButton2= (RadioButton) findViewById(R.id.main_bottom_rb02);
        mButton3= (RadioButton) findViewById(R.id.main_bottom_rb03);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_bottom_rb01:
                tv.setText("文章");
                Fragment fragment1=new ArticleFragment();
                FragmentTransaction transaction1=mManager.beginTransaction();
                transaction1.replace(R.id.main_fragment,fragment1);
                transaction1.commit();
                mButton1.setBackgroundColor(Color.parseColor("#6AC37A"));
                mButton3.setBackgroundColor(Color.BLACK);
                mButton2.setBackgroundColor(Color.BLACK);
                break;
            case R.id.main_bottom_rb02:
                tv.setText("论坛");
                Fragment fragment2=new LunTanFragment();
                FragmentTransaction transaction2=mManager.beginTransaction();
                transaction2.replace(R.id.main_fragment,fragment2);
                transaction2.commit();
                mButton2.setBackgroundColor(Color.parseColor("#6AC37A"));
                mButton1.setBackgroundColor(Color.BLACK);
                mButton3.setBackgroundColor(Color.BLACK);
                break;
            case R.id.main_bottom_rb03:
                tv.setText("游戏");
                Fragment fragment3=new GameFragment();
                FragmentTransaction transaction3=mManager.beginTransaction();
                transaction3.replace(R.id.main_fragment,fragment3);
                transaction3.commit();
                mButton3.setBackgroundColor(Color.parseColor("#6AC37A"));
                mButton2.setBackgroundColor(Color.BLACK);
                mButton1.setBackgroundColor(Color.BLACK);
                break;
        }
    }
}
