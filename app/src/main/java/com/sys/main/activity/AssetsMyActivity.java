package com.sys.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.sys.R;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AssetsMyActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationBar bottomNavigationView;
    ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mACache = ACache.get(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_assets_counting);

        TextView headerTitle = findViewById(R.id.header_title);
        headerTitle.setText("盘点操作-资产低耗");

        new Thread() {
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    Request request = new Request.Builder()
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Cookie", mACache.getAsString("Cookie"))
                            .url(RequestType.BASE_URL + "/user/isLogin")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        if (json.equals("false")) {
                            mACache.remove("Cookie");
                            Looper.prepare();
                            Toast.makeText(AssetsMyActivity.this, R.string.login_out_of_time, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AssetsMyActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Looper.loop();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, Object> map = new HashMap<>();
                map.put("pageNum", 1);
                map.put("limit", 6);
                map.put("countingTaskState", 2);
                String data = new Gson().toJson(map);
                RequestBody requestBody = RequestBody.create(RequestType.MEDIA_TYPE_JSON, data);

                Request request = new Request.Builder()
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Cookie", mACache.getAsString("Cookie"))
                        .url(RequestType.BASE_URL + "/assets/countingTask/getMyList")
                        .post(requestBody)
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        System.out.println(json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
