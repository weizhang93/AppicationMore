package com.example.kimo.daygo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.kimo.daygo.MainActivity;
import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.AnimotionViewPagerActivity;
import com.example.kimo.daygo.activity.LoginActivity;
import com.example.kimo.daygo.http.LoginThread;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/16 0016.
 * 引导页
 * 应用启动动画
 * 判断是否是第一次加载
 */
public class BootPageActivity extends Activity{

    private static final int SWITCH_GUIDEACTIVITY = 0;
    private static final int SWITCH_MAINACTIVITY = 1;
    private Context context = MyApplication.getContext();

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SWITCH_GUIDEACTIVITY:
                    startActivity(new Intent(BootPageActivity.this, AnimotionViewPagerActivity.class));
                    break;
                case SWITCH_MAINACTIVITY:
                    startActivity(new Intent(BootPageActivity.this, MainActivity.class));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootpage);

        SharedPreferences sharedPreferences = this.getSharedPreferences("setting",
                Context.MODE_PRIVATE);
        //此处会在该应用内部存储空间下建立一个setting.xml
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        //此处表示如果key "isFirstRun"对应的value没有值则默认为true，
        //否则就把对应的value取出赋值给变量isFirstRun

//        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(getString(R.string.uName),name);
//        editor.commit();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(isFirstRun){
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            //进入引导动画,延迟3秒
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDEACTIVITY, 500);

        }else{
            //进入主界面,延迟3秒
            LogUtils.logDebug(""+isFirstRun);
            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY,500);

        }

    }
}
