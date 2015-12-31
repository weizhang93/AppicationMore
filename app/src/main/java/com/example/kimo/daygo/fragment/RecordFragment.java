package com.example.kimo.daygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.PlayActivity;
import com.example.kimo.daygo.adapter.RecordAdapter;
import com.example.kimo.daygo.model.Record;
import com.example.kimo.daygo.util.LocationUtils;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;
import com.example.kimo.daygo.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei.zhang on 2015/12/16 0016.
 * 记录Fragment
 * <p/>
 * 得到位置信息（）
 * 加载ListView（）
 */
public class RecordFragment extends Fragment {
    public static final String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private RecordAdapter mAdapter;
    private List<Record> mList = new ArrayList<>();
    private List<String> mList1 = new ArrayList<>();
    private Context context = MyApplication.getContext();
    private View mRootView ;//缓存Fragment View

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(context, "又调用一次onCreate", Toast.LENGTH_SHORT).show();
        if(mRootView==null){
            mRootView = inflater.inflate(R.layout.fragment_record, null);

            File file = new File(Environment.getExternalStorageDirectory() + File.separator +
                    "DAYGO/video/");
            Utils.getAllFiles(mList1, file);//取得所有file文件夹下的视频文件名
            for (int i = 0; i < mList1.size(); i++) {//将文件名传到数据源里
                Record record = new Record(mList1.get(i), LocationUtils.getCurLocationGPS());
                mList.add(record);
            }
            mAdapter = new RecordAdapter(getContext(), mList);
            final ListView listView = (ListView) mRootView.findViewById(R.id.record_lv);
            listView.setAdapter(mAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context,PlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("wei",mList1.get(position));//将文件名传入PlayActivity
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        //View view = inflater.inflate(R.layout.fragment_record, null);
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }



        return mRootView;
    }

    private void playVideo(String s) {

        Intent i = new Intent(context, PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("key",s);
        i.putExtras(bundle);
        startActivity(i);
    }

}
