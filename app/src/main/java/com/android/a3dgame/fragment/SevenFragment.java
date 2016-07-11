package com.android.a3dgame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/5.
 */
public class SevenFragment extends Fragment{
    private View v;
    private PullToRefreshListView mListView;
    private FirstFragmentAdapter mAdapter;
    private List<Game> sevenList;
    private int pageIndex = 1;
    private String url = "http://www.3dmgame.com/sitemap/api.php?row=30&typeid=196&paging=1&page=";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.item_seven_fragment, null);
        initView();
        setData(pageIndex);
        setListener();
        return v;
    }

    private void setListener() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                setData(pageIndex);
                mListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex += 1;
                setData(pageIndex);
                mListView.onRefreshComplete();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(parent.getContext(), ImageListenerActivity.class);
                intent.putExtra("url",sevenList.get(position-1).getArcurl());
                intent.putExtra("num",sevenList.get(position-1).getId());
                parent.getContext().startActivity(intent);
            }
        });
    }

    private void setData(int pageIndex) {
        OkHttpUtils.get().url(url + pageIndex).build().execute(new Callback<List<Game>>() {


            @Override
            public List<Game> parseNetworkResponse(Response response) throws Exception {
                String str = response.body().string();
                sevenList = JsonUtils.getData(str);
                return sevenList;
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

    private void initView() {
        mListView = (PullToRefreshListView) v.findViewById(R.id.item_seven_lv);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
    }
}
