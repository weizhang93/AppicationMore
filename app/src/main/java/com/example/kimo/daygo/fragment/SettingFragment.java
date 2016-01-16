package com.example.kimo.daygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.LoginActivity;
import com.example.kimo.daygo.adapter.SettingAdapter;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class SettingFragment extends Fragment {
    private ListView listView ;
    private ImageView mImageView;
    private static final int REQ_1 = 1;
    private static final int REQ_2 = 2;
    private String mFilePath;
    private Context context = MyApplication.getContext();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_setting,null);
//        View view = inflater.inflate(R.layout.item_record,null) ;

//        mImageView = (ImageView) view.findViewById(R.id.iv_cam);
//        mFilePath = Environment.getExternalStorageDirectory().getPath();
//        mFilePath = mFilePath + "/" + "temp.png";
        listView = (ListView) view.findViewById(R.id.setting_lv);
        initView();
        return view;
    }

    private void initView() {

        final List<String> list = new ArrayList<>();
        list.add("登录");
        list.add("set2");
        list.add("set3");
        list.add("set3");
        Adapter adapter1 = new SettingAdapter(MyApplication.getContext(),list);
        //Adapter adapter = new ArrayAdapter<String>(MyApplication.getContext(),android.R.layout
        //        .simple_list_item_1,list);
        listView.setAdapter((ListAdapter) adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (list.get(position)){
//                    case "set1":
//                        LogUtils.logDebug("1111");
//                        break;
//                    case "set2":
//                        LogUtils.logDebug("2222");
//                        break;
//                    case "set3":
//                        LogUtils.logDebug("3333");
//                        break;
//                }
                switch (position){
                    case 0:
                        LogUtils.logDebug("1111");
                        startActivity(new Intent(context, LoginActivity.class));
                        break;
                    case 1:
                        LogUtils.logDebug("2222");
                        break;
                    case 2:
                        LogUtils.logDebug("3333");
                        break;
                }
            }
        });

    }

    public void camera_one(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
        startActivityForResult(intent,REQ_1);
    }

    public void camera_two(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
        Uri uri = Uri.fromFile(new File(mFilePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);//此时要去注册权限；

        startActivityForResult(intent, REQ_2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1){
            if(requestCode == REQ_1){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                mImageView.setImageBitmap(bitmap);
            }else if(requestCode == REQ_2){
                FileInputStream fis = null ;
                Bitmap bitmap = null ;
                try {
                    fis = new FileInputStream(mFilePath);
                    bitmap = BitmapFactory.decodeStream(fis);
                    mImageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
