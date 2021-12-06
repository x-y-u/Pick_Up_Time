package com.example.mytest.ItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytest.R;

public class ImageFragment extends Fragment {
    private View view;
    private ImageView imageView;
    private int imageid;

    public ImageFragment(int imgid){
        this.imageid = imgid;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.image_frag,container,false);
        imageView = view.findViewById(R.id.image_frag_iv);
        imageView.setImageResource(imageid);
        return view;
    }
}
