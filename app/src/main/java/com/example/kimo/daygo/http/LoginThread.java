package com.example.kimo.daygo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Administrator on 2015/12/28 0028.
 */
public class LoginThread extends Thread {
    private String url;
    private String arg0;
    private String arg1;

    public LoginThread(String url,String arg0,String arg1){
        this.url = url ;
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    @Override
    public void run() {
        super.run();
        //doGet();
        doPost();
    }

    private void doGet() {
            url = url+"?arg0="+arg0+"&arg1="+arg1;

        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuilder sb = new StringBuilder();
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("GETGETGETGETGET"+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(){
        try {
            Properties properties = System.getProperties();
            properties.list(System.out);

            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            OutputStream out = conn.getOutputStream();
            String content = "arg0="+arg0+"&arg1="+arg1;
            out.write(content.getBytes());
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuilder sb = new StringBuilder();
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("POSTPOSTPOSTPOST" + sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
