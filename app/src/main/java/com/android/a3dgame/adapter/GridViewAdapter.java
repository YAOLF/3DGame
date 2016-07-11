package com.android.a3dgame.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.a3dgame.R;
import com.android.a3dgame.utils.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class GridViewAdapter extends BaseAdapter{
    private List<Game> list;

    public GridViewAdapter(List<Game> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        convertView=View.inflate(parent.getContext(), R.layout.item_game_fragment_gridview_adapter,null);
        vh=new ViewHolder();
        vh.iv= (ImageView) convertView.findViewById(R.id.item_game_gridview_iv);
        vh.tv= (TextView) convertView.findViewById(R.id.item_game_gridview_tv);
        vh.tv.setText(list.get(position).getTitle());
        Picasso.with(parent.getContext()).load(list.get(position).getLitpic()).into(vh.iv);
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
