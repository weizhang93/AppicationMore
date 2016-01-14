package com.example.kimo.daygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.FeedbackActivity;
import com.example.kimo.daygo.activity.LoginActivity;
import com.example.kimo.daygo.activity.PersonInfoActivity;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;

/**
 * Created by Administrator on 2016/1/5 0005.
 * 更多Fragment
 * 放置登录，设置，反馈等，后期可以继续添加
 */
public class MoreFragment extends Fragment implements View.OnClickListener {

    private ImageView mIvIcon;
    private ImageView mIvEdit;
    private TextView mLogin;
    private TextView mSetting;
    private TextView mFeedback;
    private Context context = MyApplication.getContext();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mIvEdit = (ImageView) view.findViewById(R.id.ivEdit);
        mIvIcon = (ImageView) view.findViewById(R.id.ivIcon);
        mLogin = (TextView) view.findViewById(R.id.tv_login);
        mSetting = (TextView) view.findViewById(R.id.tv_setting);
        mFeedback = (TextView) view.findViewById(R.id.tv_connect_me);

        mIvIcon.setOnClickListener(this);
        mIvEdit.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mFeedback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivIcon:
            case R.id.ivEdit:
                startActivity(new Intent(context, PersonInfoActivity.class));
                break;
            case R.id.tv_login:
                startActivity(new Intent(context, LoginActivity.class));
                break;
            case R.id.tv_setting:
                LogUtils.logDebug("3333");
                break;
            case R.id.tv_connect_me:
                startActivity(new Intent(context, FeedbackActivity.class));
                break;
        }
    }
}
