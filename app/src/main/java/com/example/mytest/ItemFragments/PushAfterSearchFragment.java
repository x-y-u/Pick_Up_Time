package com.example.mytest.ItemFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mytest.AlbumTemplateActivity;
import com.example.mytest.MakeAlbumActivity;
import com.example.mytest.MakeMusicAlbumActivity;
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter4;
import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class PushAfterSearchFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private PushImageRecyclerViewAdapter4 adapter;
    private List<Integer> images;
    private Boolean state;
    private RelativeLayout bottom;
    private ImageView[] imageViews = new ImageView[3];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.push_after_search_frag,container,false);
        InitViews();
        InitDatas();
        InitEvents();
        return view;
    }

    private void InitEvents() {
        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MakeMusicAlbumActivity.class);
                intent.putExtra("state",1);
                startActivity(intent);
            }
        });
        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MakeAlbumActivity.class);
                intent.putExtra("state",1);
                startActivity(intent);
            }
        });

    }

    private void InitViews() {
        recyclerView = view.findViewById(R.id.push_after_search_rv);
        bottom = view.findViewById(R.id.push_after_search_bottom);
        imageViews[0] = view.findViewById(R.id.push_after_search_iv1);
        imageViews[1] = view.findViewById(R.id.push_after_search_iv2);
        imageViews[2] = view.findViewById(R.id.push_after_search_iv3);
    }

    private void InitDatas() {
        state = false;
        images = new ArrayList<>();
        bottom.setVisibility(View.INVISIBLE);
        images.add(R.drawable.search_result1);
        images.add(R.drawable.search_result2);
        images.add(R.drawable.search_result3);
        images.add(R.drawable.search_result4);
        images.add(R.drawable.search_result5);
        images.add(R.drawable.search_result6);
        images.add(R.drawable.search_result7);
        images.add(R.drawable.search_result8);
        adapter = new PushImageRecyclerViewAdapter4(getContext(),images);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new PushImageRecyclerViewAdapter4.OnItemClickListener() {
            @Override
            public void OnClick() {
            }

            @Override
            public void OnLongClick() {
                if (!state){
                    adapter.setState(true);
                    bottom.setVisibility(View.VISIBLE);
                    state = true;
                }
            }
        });
    }

    public void setState(Boolean state){
        this.state = false;
        adapter.setState(state);
    }
}
