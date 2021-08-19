package com.example.mytest.xytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mytest.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class TestActivity extends AppCompatActivity {
    private BottomSheetBehavior mBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        InitViews();
        InitBottomBehavior();

    }

    private void InitBottomBehavior() {

    }

    private void InitViews() {
    }
}