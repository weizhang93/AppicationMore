package com.example.kimo.daygo;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kimo.daygo.fragment.MoreFragment;
import com.example.kimo.daygo.fragment.RecordFragment;
import com.example.kimo.daygo.fragment.VideoFragment;
import com.example.kimo.daygo.ui.TitleLayout;
import com.example.kimo.daygo.util.MyApplication;
import com.example.kimo.daygo.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends FragmentActivity implements View.OnClickListener {

    private TitleLayout mTitleLayout;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;//适配器
    private List<Fragment> mFragmentList;//数据源

    private LinearLayout mCamLayout;
    private LinearLayout mRecLayout;
    private LinearLayout mMoreLayout;

    private Button mCamBtn;
    private Button mRecBtn;
    private Button mMoreBtn;

    private TextView mCamText;
    private TextView mRecText;
    private TextView mMoreText;

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
        mMoreLayout.setOnClickListener(this);
    }

    private void initView() {

        mTitleLayout = (TitleLayout) findViewById(R.id.tl_title);
        ViewGroup wraper = (ViewGroup) mTitleLayout.getChildAt(0);
        Button btnBack = (Button) wraper.getChildAt(0);
        btnBack.setVisibility(View.GONE);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);

        mRecLayout = (LinearLayout) findViewById(R.id.ll_rec);
        mCamLayout = (LinearLayout) findViewById(R.id.ll_cam);
        mMoreLayout = (LinearLayout) findViewById(R.id.ll_more);

        mRecBtn = (Button) findViewById(R.id.btn_rec);
        mCamBtn = (Button) findViewById(R.id.btn_cam);
        mMoreBtn = (Button) findViewById(R.id.btn_more);

        mRecText = (TextView) findViewById(R.id.tv_rec);
        mCamText = (TextView) findViewById(R.id.tv_cam);
        mMoreText = (TextView) findViewById(R.id.tv_more);


        //为数据源添加数据
        mFragmentList = new ArrayList<Fragment>();
        Fragment mTabRec = new RecordFragment();
        Fragment mTabCam = new VideoFragment();
        Fragment mTabSet = new MoreFragment();
        mFragmentList.add(mTabRec);
        mFragmentList.add(mTabCam);
        mFragmentList.add(mTabSet);


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
            case R.id.ll_more:
                setSelect(2);
                break;
        }
    }

    private void resetColers() {
        mCamBtn.setBackground(getResources().getDrawable(R.drawable.ic_video_white));
        mRecBtn.setBackground(getResources().getDrawable(R.drawable.ic_record_white));
        mMoreBtn.setBackground(getResources().getDrawable(R.drawable.ic_more_white));
        mCamText.setTextColor(Color.parseColor("#ecf0f1"));
        mRecText.setTextColor(Color.parseColor("#ecf0f1"));
        mMoreText.setTextColor(Color.parseColor("#ecf0f1"));
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
                mMoreBtn.setBackground(getResources().getDrawable(R.drawable.ic_more_green));
                mMoreText.setTextColor(Color.parseColor("#FF30BF10"));
                break;
        }
        mViewPager.setCurrentItem(i);//切换Tab
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        new AlertDialog.Builder(this).setTitle(R.string.areyousure)
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                        MyApplication.removeAllActivity();
                    }
                })
                .show();
    }
}
