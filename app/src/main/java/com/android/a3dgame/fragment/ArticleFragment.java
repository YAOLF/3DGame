package com.android.a3dgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.a3dgame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ArticleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioButton mTv1, mTv2, mTv3, mTv4, mTv5, mTv6, mTv7, mTv8, mTv9, mTv10;
    private RadioGroup rg;
    private HorizontalScrollView mScrollView;
    private ViewPager mViewPager;
    private List<RadioButton> mTextViewList;
    private List<Fragment> mListFragment;
    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.aeticle_fragment, null);
        initView();
        setData();
        setListener();
        return v;
    }

    private void setListener() {
        rg.setOnCheckedChangeListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               /* mScrollView.setVisibility(View.INVISIBLE);
                rg.setVisibility(View.INVISIBLE);*/
               RadioButton rb = (RadioButton) rg.getChildAt(position);
                rb.setChecked(true);
                int left = rb.getLeft();
                mScrollView.smoothScrollTo(left, 0);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mScrollView = (HorizontalScrollView) v.findViewById(R.id.hl);
        mTv1 = (RadioButton) v.findViewById(R.id.textView1);
        mTv2 = (RadioButton) v.findViewById(R.id.textView2);
        mTv3 = (RadioButton) v.findViewById(R.id.textView3);
        mTv4 = (RadioButton) v.findViewById(R.id.textView4);
        mTv5 = (RadioButton) v.findViewById(R.id.textView5);
        mTv6 = (RadioButton) v.findViewById(R.id.textView6);
        mTv7 = (RadioButton) v.findViewById(R.id.textView7);
        mTv8 = (RadioButton) v.findViewById(R.id.textView8);
        mTv9 = (RadioButton) v.findViewById(R.id.textView9);
        mTv10 = (RadioButton) v.findViewById(R.id.textView10);
        rg = (RadioGroup) v.findViewById(R.id.article_rg);
        mViewPager = (ViewPager) v.findViewById(R.id.first_fragment_vp);
    }

    private void setData() {

        mTv1.setChecked(true);
        mListFragment = new ArrayList<>();
        mListFragment.add(new FirstFragment());
        mListFragment.add(new TwoFragment());
        mListFragment.add(new ThreeFragment());
        mListFragment.add(new FourFragment());
        mListFragment.add(new FiveFragment());
        mListFragment.add(new SixFragment());
        mListFragment.add(new SevenFragment());
        mListFragment.add(new EitFragment());
        mListFragment.add(new NineFragment());
        mListFragment.add(new TenFragment());
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.textView1:
                mViewPager.setCurrentItem(0);
                mTv1.setChecked(true);
                break;
            case R.id.textView2:
                mViewPager.setCurrentItem(1);
                mTv2.setChecked(true);
                break;
            case R.id.textView3:
                mViewPager.setCurrentItem(2);
                mTv3.setChecked(true);
                break;
            case R.id.textView4:
                mViewPager.setCurrentItem(3);
                mTv4.setChecked(true);
                break;
            case R.id.textView5:
                mViewPager.setCurrentItem(4);
                mTv5.setChecked(true);
                break;
            case R.id.textView6:
                mViewPager.setCurrentItem(5);
                mTv6.setChecked(true);
                break;
            case R.id.textView7:
                mViewPager.setCurrentItem(6);
                mTv7.setChecked(true);
                break;
            case R.id.textView8:
                mViewPager.setCurrentItem(7);
                mTv8.setChecked(true);
                break;
            case R.id.textView9:
                mViewPager.setCurrentItem(8);
                mTv9.setChecked(true);
                break;
            case R.id.textView10:
                mViewPager.setCurrentItem(9);
                mTv10.setChecked(true);
                break;
        }
    }


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }
    }
}
