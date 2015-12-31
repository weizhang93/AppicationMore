package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.http.LoginThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/28 0028.
 */
public class LoginActivity extends Activity {

    private List<String> list = new ArrayList<>();
    private EditText mName;
    private EditText mPass;
    private Button mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mName = (EditText) findViewById(R.id.name);
        mPass = (EditText) findViewById(R.id.pass);
        mSign = (Button) findViewById(R.id.submit);
//        System.out.println("mName"+mName.getText().toString());
//        System.out.println("mPass"+mPass.getText().toString());
//        System.out.println();

        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.56.1:8080/Test/BServlet";
                new LoginThread(url, mName.getText().toString().trim(),
                        mPass.getText().toString().trim())
                        .start();

            }
        });
    }
}
