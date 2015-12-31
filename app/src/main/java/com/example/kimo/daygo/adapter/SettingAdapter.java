package com.example.kimo.daygo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kimo.daygo.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28 0028.
 */
public class SettingAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> mList;

    public SettingAdapter(Context context, List<String> recordList) {
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_setting, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.setting_tv);
        tv.setText(mList.get(position));
        return convertView;
    }
}
