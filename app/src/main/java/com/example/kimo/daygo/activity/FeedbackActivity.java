package com.example.kimo.daygo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.adapter.DemoAdapter;
import com.example.kimo.daygo.model.MSG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5 0005.
 */
public class FeedbackActivity extends Activity {

    private List<MSG> mMSGList = new ArrayList<>();
    private ListView mListView;
    private DemoAdapter mDemoAdapter;
    private EditText mInputText;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        initMSG();

        mListView = (ListView)findViewById(R.id.lv_msg);
        mInputText = (EditText)findViewById(R.id.et_input);
        mBtnSend = (Button)findViewById(R.id.btn_send);
        mDemoAdapter = new DemoAdapter(this,mMSGList);
        mListView.setAdapter(mDemoAdapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mInputText.getText().toString().trim();
                MSG msg = new MSG(content, MSG.TYPE_SEND);
                mMSGList.add(msg);
                mDemoAdapter.notifyDataSetChanged();//刷新Adapter
                mListView.setSelection(mMSGList.size());//将ListView定位到最后一行
                mInputText.setText("");
            }
        });
    }

    private void initMSG() {
        MSG msg1 = new MSG("如果有什么问题和建议请直接发消息给我，我会适时回复",MSG.TYPE_RECEIVED);
        mMSGList.add(msg1);
        MSG msg2 = new MSG("Test",MSG.TYPE_SEND);
        mMSGList.add(msg2);
        MSG msg3 = new MSG("Test?",MSG.TYPE_SEND);
        mMSGList.add(msg3);
        MSG msg4 = new MSG("Test",MSG.TYPE_RECEIVED);
        mMSGList.add(msg4);
        MSG msg5 = new MSG("Test",MSG.TYPE_SEND);
        mMSGList.add(msg5);
    }
}
