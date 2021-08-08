package com.example.mytest.ItemFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ImageFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;

    public ImageFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position%list.size());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
