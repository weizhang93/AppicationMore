package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.kimo.daygo.R;

import java.io.File;

/**
 * Created by Administrator on 2015/12/18 0018.
 * 视频播放类
 */
public class PlayActivity extends Activity implements View.OnClickListener{

    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private VideoView mVideoView;
    private File mfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initView();
    }

    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_play_play);
        btnPause = (Button) findViewById(R.id.btn_pause_play);
        btnStop = (Button) findViewById(R.id.btn_stop_play);
        mVideoView = (VideoView) findViewById(R.id.vv_play);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        initVideoPath();
    }

    private void initVideoPath() {
        Intent i = getIntent();
        String s = i.getExtras().getString("wei");
        if(s!=null){
            mfile = new File(Environment.getExternalStorageDirectory() + File.separator +
                    "DAYGO/video/"+s);
        }
        mVideoView.setVideoPath(mfile.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_play:
                if(!mVideoView.isPlaying()){
                    mVideoView.start();
                }
                break;
            case R.id.btn_pause_play:
                if(mVideoView.isPlaying()){
                    mVideoView.pause();
                }
                break;
            case R.id.btn_stop_play:
                if(mVideoView.isPlaying()){
                    mVideoView.resume();
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mVideoView!=null){
            mVideoView.suspend();//
        }
    }
}
