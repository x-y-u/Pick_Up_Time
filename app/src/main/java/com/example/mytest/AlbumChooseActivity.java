package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.ItemFragments.AlbumItemFragment;
import com.example.mytest.MyAdapter.FragmentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlbumChooseActivity extends AppCompatActivity {
    private List<Fragment> list;
    private ViewPager viewPager;
    private int[] progresses = new int[4];
    private ImageView progress;
    private ImageButton back;
    private FragmentsAdapter adapter;
    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_choose);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //progress.setImageResource(progresses[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void InitDatas() {
        list = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.album_choose_1);
        images.add(R.drawable.album_choose_2);
        images.add(R.drawable.album_choose_3);
        images.add(R.drawable.album_choose_4);
        progresses[0] = R.drawable.album_progress_1;
        progresses[1] = R.drawable.album_progress_2;
        progresses[2] = R.drawable.album_progress_3;
        progresses[3] = R.drawable.album_progress_4;
        for (int i=0;i<4;i++){
            list.add(new AlbumItemFragment(i,images.get(i),progresses[i]));
        }
        adapter = new FragmentsAdapter(getSupportFragmentManager(),list);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        //viewPager.setPageTransformer(true,new fadeInFadeOut());
        viewPager.setPageMargin(CommonUtils.dip2px(this,40));
        viewPager.setCurrentItem(0);
    }

    private void InitViews() {
        back = findViewById(R.id.album_choose_back);
        viewPager = findViewById(R.id.album_choose_vp);
        //progress = findViewById(R.id.album_choose_progress_iv);
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