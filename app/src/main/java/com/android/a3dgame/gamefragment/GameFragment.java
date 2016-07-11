package com.android.a3dgame.gamefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import com.android.a3dgame.GameListenerActivity;
import com.android.a3dgame.R;
import com.android.a3dgame.adapter.GridViewAdapter;
import com.android.a3dgame.adapter.MySipnnerAdapter;
import com.android.a3dgame.utils.Game;
import com.android.a3dgame.utils.JsonUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/6.
 */
public class GameFragment extends Fragment {
    private View v;
    private Spinner mSpinner;
    private PullToRefreshGridView mGridView;
    private GridViewAdapter mAdapter;
    private MySipnnerAdapter spAdapter;
    private List<Map<String, Object>> mList;
    private List<Game> data;
    private int pageIndex=1;
    private String url;
    private String[] text = {"首页", "动作(ACT)", "射击(FPS)", "角色扮演(RPG)", "养成(GAL)", "益智(PUZ)", "即时战略(RTS)", "策略(SLG)", "体育(SPG)", "模拟经营(SIM)", "赛车(RAC)", "冒险(AVG)", "动作角色(ARPG)"};
    private int[] typeId = {179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.item_game_fragment, null);
        initView();
        setData();
        setListener();
        return v;
    }

    private void setListener() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Objects> map = (Map<String, Objects>) parent.getItemAtPosition(position);
                url = "http://www.3dmgame.com/sitemap/api.php?row=30&typeid=" + map.get("typeId") + "&paging=1&page=";
               go(url,pageIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                 pageIndex+=1;
                go(url,pageIndex);
                mGridView.onRefreshComplete();
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(parent.getContext(), GameListenerActivity.class);
                intent.putExtra("url",data.get(position).getArcurl());
                startActivity(intent);
            }
        });
    }

    private void setData() {
        mList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", text[i]);
            map.put("typeId", typeId[i]);
            mList.add(map);
        }
        spAdapter = new MySipnnerAdapter(mList);
        mSpinner.setAdapter(spAdapter);
    }

    private void initView() {
        mSpinner = (Spinner) v.findViewById(R.id.item_game_sp);
        mGridView = (PullToRefreshGridView) v.findViewById(R.id.item_game_gv);
mGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }
    public void go(String url,int pageIndex){
        OkHttpUtils.get().url(url+pageIndex).build().execute(new Callback<List<Game>>() {


            @Override
            public List<Game> parseNetworkResponse(Response response) throws Exception {
                String jsonStr = response.body().string();
                data = JsonUtils.getData(jsonStr);
                return data;
            }

            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(Call call, List<Game> games) {
                mAdapter = new GridViewAdapter(games);
                mGridView.setAdapter(mAdapter);
            }
        });
    }
}
