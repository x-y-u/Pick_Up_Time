package com.example.mytest.Common;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private static OkHttpClient client;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static String post(String url, String json) throws IOException, JSONException {
        if (client == null){
            client = new OkHttpClient();
        }
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static String post(String url, String date,String title,String content, List<String> images) throws IOException {
        if (client == null){
            client = new OkHttpClient();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("date",date);
        builder.addFormDataPart("title",title);
        builder.addFormDataPart("content",content);
        for (int i = 0;i<images.size();i++){
            builder.addFormDataPart("images",null,RequestBody.create(MEDIA_TYPE_PNG, new File(images.get(i))));
        }
        //构建请求体
        RequestBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(url)//地址
                .post(requestBody)//添加请求体
                .build();
        try (Response response = client.newCall(request).execute()) {

            return response.body().string();
        }
    }
}
