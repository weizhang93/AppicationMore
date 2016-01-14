package com.example.kimo.daygo.http;

import com.example.kimo.daygo.util.LogUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import net.tsz.afinal.http.HttpHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2016/1/14 0014.
 * AFinal上传下载类
 */
public class HttpUtil {

    public static void upload(String usname,String password,String email){
        AjaxParams params = new AjaxParams();
        params.put("username", "michael yang");
        params.put("password", "123456");
        params.put("email", "test@tsz.net");
        try {
            params.put("profile_picture", new File("/mnt/sdcard/pic.jpg")); // 上传文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //params.put("profile_picture2", inputStream); // 上传数据流
        //params.put("profile_picture3", new ByteArrayInputStream(bytes)); // 提交字节流

        FinalHttp fh = new FinalHttp();
        fh.post("http://www.yangfuhai.com", params, new AjaxCallBack<Object>() {
            @Override
            public void onLoading(long count, long current) {
                LogUtils.logDebug(count + "/" + current);
            }

            @Override
            public void onSuccess(Object o) {
                LogUtils.logDebug(o==null?"null":o+"");
            }
        });
    }

    public static void download(){
        FinalHttp fh = new FinalHttp();
        //调用download方法开始下载
        HttpHandler handler = fh.download("http://myweb.site",//下载路径
                "/mnt/sdcard/test.apk",//保存地址
                true,//断点续传
                new AjaxCallBack<File>() {
                    @Override
                    public void onLoading(long count, long current) {
                        LogUtils.logDebug("下载进度："+current+"/"+count);
                    }

                    @Override
                    public void onSuccess(File t) {
                        LogUtils.logDebug(t==null?"null":t.getAbsoluteFile().toString());
                    }
                }
        );


        //调用stop()方法停止下载
        handler.stop();
    }
}
