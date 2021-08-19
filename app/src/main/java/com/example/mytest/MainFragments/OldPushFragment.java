package com.example.mytest.MainFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mytest.ItemFragments.ImageFragment;
import com.example.mytest.Common.MyListView;
import com.example.mytest.ItemFragments.ImageFragmentAdapter;
import com.example.mytest.MyAdapter.PushListAdapter;
import com.example.mytest.PersonalActivity;
import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class OldPushFragment extends Fragment {
    private int imgResIds[] = new int[]{R.drawable.head_image1, R.drawable.head_image2,
            R.drawable.head_image3, R.drawable.head_image4, R.drawable.head_image5};
    private ViewPager viewPager;
    private ImageFragmentAdapter imageFragmentAdapter;
    private List<Fragment> fragments;
    private int curindex = 0;
    private int count;
    private View view;
    private LinearLayout dots;
    private Handler handler;
    private MyListView list_news,list_photos;
    private PushListAdapter news_adapter,photos_adapter;
    private ImageButton to_personal;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_push_old,container,false);
        InitViews();
        InitDatas();
        InitPoints();
        InitEvents();
        return view;
    }

    private void InitDatas() {
        count = imgResIds.length;
        fragments = new ArrayList<>();
        for(int i = 0;i<count;i++){
            fragments.add(new ImageFragment(imgResIds[i]));
        }
        imageFragmentAdapter = new ImageFragmentAdapter(getFragmentManager(),fragments);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(imageFragmentAdapter);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        viewPager.setCurrentItem(15);
        viewPager.setPageTransformer(true,new fadeInFadeOut());
        news_adapter = new PushListAdapter(getContext());
        photos_adapter = new PushListAdapter(getContext());
        list_news.setAdapter(news_adapter);
        list_photos.setAdapter(photos_adapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitEvents() {
        handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handler.postDelayed(this, 2500);
            }
        };
        handler.postDelayed(runnable, 2500);
        viewPager.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                handler.removeCallbacks(runnable);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                break;
                            case MotionEvent.ACTION_UP:
                                handler.removeCallbacks(runnable);
                                handler.postDelayed(runnable,2500);
                                break;
                        }
                        return false;
                    }
                }
        );
        to_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonalActivity.class));
            }
        });
    }

    private void InitViews() {
        viewPager = view.findViewById(R.id.push_viewpager);
        dots = view.findViewById(R.id.push_dots);
        list_news = view.findViewById(R.id.push_news);
        list_photos = view.findViewById(R.id.push_photos);
        to_personal = view.findViewById(R.id.to_personal);
    }

    private void InitPoints(){
        for (int i = 0; i < count; i++) {

            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    45, 45);
            //params.setMargins(0, 0, 15, 0);
            iv.setLayoutParams(params);

            iv.setImageResource(R.drawable.dot1);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            dots.addView(iv);

        }
        ((ImageView) dots.getChildAt(curindex))
                .setImageResource(R.drawable.dot2);
    }

    // 定义ViewPager控件页面切换监听器
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            ImageView imageView1 = (ImageView) dots.getChildAt(position%count);
            ImageView imageView2 = (ImageView) dots.getChildAt(curindex);
            if (imageView1 != null) {
                imageView1.setImageResource(R.drawable.dot2);
            }
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.dot1);
            }
            curindex = position%count;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public class fadeInFadeOut implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if(position<1&&position>0){
                page.setAlpha(1-position);
            }
            else if(position>-1&&position<0){
                page.setAlpha(1+position);
            }
        }
    }
}