package com.example.mytest.ItemFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CommonUtils;
import com.example.mytest.R;
import com.example.mytest.ScrollView.ScrollParentView;

public class EventFragment extends Fragment {
    private RelativeLayout mtransparentTop;
    private LinearLayout mlinearlayout;
    private ScrollParentView mScrollParentView;
    private View view;
    private String url = "http://120.27.195.193/files/QuanJiaFu/1/image/1-0.jpeg";
    private String url1 = "http://www.51pptmoban.com/d/file/2014/09/18/b5b0bfe1ce6ad1d1f073a17845ea7dc0.jpg";
    private ImageView event_img;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_event,container,false);
        InitViews();
        InitSize();
        InitDatas();
        return view;
    }

    public void InitViews(){
        mtransparentTop = view.findViewById(R.id.transparentTop);
        mlinearlayout = view.findViewById(R.id.content_list);
        mScrollParentView = view.findViewById(R.id.scrollparentview);
        event_img = view.findViewById(R.id.event_img);
    }

    public void InitClickListener(){
    }

    public void InitDatas(){
        Glide.with(getActivity()).load(url).into(event_img);
    }

    public void InitSize(){

        //设置透明部分的高度
//        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mtransparentTop.getLayoutParams();
//        params1.height = CommonUtils.getWindowHeight(getContext())/6 * 5;
//        mtransparentTop.setLayoutParams(params1);

    }

    public void InitAdapter(){

    }
}
