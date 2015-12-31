package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.ui.MovieRecorderView;

/**
 * Created by Wei.zhang on 2015/12/25 0025.
 * 录像机类
 *
 */
public class VideoActivity extends Activity {

    private MovieRecorderView mRecorderView;//
    private Button mShootBtn;
    private boolean isFinish = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);//小视频布局
        mRecorderView = (MovieRecorderView) findViewById(R.id.movieRecorderView);
        mShootBtn = (Button) findViewById(R.id.shoot_button);

        mShootBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRecorderView.record(new MovieRecorderView.OnRecordFinishListener() {

                        @Override
                        public void onRecordFinish() {
                            handler.sendEmptyMessage(1);
                        }
                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mRecorderView.getTimeCount() > 1)
                        handler.sendEmptyMessage(1);
                    else {
                        if (mRecorderView.getmVecordFile() != null)
                            mRecorderView.getmVecordFile().delete();
                        mRecorderView.stop();
                        Toast.makeText(VideoActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        isFinish = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFinish = false;
        mRecorderView.stop();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            finishActivity();
        }
    };

    private void finishActivity() {
        if (isFinish) {
            mRecorderView.stop();
            Intent i = new Intent(this,PlayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("wei", mRecorderView.getmVecordFile().getName());
            i.putExtras(bundle);
            startActivity(i);
        }
    }

    /**
     * 录制完成回调
     *
     * @author liuyinjun
     * @date 2015-2-9
     */
    public interface OnShootCompletionListener {
        public void OnShootSuccess(String path, int second);

        public void OnShootFailure();
    }
}
