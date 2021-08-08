package com.example.mytest.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mytest.MyAdapter.PostListAdapter;
import com.example.mytest.R;
import com.example.mytest.db.PostBean;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    private ListView listView;
    private List<PostBean> postBeans;
    private List<Integer> images;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_post,container,false);
        InitViews();
        InitDatas();
        return view;
    }

    private void InitViews() {
        listView = view.findViewById(R.id.post_listview);
    }

    private void InitDatas() {
        postBeans = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.head_image1);
        images.add(R.drawable.head_image2);
        images.add(R.drawable.head_image3);
        images.add(R.drawable.head_image4);
        images.add(R.drawable.head_image5);
        images.add(R.drawable.head_image6);
        images.add(R.drawable.head_image7);
        images.add(R.drawable.head_image8);
        images.add(R.drawable.head_image9);
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image1,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image2,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image3,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image4,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image5,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image6,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image7,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image8,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image9,9,30,"这是文本内容",images));
        postBeans.add(new PostBean("我是昵称",R.drawable.head_image1,9,30,"这是文本内容",images));
        listView.setAdapter(new PostListAdapter(getActivity(),postBeans));

    }
}
