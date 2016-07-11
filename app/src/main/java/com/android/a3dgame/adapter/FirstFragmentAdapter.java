package com.android.a3dgame.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.a3dgame.R;
import com.android.a3dgame.utils.Game;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class FirstFragmentAdapter extends BaseAdapter{
    private List<Game> list;


    public FirstFragmentAdapter(List<Game> list) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView=View.inflate(parent.getContext(), R.layout.item_firstfragment_adapter,null);
            vh=new ViewHolder();
            vh.mImageView= (ImageView) convertView.findViewById(R.id.item_firstfragment_iv);
            vh.mTitle= (TextView) convertView.findViewById(R.id.item_firstfragment_title);
            vh.mTime= (TextView) convertView.findViewById(R.id.item_firstfragment_time);
            vh.mId= (TextView) convertView.findViewById(R.id.item_firstfragment_id);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.mTitle.setText(list.get(position).getTitle());
        long date=Long.parseLong(list.get(position).getSenddate());
        String time=getFormatedDateTime(" yy-MM-dd HH:mm:ss",date);
        vh.mTime.setText(time);
        vh.mId.setText(list.get(position).getId());
        Picasso.with(parent.getContext()).load(list.get(position).getLitpic()).into(vh.mImageView);
        return convertView;
    }
    class ViewHolder{
        ImageView mImageView;
        TextView mTitle,mTime,mId;
    }
    public static String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }
}
