package com.example.kimo.daygo.model;

/**
 * Created by Administrator on 2015/12/14 0014.
 * 消息类
 * content 消息内容
 * type 消息类型
 *      TYPE_RECEIVED 接受消息
 *      TYPE_SEND   发送消息
 */
public class MSG {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1 ;
    private int type;
    private String content;

    public MSG(String content, int type){
        this.content = content ;
        this.type = type ;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
