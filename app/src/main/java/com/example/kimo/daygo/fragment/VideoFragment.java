package com.example.kimo.daygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.VideoActivity;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;

import static com.example.kimo.daygo.R.layout.fragment_video;

/**
 * Created by Administrator on 2015/12/25 0025.
 */
public class VideoFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,null);
        view.findViewById(R.id.video_ll).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_ll:
                LogUtils.logDebug("***Video Start***");
                startActivity(new Intent(MyApplication.getContext(),VideoActivity.class));
                break;
            default:
                break;
        }
    }
}
