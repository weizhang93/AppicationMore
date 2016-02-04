package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kimo.daygo.MainActivity;
import com.example.kimo.daygo.R;
import com.example.kimo.daygo.http.LoginThread;
import com.example.kimo.daygo.util.LogUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/28 0028.
 * 登录Activity
 * 2016/01/06 添加姓名暂存
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private List<String> list = new ArrayList<>();
    private EditText mName;
    private EditText mPass;
    private Button mSign;
    private Handler mHandler = new Handler();
    private boolean isLoginSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPass = (EditText) findViewById(R.id.pass);
        mSign = (Button) findViewById(R.id.submit);

        //retrieve you stored text;
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.uName), null);
        if (name != null && !"".equals(name)) {
            mName.setText(name);
        }

        mSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                //暂存姓名 store name
                String name = mName.getText().toString().trim();
                if (name != null && !"".equals(name)) {
                    SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.uName), name);
                    editor.commit();
                }

                //loginThread();

                if (!isLoginSuccess) {
                    Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                }
        }
    }

    private void loginThread() {
        //登录
        FinalHttp fh = new FinalHttp();
        fh.get("http://192.168.56.1:8080/dayGO/FileReceiveServlet" + "?arg0=" + mName + "&arg1="
                + mPass.getText
                (), new
                AjaxCallBack<String>() {

                    @Override
                    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
                        LogUtils.logDebug(current + "/" + count);
                    }

                    public void onSuccess(String s) {
                        LogUtils.logDebug("receive msg", s);
                        isLoginSuccess = true;
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        isLoginSuccess = false;
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(isLoginSuccess){
            finish();
        }
    }
}
