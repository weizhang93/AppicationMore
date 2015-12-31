package com.example.kimo.daygo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.kimo.daygo.R;
import com.example.kimo.daygo.adapter.DemoAdapter;
import com.example.kimo.daygo.model.MSG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class ChatFragment extends Fragment {

    private List<MSG> mMSGList = new ArrayList<>();
    private ListView mListView;
    private DemoAdapter mDemoAdapter;
    private EditText mInputText;
    private Button mBtnSend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_msg,null);

        initMSG();
        mListView = (ListView) view.findViewById(R.id.lv_msg);
        mInputText = (EditText) view.findViewById(R.id.et_input);
        mBtnSend = (Button) view.findViewById(R.id.btn_send);
        mDemoAdapter = new DemoAdapter(getContext(),mMSGList);
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
        return view;
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
