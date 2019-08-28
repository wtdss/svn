package com.sys.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sys.R;
import com.sys.bean.CountingTask;
import com.sys.bean.PageUtils;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.main.adapter.CountingTaskListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AssetsCountingActivity extends AppCompatActivity {

    private ListView countingTaskListView;
    TextView mIn;
    TextView mFinish;
    private String countingTaskState = "2";
    private List<CountingTask> list;
    private int total;
    ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_assets_counting);
        TextView headerTitle = findViewById(R.id.header_title);
        headerTitle.setText("盘点操作-资产低耗");

        mACache = ACache.get(this);

        mIn = findViewById(R.id.counting_in);
        mIn.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
        mIn.setOnClickListener(mListener);
        mFinish = findViewById(R.id.counting_finish);
        mFinish.setOnClickListener(mListener);

        countingTaskListView = findViewById(R.id.assets_counting_task_list_view);
        myCountingTaskList();
    }


    private void myCountingTaskList() {
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
                            Toast.makeText(AssetsCountingActivity.this, R.string.login_out_of_time, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AssetsCountingActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Looper.loop();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list = new ArrayList<>();
                RequestBody requestBody = new FormBody.Builder()
                        .add("pageNum", "1")
                        .add("limit", "10")
                        .add("countingTaskState", countingTaskState)
                        .build();
                Request request = new Request.Builder()
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Cookie", mACache.getAsString("Cookie"))
                        .url(RequestType.BASE_URL + "/assets/countingTask/getMyList")
                        .post(requestBody)
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            String json = body.string();
                            Type type = new TypeToken<PageUtils<CountingTask>>() {
                            }.getType();
                            PageUtils<CountingTask> pageUtils = new Gson().fromJson(json, type);
                            if (pageUtils != null) {
                                list = pageUtils.getRows();
                                total = pageUtils.getTotal();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (countingTaskState.equals("2")) {
                    handler.sendMessage(handler.obtainMessage(R.layout.counting_task_in_item, list));
                } else {
                    handler.sendMessage(handler.obtainMessage(R.layout.counting_task_finish_item, list));
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            countingTaskListView.setAdapter(new CountingTaskListAdapter(AssetsCountingActivity.this, msg.what, (List<CountingTask>) msg.obj));
        }
    };

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.counting_in:
                    countingTaskState = "2";
                    mIn.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    mFinish.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    myCountingTaskList();
                    break;
                case R.id.counting_finish:
                    countingTaskState = "3";
                    mIn.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    mFinish.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    myCountingTaskList();
                    break;
            }
        }
    };
}
