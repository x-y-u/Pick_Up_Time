package com.example.mytest;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.Dialogs.TimeChooseDialog;
import com.example.mytest.MyAdapter.UploadNineGridRecyclerViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemoryUploadActivity extends BaseStatusBarActivity {
    private int request_code = 1;
    private RecyclerView images_rv;
    private UploadNineGridRecyclerViewAdapter adapter;
    //private List<String> images;
    private ImageButton img_back;
    private TextView tv_time,tv_confirm;
    private EditText et_title,et_content;
    private int year,month,day;
    private String title,content;
    private ImageView checked_iv;
    private Boolean is_checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_upload);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeChooseDialog dialog = TimeChooseDialog.newInstance(year,month-1,day);
                dialog.setOnDateChangeListener(new TimeChooseDialog.OnDateChangeListener() {
                    @Override
                    public void OnDateChange(int year, int month, int day) {
                        String date = String.format("%d-%d-%d", year, month+1, day);
                        tv_time.setText(date);
                        MemoryUploadActivity.this.year = year;
                        MemoryUploadActivity.this.month = month+1;
                        MemoryUploadActivity.this.day = day;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c = Calendar.getInstance();
                        try {
                            c.setTime(format.parse(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //MemoryUploadActivity.this.weekday = c.get(Calendar.DAY_OF_WEEK);
                    }
                });
                dialog.show(getSupportFragmentManager(),"MemoryUpload");
            }
        });
        checked_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_checked){
                    checked_iv.setImageResource(R.mipmap.circle_unchecked);
                    is_checked = false;
                }else {
                    checked_iv.setImageResource(R.mipmap.circle_checked);
                    is_checked = true;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1){
            List<String> s =data.getExtras().getStringArrayList("images");
            int index = data.getIntExtra("position",0);
            setImages(index,s);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImages(int index,List<String> new_images) {
        if (new_images.size()>1||index == adapter.getImagesNum()){
            adapter.addImages(new_images);
        }else {
            adapter.setImages(index,new_images.get(0));
        }
    }

    private void InitDatas() {
        is_checked = false;
        images_rv.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new UploadNineGridRecyclerViewAdapter(this);
        images_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new UploadNineGridRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int pos) {
                Intent intent = new Intent(MemoryUploadActivity.this,ChooseImageActivity.class);
                if (adapter.getImagesNum()==9){
                    intent.putExtra("maxsize",1);
                }else {
                    intent.putExtra("maxsize",9-adapter.getImagesNum());
                }
                intent.putExtra("position",pos);
                startActivityForResult(intent,request_code);
            }
        });
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tv_time.setText(year+"-"+month+"-"+day);
        //weekday = calendar.get(Calendar.DAY_OF_WEEK);
    }

    private void InitViews() {
        images_rv = findViewById(R.id.memory_upload_images_rv);
        img_back = findViewById(R.id.memory_upload_back);
        tv_time = findViewById(R.id.memory_upload_time);
        tv_confirm = findViewById(R.id.memory_upload_confirm);
        et_title = findViewById(R.id.memory_upload_title);
        et_content = findViewById(R.id.memory_upload_content);
        checked_iv = findViewById(R.id.memory_upload_checked_iv);
    }

    public void upload(){
        Intent intent = getIntent();
        title = et_title.getText().toString();
        content = et_content.getText().toString();
        //String date = String.format("%d-%d-%d", year, month, day);
        List<String> images = adapter.getImages();
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        intent.putExtra("year",year);
        intent.putExtra("month",month);
        intent.putExtra("day",day);
        Bundle b = new Bundle();
        b.putStringArrayList("images",(ArrayList<String>)images);
        intent.putExtras(b);
        setResult(2,intent);
//        String url = "";
//        try {
//            HttpUtils.post(url,date,title,content,images);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        finish();
    }
}