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
    private int imageId;
    private ImageView imageView;
    public ImageFragment(int imageId){
        this.imageId = imageId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_image,null);
        imageView = view.findViewById(R.id.push_iv);
        imageView.setImageResource(imageId);
        return view;
    }
}
