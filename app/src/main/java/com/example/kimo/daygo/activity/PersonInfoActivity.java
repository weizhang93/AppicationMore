package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.util.MyApplication;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class PersonInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        MyApplication.addActivity(this);

        initView();
    }

    private void initView() {

    }

}
