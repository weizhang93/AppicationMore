package com.example.kimo.daygo.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.activity.PlayActivity;
import com.example.kimo.daygo.adapter.RecordAdapter;
import com.example.kimo.daygo.model.Record;
import com.example.kimo.daygo.util.LocationUtils;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;
import com.example.kimo.daygo.util.Utils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Wei.zhang on 2015/12/16 0016.
 * 记录Fragment
 * <p/>
 * 得到位置信息（）
 * 加载ListView（）
 *
 * 2015.11.37
 * 尝试加入下拉刷新,失败。
 */
public class RecordFragment extends Fragment {
    public static final String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private static final int MENU_UPLOAD = 1;
    private static final int MENU_DELETE = 2;
    private static final int MENU_SHARE = 3;
    private RecordAdapter mAdapter;
    private List<Record> mList = new ArrayList<>();
    private List<String> mList1 = new ArrayList<>();
    private Context context = MyApplication.getContext();
    private View mRootView ;//缓存Fragment View

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(context, "又调用一次onCreate", Toast.LENGTH_SHORT).show();
        if(mRootView==null){
            mRootView = inflater.inflate(R.layout.fragment_record, null);
            File file = new File(Environment.getExternalStorageDirectory() + File.separator +
                    "DAYGO/video/");
            Utils.getAllFiles(mList1, file);//取得所有file文件夹下的视频文件名
            for (int i = 0; i < mList1.size(); i++) {//将文件名传到数据源里
                Record record = new Record(mList1.get(i), LocationUtils.getCurLocationGPS());
                mList.add(record);
            }
            mAdapter = new RecordAdapter(getContext(), mList);
            final ListView listView = (ListView)mRootView.findViewById(R.id.record_lv);
            listView.setAdapter(mAdapter);

            //点击监听,进入播放
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, PlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("wei", mList1.get(position));//将文件名传入PlayActivity
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            //注册长点击事件
            this.registerForContextMenu(listView);
            listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(Menu.NONE,MENU_UPLOAD,Menu.NONE,R.string.menu_upload).setIcon(android.R
                            .drawable.ic_menu_send);
                    menu.add(Menu.NONE,MENU_DELETE,Menu.NONE,R.string.menu_delete).setIcon(android.R
                            .drawable.ic_menu_delete);
                    menu.add(Menu.NONE,MENU_SHARE,Menu.NONE,R.string.menu_share).setIcon(android.R
                            .drawable.ic_menu_delete);
                    menu.setHeaderTitle("more");

                }
            });

            //下拉监听,即刷新列表
//            listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//                class GetDataTask extends AsyncTask<Void,Void,List<String>>{
//                    @Override
//                    protected List<String> doInBackground(Void... params) {
//                        File file = new File(Environment.getExternalStorageDirectory() + File.separator +
//                                "DAYGO/video/");
//                        Utils.getAllFiles(mList1, file);//取得所有file文件夹下的视频文件名
//                        return mList1;
//                    }
//
//                    @Override
//                    protected void onPostExecute(List<String> list) {
//                        for (int i = 0; i < list.size(); i++) {//将文件名传到数据源里
//                            Record record = new Record(list.get(i), LocationUtils.getCurLocationGPS());
//                            mList.add(record);
//                        }
//
//                        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
//                        mAdapter.notifyDataSetChanged();
//                        // Call onRefreshComplete when the list has been refreshed.
//                        listView.onRefreshComplete();
//                        super.onPostExecute(list);
//                    }
//                }
//
//                @Override
//                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                    //显示下拉时间
//                    String label = Utils.dataFormat("HH:mm:ss");
//                    listView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                    listView.getLoadingLayoutProxy().setRefreshingLabel("刷新中。。。");
//                    listView.getLoadingLayoutProxy().setReleaseLabel("下拉刷新。。。");
//
//                    if (!isRefreshing) {
//                        isRefreshing = true;
//                        new GetDataTask().execute();//启动新线程处理
//                    } else {
//                        listView.onRefreshComplete();
//                    }
//
//                }
//            });

        }
        //View view = inflater.inflate(R.layout.fragment_record, null);
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }



        return mRootView;
    }
//
//    private void playVideo(String s) {
//
//        Intent i = new Intent(context, PlayActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("key",s);
//        i.putExtras(bundle);
//        startActivity(i);
//    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case MENU_UPLOAD:
                upload(menuInfo.position);
                return true;
            case MENU_DELETE:
                delete();
                return true;
            case MENU_SHARE:
                share(menuInfo.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void delete() {

    }

    private void upload(int position) {
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
    }

    private void share(int position){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}
