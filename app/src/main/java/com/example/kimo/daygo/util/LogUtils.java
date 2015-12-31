package com.example.kimo.daygo.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Wei.zhang on 2015/12/22 0022.
 * 自定义Log类
 * 我不是针对苹果，在座的各位都是垃圾
 */
public class LogUtils {
    public final static boolean DEBUG = true ;
    private final static String DEBUG_TAG = "Wei.zhang" ;

    public static void logDebug(String tag,String msg){
        if(DEBUG) Log.d(tag,msg);
    }

    public static void logDebug(String msg){
        if(DEBUG) Log.d(DEBUG_TAG,msg);
    }

    public static void logDebug(InputStream is){
        if(DEBUG){
            StringBuilder sb = new StringBuilder();
            String line = "" ;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {
                while((line=reader.readLine())!=null){
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(DEBUG_TAG, sb.toString());
        }
    }
}
