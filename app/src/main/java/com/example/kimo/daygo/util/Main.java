package com.example.kimo.daygo.util;

import java.io.File;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class Main {
    public static void main(String args[]){
        String date = Utils.dateFormat(System.currentTimeMillis(),"yy/MM/dd");
        System.out.println(date);
//        File path = new File("D:\\UserData\\My Documents\\My Pictures\\粗线性图标\\dayGo");
    }
}
