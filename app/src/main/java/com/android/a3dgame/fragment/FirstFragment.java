package com.android.a3dgame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.a3dgame.ImageListenerActivity;
import com.android.a3dgame.R;
import com.android.a3dgame.adapter.FirstFragmentAdapter;
import com.android.a3dgame.utils.Game;
import com.android.a3dgame.utils.JsonUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/5.
 */
public class FirstFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private View v;
    private ViewPager mViewPager;
    private PullToRefreshListView mListView;
    private LinearLayout mLayout;
    private List<ImageView> mList;
    private List<Game> list;
    private FirstFragmentAdapter mAdapter;
    private int preposition = 0;
    private int pageIndex = 1;
    private MyHandler mHandler = new MyHandler();
    public static final int AUTO = 0;
    private int imgs[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    private String url = "http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.item_first_fragment, null);
        initView();
        setData();
        setListener();
        return v;
    }

    private void setListener() {
        mViewPager.addOnPageChangeListener(this);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex-=1;
                getJson(pageIndex);

                mListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex += 1;
                getJson(pageIndex);
                mListView.onRefreshComplete();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(parent.getContext(), ImageListenerActivity.class);
                intent.putExtra("url",list.get(position-1).getArcurl());
                intent.putExtra("num",list.get(position-1).getId());
                parent.getContext().startActivity(intent);
            }
        });
    }

    private void setData() {
        mList = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(imgs[i]);
            mList.add(iv);
            final View point = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.leftMargin = 5;
            point.setBackgroundResource(R.drawable.dot_normal);
            point.setLayoutParams(params);
            point.setTag(i);
            point.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = mViewPager.getCurrentItem();
                    int num = current / 5;
                    int tag = (int) point.getTag();
                    int position = num * 5 + tag;
                    mViewPager.setCurrentItem(position);
                }
            });
            mLayout.addView(point);
        }
        mLayout.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);
        MyAdapter adapter = new MyAdapter();
        mViewPager.setAdapter(adapter);
        mHandler.sendEmptyMessageDelayed(AUTO, 2000);
    }

    private void initView() {
        mViewPager = (ViewPager) v.findViewById(R.id.item_first_vp);
        mListView = (PullToRefreshListView) v.findViewById(R.id.item_first_lv);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setAdapter(mAdapter);
        mLayout = (LinearLayout) v.findViewById(R.id.llPoints);
        getJson(pageIndex);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mLayout.getChildAt(position % mList.size()).setBackgroundResource(R.drawable.dot_enable);
        mLayout.getChildAt(preposition).setBackgroundResource(R.drawable.dot_normal);
        preposition = position % mList.size();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position % mList.size());
            if (v.getParent() != null) {
                ViewGroup vg = (ViewGroup) v.getParent();
                vg.removeView(view);
            }

            container.removeView(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % mList.size()));
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    class MyHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            int index = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(index + 1);
            mHandler.sendEmptyMessageDelayed(AUTO, 2000);
        }
    }

    public void getJson(int pageIndex) {
        OkHttpUtils.get().url(url+pageIndex).build().execute(new Callback<List<Game>>() {


            @Override
            public List<Game> parseNetworkResponse(Response response) throws Exception {
                String str = response.body().string();
                list = JsonUtils.getData(str);
                return list;
            }

            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(Call call, List<Game> games) {
                mAdapter = new FirstFragmentAdapter(games);
                mListView.setAdapter(mAdapter);
            }
        });
    }
}
