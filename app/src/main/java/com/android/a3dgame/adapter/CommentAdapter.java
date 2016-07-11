package com.android.a3dgame.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.a3dgame.R;
import com.android.a3dgame.utils.CommentUtils;

/**
 * Created by Administrator on 2016/7/9.
 */
public class CommentAdapter extends BaseAdapter{
    private CommentUtils comment;

    public CommentAdapter(CommentUtils comment) {
        this.comment = comment;
    }

    @Override
    public int getCount() {
        return comment.getDescription().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return comment.getDescription().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(parent.getContext(), R.layout.item_comment,null);
       /* TextView tv= (TextView) convertView.findViewById(R.id.item_comment_tv);
        ImageView iv= (ImageView) convertView.findViewById(R.id.item_comment_iv);
        CommentUtils.DescriptionBean.DataBean data=comment.getDescription().getData().get(position);
        tv.setText("dddddddd");*/
        //tv.setText(data.getMsg());


        return convertView;
    }
}
