package com.example.kimo.daygo.util;

import com.google.gson.Gson;

/**
 * Created by Wei.zhang on 2015/12/22 0022.
 * Json工具类
 */
public class JsonUtils {
    private static Gson mGson = new Gson();

    /**
     * 解析json字符串至对象模型
     * @param json  json字符串
     * @param clazz 映射类
     * @param <T>
     * @return
     */
    public static <T>T parseJson(String json,Class<T> clazz){
        return mGson.fromJson(json,clazz);
    }
}
