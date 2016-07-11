package com.android.a3dgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.a3dgame.adapter.CommentAdapter;
import com.android.a3dgame.utils.CommentUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    private ListView mListView;
    private Intent intent;
    private String id;
    private CommentAdapter mAdapter;
    private  CommentUtils commentUtils;
    private String url = "http://www.3dmgame.com/sitemap/api.php?type=1&aid=";
    private String path = "&pageno=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        tv = (TextView) findViewById(R.id.comment_activity_tv);
        iv = (ImageView) findViewById(R.id.comment_activity_iv);
        mListView = (ListView) findViewById(R.id.comment_activity_lv);
        tv.setText("评论");
        intent = getIntent();
        id = intent.getStringExtra("num");
        int nu = Integer.parseInt(id);
        setData(nu);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setData(int nu) {
        OkHttpUtils.get().url(url + nu + path).build().execute(new Callback<CommentUtils>() {
            @Override
            public CommentUtils parseNetworkResponse(Response response) throws Exception {
                String str = response.body().string();
                Gson gson = new Gson();
                commentUtils = gson.fromJson(str, CommentUtils.class);
                Log.e("ddd",commentUtils.getCode());
                Log.e("dddd",commentUtils.getDescription().getData().get(0).getMsg());
                return commentUtils;
            }

            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(Call call, CommentUtils commentUtils) {
                String code = commentUtils.getCode();
                if (code.equals(1)) {
                    mAdapter = new CommentAdapter(commentUtils);
                    mListView.setAdapter(mAdapter);

                }

            }
        });
    }
}
