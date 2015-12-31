package com.example.kimo.daygo.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Wei.zhang on 2015/12/22 0022.
 */
public class MyApplication extends Application {
    private static Context context;
    public static ArrayList<Activity> activityArrayList ;
    public static String PACKAGE_NAME = MyApplication.class.getPackage().getName();

    @Override
    public void onCreate() {
        super.onCreate();
        activityArrayList = new ArrayList<>();
        context = getApplicationContext();
    }

    public static void addActivity(Activity activity){
        activityArrayList.add(activity);
    }

    public static void removeAllActivity(){
        for(Activity a : activityArrayList){
            a.finish();
        }
        activityArrayList.clear();
    }

    public static Context getContext(){
        return context;
    }
}
