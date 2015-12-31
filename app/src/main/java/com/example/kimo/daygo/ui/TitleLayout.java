package com.example.kimo.daygo.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kimo.daygo.R;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet set) {
        super(context, set);
        LayoutInflater.from(context).inflate(R.layout.activity_top,this);
        Button btnBack = (Button) findViewById(R.id.btn_back);
        Button btnGo = (Button) findViewById(R.id.btn_go);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

        btnGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"GOGOGO",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
