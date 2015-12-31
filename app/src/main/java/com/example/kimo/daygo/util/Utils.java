package com.example.kimo.daygo.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Wei.zhang on 2015/12/23 0023.
 * 常用工具类
 */
public class Utils {


    public static boolean NET_WORK_STATE = true ;

    /**
     * 查看网络是否接通
     * @return
     */
    public static boolean pingNetworkHttp(){
        boolean result = false ;
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            int status = httpResponse.getStatusLine().getStatusCode();
            if(status == HttpURLConnection.HTTP_OK){
                result = true;
            }
            LogUtils.logDebug("***pingNetworkHttp*** "+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NET_WORK_STATE = result;
        return result;
    }

    /**
     * 查看网络是否连接并可ping通
     * @param context
     * @return
     */
    public static boolean isNetConnect(Context context){
        boolean isConnect = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info!=null){
                isConnect = info.isAvailable()&&info.isConnected();
            }
            LogUtils.logDebug("***net is connect***"+isConnect);
            return isConnect&& NET_WORK_STATE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnect;
    }

    /**
     * 是否是移动网络
     * @param context
     * @return
     */
    public static boolean isMobileNetwork(Context context){
        boolean isMobileOn = false ;
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //手机网络判断
        NetworkInfo.State state = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if(state == NetworkInfo.State.CONNECTED||state == NetworkInfo.State.CONNECTING){
            isMobileOn = true;
        }
        LogUtils.logDebug("***isMobileNetwork*** ="+isMobileOn);
        return isMobileOn;
    }

    /**
     * 是否是Wifi网络
     * @param context
     * @return
     */
    public static boolean isWifiNetwork(Context context){
        boolean isWifiOn = false ;
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //Wifi网络判断
        NetworkInfo.State state = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if(state == NetworkInfo.State.CONNECTED||state == NetworkInfo.State.CONNECTING){
            isWifiOn = true;
        }
        LogUtils.logDebug("***isWifiNetwork*** ="+isWifiOn);
        return isWifiOn;
    }

    /**
     * 判断GPS状态
     */
    public static boolean getGpsStatus(Context context){
        boolean state = false ;
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        state = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        LogUtils.logDebug("***getGpsStatus*** "+state);
        return state;
    }

    /**
     * 返回标准格式的时间： yyyy-MM-dd HH:mm
     */
    public static String getDateFormat_yMdHm(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String dateStr = null ;
        try {
            date = new Date(Long.parseLong(timeStr));
            dateStr = sdf.format(date);
            LogUtils.logDebug("***getDateFormat_yMdHm*** "+dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 返回标准格式的时间： yyyy-MM-dd
     */
    public static long getDateFormat_yMdlong(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(timeStr);
            LogUtils.logDebug("***getDateFormat_yMdlong*** "+date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  date.getTime();
    }

    /**
     * 返回标准格式的时间： yyyy-MM-dd
     */
    public static String getDateFormat_yMd(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = null;
        try {
            dateStr = sdf.format(sdf.parse(timeStr));
            LogUtils.logDebug("***getDateFormat_yMd*** " + dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 返回标准格式的时间： yyyy-MM-dd HH:mm
     */
    public static String getDateFormat_yMdHd(long timeMillSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = null;
        try {
            Date date = new Date(timeMillSecond);
            dateStr = sdf.format(date);
            LogUtils.logDebug("***getDateFormat_yMdHd*** " + dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 返回标准格式的时间： yyyy-MM-dd
     */
    public static String getDateFormat_yMd(long timeMillSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
        String dateStr = null;
        try {
            Date date = new Date(timeMillSecond);
            dateStr = sdf.format(date);
            LogUtils.logDebug("***getDateFormat_yMd*** " + dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 返回标准格式的时间： yyyy/MM/dd-HH:mm
     */
    public static String getDateFormat_yMdHd_Italic(long timeMillSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        String dateStr = null;
        try {
            Date date = new Date(timeMillSecond);
            dateStr = sdf.format(date);
            LogUtils.logDebug("***getDateFormat_yMdHd_Italic*** " + dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 返回标准格式的时间： yyyy/MM/dd
     */
    public static String getDateFormat_yMd_Italic(long timeMillSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = null;
        try {
            Date date = new Date(timeMillSecond);
            dateStr = sdf.format(date);
            LogUtils.logDebug("***getDateFormat_yMd_Italic*** " + dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    /**
     * 通用格式
     * 常用格式 yyyy-MM-dd HH:mm
     *         yyyy/MM/dd HH:mm
     */
    public static String dateFormat(long timeMillSecond,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = null ;
        try{
            dateStr = sdf.format(new Date(timeMillSecond));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 当前时间戳
     */
    public static String dataFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = null ;
        try {
            dateStr = sdf.format(new Date(System.currentTimeMillis()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateStr;
    }

    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    public static List<String> getAllFiles(List<String> list,File root){
        File files[] = root.listFiles();
        if(files != null){
            for (File f : files){
                if(f.isDirectory()){
                    getAllFiles(list,f);
                }else{
                    list.add(f.getName());
                }
            }
        }
        return list;
    }



    public static String getFileName(String path){
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }
}
