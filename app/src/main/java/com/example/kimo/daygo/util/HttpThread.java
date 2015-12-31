package com.example.kimo.daygo.util;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpThread extends Thread{
    private String mUrl ;
    private WebView mWebView;
    private Handler mHandler;

    public HttpThread(String url,WebView webView,Handler handler) {
        mUrl = url;
        mWebView = webView;
        mHandler = handler;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            final StringBuilder sb = new StringBuilder();
            String line = "";
            while((line=reader.readLine())!= null){
                sb.append(line);
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadData(sb.toString(),"text/html;charset=utf-8",null);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
