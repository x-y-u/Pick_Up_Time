package com.example.mytest.MainFragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.EventsActivity;
import com.example.mytest.R;
import com.example.mytest.ScrollView.ScrollParentView;

public class AlbumFragment extends Fragment implements View.OnClickListener {
    private Button to_events;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_album,container,false);
        InitView();
        InitSize();
        InitEvents();
        InitData();
        return view;
    }

    private void InitEvents() {
        to_events.setOnClickListener(this);
    }

    public void InitView(){
        to_events = view.findViewById(R.id.to_events);
    }

    public void InitData(){
    }

    public void InitSize(){
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_events:
                startActivity(new Intent(getActivity(), EventsActivity.class));
                break;
            default:
                break;
        }
    }
}
