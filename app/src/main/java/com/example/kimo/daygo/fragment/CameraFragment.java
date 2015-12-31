package com.example.kimo.daygo.fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kimo.daygo.R;

import java.io.File;

/**
 * Created by Administrator on 2015/12/16 0016.
 * 无用类，关于音乐播放
 */
public class CameraFragment extends Fragment implements View.OnClickListener{
    private Button button ;
    private Button buttonPlay;
    private Button buttonPause;
    private Button buttonStop;
    private Button buttonIntent;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private File file;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.item_record,null);


//        button = (Button) view.findViewById(R.id.btn_vibrate);
//        buttonPlay = (Button) view.findViewById(R.id.btn_play);
//        buttonPause = (Button) view.findViewById(R.id.btn_pause);
//        buttonStop = (Button) view.findViewById(R.id.btn_stop);
//        buttonIntent = (Button) view.findViewById(R.id.btn_intent);
//
//        button.setOnClickListener(this);
//        buttonPlay .setOnClickListener(this);
//        buttonPause.setOnClickListener(this);
//        buttonStop .setOnClickListener(this);
//        buttonIntent.setOnClickListener(this);
//
//        initMediaPlayer();

        return view;
    }
    public void initMediaPlayer(){
        try {
            String file = new File(Environment.getExternalStorageDirectory(),"a.mp3").getPath();
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_vibrate:
//                Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
//                long[] num = {500,1000,500,1200};
//                vibrator.vibrate(num,-1);
//                break;
//            case R.id.btn_play:
//                if(!mediaPlayer.isPlaying()){
//                    mediaPlayer.start();
//                }
//                break;
//            case R.id.btn_pause:
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.pause();
//                }
//                break;
//            case R.id.btn_stop:
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.reset();
//                    initMediaPlayer();
//                }
//                break;
//            case R.id.btn_intent:
//                Intent i = new Intent(getContext(), PlayActivity.class);
//                startActivity(i);
//        }
//    }
}
