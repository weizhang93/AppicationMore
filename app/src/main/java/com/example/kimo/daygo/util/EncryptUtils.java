package com.example.kimo.daygo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Wei.zhang on 2015/12/24 0024.
 * 常用加密算法工具
 */
public class EncryptUtils {

    public static String encode(String str,String method){
        MessageDigest md = null ;
        String dstr = null ;
        try {
            md = MessageDigest.getInstance(method);
            md.update(str.getBytes());
            dstr = new BigInteger(1,md.digest()).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dstr;
    }

    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    public static String encodeSHAString(String str) {
        return encode(str, "SHA");
    }

    public static String decodeBase64String(String str){
//        BASE64Decoder encoder = new BASE64Decoder();
//        return new String(encoder.decodeBuffer(str));
        return "";
    }

    public static String encodeBase64String(String str){
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(str.getBytes());
        return "";
    }

    public static void main(String[] args){
        String user = "Wei.zhang";
        System.out.println("***原始字符串*** "+user);
        System.out.println("***MD5加密*** "+encodeMD5String(user));
        System.out.println("***SHA加密*** "+encodeSHAString(user));
    }
}
