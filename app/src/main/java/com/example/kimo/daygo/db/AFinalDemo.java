package com.example.kimo.daygo.db;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.model.User;
import com.example.kimo.daygo.util.LogUtils;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;

import org.apache.commons.logging.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/14 0014.
 */
public class AFinalDemo extends FinalActivity {

    @ViewInject(id=R.id.tv_afinal)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_afinal);

        //initView1();

        initView2();
    }

    private void initView2() {
        FinalHttp fh = new FinalHttp();
        fh.get("http://www.baidu.com", new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();

            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                textView.setText(current+"/"+count);
            }


            public void onSuccess(String s) {
                textView.setText(s==null?"null":s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    private void initView1() {
        FinalDb db = FinalDb.create(this,"day_go.db",true);

        User user  = new User();
        user.setName("kimo");
        user.setGender("male");
        user.setEmail("785414210@qq.com");
        user.setRegisterDate(new Date());

        db.save(user);

        List<User> userList =  db.findAll(User.class);
        LogUtils.logDebug("AfinalOrmDemoActivity", "用户数量：" + (userList != null ? userList.size() : 0));

        textView.setText(userList.get(0).getName() + ":" + user.getRegisterDate());
    }
}
