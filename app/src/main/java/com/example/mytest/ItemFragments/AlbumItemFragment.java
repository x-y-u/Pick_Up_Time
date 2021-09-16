package com.example.mytest.ItemFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytest.EventsActivity;
import com.example.mytest.R;

public class AlbumItemFragment extends Fragment {
    private View view;
    private int imageid;
    private ImageView imageView;
    private int i;
    public AlbumItemFragment(int i,int imageid){
        this.i = i;
        this.imageid = imageid;
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
        return view;
    }
}
