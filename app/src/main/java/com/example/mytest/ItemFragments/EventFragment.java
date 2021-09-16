package com.example.mytest.ItemFragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CommonUtils;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.MyAdapter.EventImagesRecyclerViewAdapter;
import com.example.mytest.MyAdapter.EventMoviesRecyclerViewAdapter;
import com.example.mytest.MyAdapter.EventMusicsRecyclerViewAdapter;
import com.example.mytest.MyAdapter.EventPeoplesRecyclerViewAdapter;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    private View view;
    private String url = "http://120.27.195.193:8080/qjftest01";
    private List<String> images,people_pics,people_names,movie_pics,movie_names,music_pics,music_names;
    private String year,title,main_image,day,month,content,poetry,address,passage;
    private ImageView event_img1;
    private ImageButton back;
    private TextView[] years = new TextView[3];
    private TextView[] titles = new TextView[2];
    private TextView tv_date,tv_address,tv_content,tv_poetry,tv_address_guard,tv_passage;
    private RecyclerView images_rv,peoples_rv,movies_rv,musics_rv;
    private EventImagesRecyclerViewAdapter image_adapter;
    private EventPeoplesRecyclerViewAdapter people_adapter;
    private EventMoviesRecyclerViewAdapter movie_adapter;
    private EventMusicsRecyclerViewAdapter music_adapter;
    private String s;
    private int pos;

    public EventFragment(int pos){
        this.pos = pos+1;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_event,container,false);
        InitViews();
        InitDatas();
        InitEvents();
        return view;
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public void InitViews(){
        event_img1 = view.findViewById(R.id.event_img1);
        years[0] = view.findViewById(R.id.event_year1);
        years[1] = view.findViewById(R.id.event_year2);
        years[2] = view.findViewById(R.id.event_year3);
        titles[0] = view.findViewById(R.id.event_title1);
        titles[1] = view.findViewById(R.id.event_title2);
        images_rv = view.findViewById(R.id.event_images_rv);
        peoples_rv = view.findViewById(R.id.event_peoples_rv);
        movies_rv = view.findViewById(R.id.event_movies_rv);
        musics_rv = view.findViewById(R.id.event_musics_rv);
        tv_date = view.findViewById(R.id.event_date);
        tv_address = view.findViewById(R.id.event_address);
        tv_content = view.findViewById(R.id.event_content);
        tv_poetry = view.findViewById(R.id.event_poetry);
        tv_address_guard = view.findViewById(R.id.event_address_guard);
        back = view.findViewById(R.id.event_back);
        tv_passage = view.findViewById(R.id.event_passage);
    }


    public void InitDatas(){
        images = new ArrayList<>();
        people_pics = new ArrayList<>();
        people_names = new ArrayList<>();
        movie_pics = new ArrayList<>();
        movie_names = new ArrayList<>();
        music_pics = new ArrayList<>();
        music_names = new ArrayList<>();
        image_adapter = new EventImagesRecyclerViewAdapter(getContext());
        people_adapter = new EventPeoplesRecyclerViewAdapter(getContext());
        movie_adapter = new EventMoviesRecyclerViewAdapter(getContext());
        music_adapter = new EventMusicsRecyclerViewAdapter(getContext());
        LinearLayoutManager image_manager,people_manager,movie_manager,music_manager;
        image_manager = new LinearLayoutManager(getContext());
        image_manager.setOrientation(RecyclerView.HORIZONTAL);
        people_manager = new LinearLayoutManager(getContext());
        people_manager.setOrientation(RecyclerView.HORIZONTAL);
        movie_manager = new LinearLayoutManager(getContext());
        movie_manager.setOrientation(RecyclerView.HORIZONTAL);
        music_manager = new LinearLayoutManager(getContext());
        music_manager.setOrientation(RecyclerView.HORIZONTAL);
        images_rv.setLayoutManager(image_manager);
        images_rv.setAdapter(image_adapter);
        peoples_rv.setLayoutManager(people_manager);
        peoples_rv.setAdapter(people_adapter);
        movies_rv.setAdapter(movie_adapter);
        movies_rv.setLayoutManager(movie_manager);
        musics_rv.setLayoutManager(music_manager);
        musics_rv.setAdapter(music_adapter);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"text_type1.ttf");
        tv_passage.setTypeface(tf);
    }

    public void LoadDatas(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                try {
                    json.put("id",pos+"");
                    s = HttpUtils.post(url,json.toString());
                    JSONObject jsonObject = new JSONObject(s);
                    day = jsonObject.getString("day");
                    month = jsonObject.getString("month");
                    year = jsonObject.getString("year");
                    address = jsonObject.getString("address");
                    passage = jsonObject.getString("passage");
                    content = jsonObject.getString("content");
                    title = jsonObject.getString("title");
                    poetry = jsonObject.getString("poetry");
                    main_image = jsonObject.getString("mian_image");
                    JSONArray array= jsonObject.getJSONArray("images");
                    images.clear();
                    for (int i=0;i<array.length();i++){
                        images.add(array.get(i).toString());
                    }
                    //Log.i("json",array.toString());
                    array = jsonObject.getJSONArray("humans");
                    people_pics.clear();
                    people_names.clear();
                    for (int i=0;i<array.length();i++){
                        people_pics.add(array.getJSONObject(i).getString("headurl"));
                        people_names.add(array.getJSONObject(i).getString("human_name"));
                    }
                    //Log.i("json",array.toString());
                    array = jsonObject.getJSONArray("movies");
                    movie_pics.clear();
                    movie_names.clear();
                    for (int i=0;i<array.length();i++){
                        movie_pics.add(array.getJSONObject(i).getString("imageurl"));
                        movie_names.add(array.getJSONObject(i).getString("movie_name"));
                    }
                    //Log.i("json",array.toString());
                    array = jsonObject.getJSONArray("musics");
                    music_pics.clear();
                    music_names.clear();
                    for (int i=0;i<array.length();i++){
                        music_pics.add(array.getJSONObject(i).getString("imageurl"));
                        music_names.add(array.getJSONObject(i).getString("music_name"));
                    }
//                    Log.i("json",array.toString());
//                    Log.i("json",day);
//                    Log.i("json",month);
//                    Log.i("json",year);
//                    Log.i("json",passage);
//                    Log.i("json",content);
//                    Log.i("json",title);
//                    Log.i("json",address);
//                    Log.i("json",poetry);
//                    Log.i("json",main_image);
                    Message msg = new Message();
                    handler.sendMessage(msg);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            years[0].setText(year+"");
            years[1].setText(year+"");
            years[2].setText(year+"年");
            titles[0].setText("———"+title);
            titles[1].setText(title);
            tv_address.setText(address);
            tv_content.setText(content);
            tv_date.setText(month+"月"+day+"日");
            tv_passage.setText(passage);
            tv_poetry.setText(poetry);
            image_adapter.loading(images);
            people_adapter.loading(people_pics,people_names);
            movie_adapter.loading(movie_pics,movie_names);
            music_adapter.loading(music_pics,music_names);
            Glide.with(getContext()).load(main_image)
                    .placeholder(R.drawable.on_loading)
                    .into(event_img1);
        }
    };
}
