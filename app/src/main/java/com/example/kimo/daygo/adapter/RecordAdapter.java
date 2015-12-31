package com.example.kimo.daygo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.model.Record;

import java.util.List;

/**
 * Created by Administrator on 2015/12/25 0025.
 */
public class RecordAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Record> mList;

    public RecordAdapter(Context context,List<Record> recordList){
        mInflater = LayoutInflater.from(context);
        mList = recordList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        Record bean = mList.get(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_record,null);

            viewHolder.vTitle = (TextView) convertView.findViewById(R.id.record_title);
            viewHolder.vLocation = (TextView) convertView.findViewById(R.id.record_address);
            viewHolder.vImage = (ImageView) convertView.findViewById(R.id.record_iv);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.vImage.setImageResource(R.mipmap.ic_launcher);
        viewHolder.vTitle.setText(bean.getTitle());
        viewHolder.vLocation.setText(bean.getLocation());

        return convertView;
    }

    private class ViewHolder{
        ImageView vImage;
        TextView vTitle;
        TextView vLocation;
    }
}
