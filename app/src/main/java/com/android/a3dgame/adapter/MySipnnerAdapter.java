package com.android.a3dgame.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.a3dgame.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MySipnnerAdapter extends BaseAdapter{
    private List<Map<String,Object>> list;

    public MySipnnerAdapter(List<Map<String, Object>> list) {
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

        convertView=View.inflate(parent.getContext(), R.layout.item_game_fragment_spinner_adapter,null);
        TextView tv= (TextView) convertView.findViewById(R.id.item_game_spinner_tv);
        Map<String,Object> map=list.get(position);
        tv.setText(map.get("text").toString());

        return convertView;
    }

}
