package com.example.mytest.ItemFragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mytest.R;

public class EventFragment extends Fragment {
    private RelativeLayout mtransparentTop;
    private LinearLayout mlinearlayout;
    private View view;
    private String url = "http://120.27.195.193/files/QuanJiaFu/1/image/1-0.jpeg";
    private String url1 = "http://www.51pptmoban.com/d/file/2014/09/18/b5b0bfe1ce6ad1d1f073a17845ea7dc0.jpg";
    private ImageView event_img1,event_img2;
    private int imgheight;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_event,container,false);
        InitViews();
        InitSize();
        InitDatas();
        InitEvents();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void InitEvents() {
        //imgheight = event_img2.getMeasuredHeight();
    }

    public void InitViews(){
        event_img1 = view.findViewById(R.id.event_img1);
    }


    public void InitDatas(){
        Glide.with(getActivity()).load(url).into(event_img1);
        //Glide.with(getActivity()).load(url).into(event_img2);
    }

    public void InitSize(){


    }

}
