package com.example.mytest.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytest.R;

public class RelaxFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_relax,container,false);
        InitViews();
        InitDates();
        InitTabs();
        AddTabListener();
        return view;
    }

    private void AddTabListener() {
    }

    private void InitTabs(){
    }

    private void InitDates() {
    }

    private void InitViews() {
    }
}