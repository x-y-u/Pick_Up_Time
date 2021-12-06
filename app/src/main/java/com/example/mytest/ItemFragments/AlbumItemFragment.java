package com.example.mytest.ItemFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytest.EventsActivity;
import com.example.mytest.GalleryActivity;
import com.example.mytest.R;
import com.example.mytest.VideoActivity;

public class AlbumItemFragment extends Fragment {
    private View view;
    private int imageid,progressid;
    private ImageView imageView,progress,to_video;
    private LinearLayout to_gallery;
    private int i;
    public AlbumItemFragment(int i,int imageid,int progressid){
        this.i = i;
        this.imageid = imageid;
        this.progressid = progressid;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.frag_album_item,container,false);
        imageView = view.findViewById(R.id.album_item_iv);
        //imageView.setAdjustViewBounds(true);
        imageView.setImageResource(imageid);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EventsActivity.class);
                intent.putExtra("pos",i);
                getContext().startActivity(intent);
            }
        });
        to_gallery = view.findViewById(R.id.album_item_to_gallery);
        to_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GalleryActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
        progress = view.findViewById(R.id.album_progress_iv);
        progress.setImageResource(progressid);
        to_video = view.findViewById(R.id.album_item_to_video);
        to_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
        return view;
    }
}
