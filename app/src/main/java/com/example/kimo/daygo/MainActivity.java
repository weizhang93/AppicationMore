package com.example.kimo.daygo;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kimo.daygo.fragment.CameraFragment;
import com.example.kimo.daygo.fragment.ChatFragment;
import com.example.kimo.daygo.fragment.RecordFragment;
import com.example.kimo.daygo.fragment.SettingFragment;
import com.example.kimo.daygo.fragment.VideoFragment;
import com.example.kimo.daygo.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;//适配器
    private List<Fragment> mFragmentList;//数据源

    private LinearLayout mCamLayout;
    private LinearLayout mRecLayout;
    private LinearLayout mSetLayout;
    private LinearLayout mChaLayout;

    private Button mCamBtn;
    private Button mRecBtn;
    private Button mSetBtn;
    private Button mChaBtn;

    private TextView mCamText;
    private TextView mRecText;
    private TextView mSetText;
    private TextView mChaText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        initEvent();
        setSelect(1);
        Utils.dateFormat(System.currentTimeMillis(), "yyyy/MM/dd-HH/mm");
    }

    private void initEvent() {
        mRecLayout.setOnClickListener(this);
        mCamLayout.setOnClickListener(this);
        mSetLayout.setOnClickListener(this);
        mChaLayout.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_content);

        mRecLayout = (LinearLayout) findViewById(R.id.ll_rec);
        mCamLayout = (LinearLayout) findViewById(R.id.ll_cam);
        mSetLayout = (LinearLayout) findViewById(R.id.ll_set);
        mChaLayout = (LinearLayout) findViewById(R.id.ll_cha);

        mRecBtn = (Button) findViewById(R.id.btn_rec);
        mCamBtn = (Button) findViewById(R.id.btn_cam);
        mSetBtn = (Button) findViewById(R.id.btn_set);
        mChaBtn = (Button) findViewById(R.id.btn_cha);

        mRecText = (TextView) findViewById(R.id.tv_rec);
        mCamText = (TextView) findViewById(R.id.tv_cam);
        mSetText = (TextView) findViewById(R.id.tv_set);
        mChaText = (TextView) findViewById(R.id.tv_cha);


        //为数据源添加数据
        mFragmentList = new ArrayList<Fragment>();
        Fragment mTabRec = new RecordFragment();
        Fragment mTabCam = new VideoFragment();
        Fragment mTabSet = new SettingFragment();
        Fragment mTabCha = new ChatFragment();
        mFragmentList.add(mTabRec);
        mFragmentList.add(mTabCam);
        mFragmentList.add(mTabSet);
        mFragmentList.add(mTabCha);

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };

        //注册监听器
        mViewPager.setAdapter(mAdapter);
        //添加联动效果
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                resetColers();//重置图片
                setSelect(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onClick(View v) {
        resetColers();//重置图片
        switch (v.getId()){
            case R.id.ll_rec:
                setSelect(0);
                break;
            case R.id.ll_cam:
                setSelect(1);
                break;
            case R.id.ll_set:
                setSelect(2);
                break;
            case R.id.ll_cha:
                setSelect(3);
                break;
        }
    }

    private void resetColers() {
        mCamBtn.setBackground(getResources().getDrawable(R.drawable.ic_video_white));
        mRecBtn.setBackground(getResources().getDrawable(R.drawable.ic_record_white));
        mSetBtn.setBackground(getResources().getDrawable(R.drawable.ic_setting_white));
        mChaBtn.setBackground(getResources().getDrawable(R.drawable.ic_chat_white));
        mCamText.setTextColor(Color.parseColor("#ecf0f1"));
        mRecText.setTextColor(Color.parseColor("#ecf0f1"));
        mSetText.setTextColor(Color.parseColor("#ecf0f1"));
        mChaText.setTextColor(Color.parseColor("#ecf0f1"));
    }

    private void setSelect(int i){
        //把btn,tv设置为亮
        //切换类容区域
        switch (i){
            case 0:
                mRecBtn.setBackground(getResources().getDrawable(R.drawable.ic_record_green));
                mRecText.setTextColor(Color.parseColor("#FF30BF10"));
                break;
            case 1:
                mCamBtn.setBackground(getResources().getDrawable(R.drawable.ic_video_green));
                mCamText.setTextColor(Color.parseColor("#FF30BF10"));
            break;
            case 2:
                mSetBtn.setBackground(getResources().getDrawable(R.drawable.ic_setting_green));
                mSetText.setTextColor(Color.parseColor("#FF30BF10"));
                break;
            case 3:
                mChaBtn.setBackground(getResources().getDrawable(R.drawable.ic_chat_green));
                mChaText.setTextColor(Color.parseColor("#FF30BF10"));
                break;
        }
        mViewPager.setCurrentItem(i);//切换Tab
    }

}
