package com.example.kimo.daygo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Wei.zhang on 2015/12/23 0023.
 * 图片加载类
 * 遇到的问题：图片加载速度过慢
 *
 */
public class PicDowThread extends Thread {
    private String mUrl ;
    private ImageView mImageView;
    private Handler mHandler;

    public PicDowThread(String url,ImageView imageView,Handler handler) {
        mUrl = url;
        mImageView = imageView;
        mHandler = handler;
    }
    @Override
    public void run() {
        super.run();

        try {
            URL httpUrl = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setReadTimeout(12000);
            connection.setRequestMethod("GET");

            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            FileOutputStream out = null;

            //从网上读取流放入输出流
            String fileName = String.valueOf(System.currentTimeMillis());
            String downloadPath = null ;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downloadPath = new File(Environment.getExternalStorageDirectory(),
                        fileName+".jpg").getPath();
                out = new FileOutputStream(downloadPath);//准备一个输出流
            }

            final byte[] b = new byte[1024];
            int len ;
            if(out!=null){
                while ((len = is.read(b))!=-1){//读取从网上得到的流
                    out.write(b,0, len);//将流写进文件
                }
            }


            final Bitmap bitmap = BitmapFactory.decodeFile(downloadPath);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mImageView.setImageBitmap(bitmap);
                }
            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
