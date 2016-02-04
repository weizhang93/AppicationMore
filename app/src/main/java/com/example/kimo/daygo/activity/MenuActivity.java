package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.adapter.SettingAdapter;
import com.example.kimo.daygo.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/25 0025.
 */
public class MenuActivity extends Activity{
    private ListView mListView;
    private Context context = MyApplication.getContext();
    private String[] strings = {"a","b","c"};
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        Adapter adapter = new ArrayAdapter<String>(context,android.R.layout
//                .simple_list_item_1,strings);
        mList.add("获取当前位置");
        mList.add("获取当前天气");
        mList.add("。。。");
        mList.add("退出");
        Adapter adapter = new SettingAdapter(context,mList);
        mListView = (ListView) findViewById(R.id.lv_menu);
        mListView.setAdapter((ListAdapter) adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mList.get(position)){
                    case "获取当前位置":
                        Toast.makeText(MenuActivity.this, "获取当前位置", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MenuActivity.this,LocationActivity.class));
                        break;
                    case "获取当前天气":
                        Toast.makeText(MenuActivity.this, "获取当前天气", Toast.LENGTH_SHORT).show();
                        break;
                    case "退出":
                        finish();
                        break;
                }
            }
        });
    }
}
