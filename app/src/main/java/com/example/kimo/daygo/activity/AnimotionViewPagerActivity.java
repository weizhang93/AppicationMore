package com.example.kimo.daygo.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kimo.daygo.R;
import com.example.kimo.daygo.anim.DepthPageTransformer;
import com.example.kimo.daygo.anim.ZoomOutPageTransformer;
import com.example.kimo.daygo.util.LogUtils;
import com.example.kimo.daygo.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/12 0012.
 * 引导动画
 *
 */
public class AnimotionViewPagerActivity extends Activity {

    private ViewPager mViewPager;
    private int[] mImgIds = {R.drawable.t1,R.drawable.t4,R.drawable
            .t6};
    private List<ImageView> mImageViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewpager);

        mViewPager = (ViewPager) findViewById(R.id.vp_begin);
        //添加动画效果
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        //mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                if(position<mImgIds.length) {
                    LogUtils.logDebug(position + "");
                    ImageView imageView = new ImageView(AnimotionViewPagerActivity.this);
                    imageView.setImageResource(mImgIds[position]);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    container.addView(imageView);
                    mImageViewList.add(imageView);
                    return imageView;
                }else if(position==mImgIds.length){
                    LinearLayout ll = new LinearLayout(AnimotionViewPagerActivity.this);
                    ll.setGravity(Gravity.CENTER);
                    ll.setBackground(getResources().getDrawable(R.drawable.t4));

                    Button button = new Button(AnimotionViewPagerActivity.this);
                    button.setText("点击登录");
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup
                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    button.setLayoutParams(params);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(AnimotionViewPagerActivity.this,
                                    LoginActivity.class));
                        }
                    });

                    ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup
                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ll.setLayoutParams(params1);
                    ll.addView(button);

                    container.addView(ll);
                    return ll;
                }
                return null;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViewList.get(position));
            }
        });
    }
}
