package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.util.PicDowThread;

/**
 * Created by Wei.zhang on 2015/12/23 0023.
 */
public class HttpActivity extends Activity {

    //下载图片
    private String url = "http://pic6.nipic.com/20100311/1295091_091344479721_2.jpg";
    private ImageView imageView;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_pic);
//        WebView webView = (WebView) findViewById(R.id.http_wb);
//        new HttpThread("http://www.baidu.com",webView,handler).start();

        imageView = (ImageView) findViewById(R.id.pic_iv);
        new PicDowThread(url,imageView,handler).start();

    }
}
