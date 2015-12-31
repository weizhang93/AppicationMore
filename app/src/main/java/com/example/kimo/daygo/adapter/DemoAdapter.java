package com.example.kimo.daygo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.kimo.daygo.R;
import com.example.kimo.daygo.model.MSG;

import java.util.List;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class DemoAdapter extends BaseAdapter {
    private List<MSG> msgList;
    private LayoutInflater mInflater;
    public DemoAdapter(Context context, List<MSG> list){
        msgList = list;
        //mView = LayoutInflater.from(context).inflate(resourceId,null);
        mInflater = LayoutInflater.from(context );
    }
    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        MSG msg = msgList.get(position);
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_msg,null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) convertView.findViewById(R.id.layout_left);
            viewHolder.rightLayout = (LinearLayout) convertView.findViewById(R.id.layout_right);
            viewHolder.leftMsg = (TextView)convertView.findViewById(R.id.tv_leftMsg);
            viewHolder.rightMsg = (TextView)convertView.findViewById(R.id.tv_rightMsg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leftMsg.setText(msg.getContent());
        if(msg.getType()==MSG.TYPE_RECEIVED){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        }else if(msg.getType()==MSG.TYPE_SEND){
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }

        return convertView;
//        convertView = mView;
//        TextView rightMsg;
//        MSG msg = msgList.get(position);
//        rightMsg = (TextView) convertView.findViewById(R.id.tv_rightMsg);
//        rightMsg.setText(msg.getContent());
//        return convertView;

    }
    class ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}
