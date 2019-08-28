package com.sys.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sys.R;
import com.sys.bean.CountingTask;
import com.sys.bean.PageUtils;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.entity.ToDoProcess;
import com.sys.main.activity.AssetsCountingActivity;
import com.sys.main.activity.LoginActivity;
import com.sys.main.adapter.CollectRecycleAdapter;
import com.sys.main.adapter.CountingTaskListAdapter;

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

/**
 * A simple {@link Fragment} subclass.

 */
public class ToDo_ProcessFragment extends Fragment {
    private View view;//定义view用来设置fragment的layout
    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<ToDoProcess> toDoProcessList = new ArrayList<ToDoProcess>();
    //自定义recyclerveiw的适配器
    private CollectRecycleAdapter mCollectRecyclerAdapter;
    ACache mACache;
    private Context context;
    //test
    private List<CountingTask> list;
    public static final int SHOW_R= 1;
    private int total;
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_R:
                    // 在这里可以进行UI操作
                    //text.setText("Nice to meet you");
                    initRecyclerView();
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_to_do__process, container, false);
        context=getContext();
        mACache = ACache.get(context);
        //对recycleview进行配置
        //initRecyclerView();
        //模拟数据
        //initData();
        getTodoList();
        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
        for (int i=0;i<10;i++){
            ToDoProcess toDoProcess =new ToDoProcess();
            toDoProcess.setProcessId("100"+i);
            toDoProcessList.add(toDoProcess);
        }
    }

    private void getTodoList() {
        new Thread() {
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    Request request = new Request.Builder()
                            .addHeader("Connection", "keep-alive")
                           // .addHeader("Cookie", mACache.getAsString("Cookie"))
                            .addHeader("Cookie", "DIV23Shiro=63e3b44b-1ce2-44f1-a45a-1cc788ea6719")
                            .url(RequestType.BASE_URL + "/user/isLogin")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        if (json.equals("false")) {
                            mACache.remove("Cookie");
                            Looper.prepare();
                            Toast.makeText(context, R.string.login_out_of_time, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
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
                       // .add("countingTaskState", countingTaskState)
                        .build();
                Request request = new Request.Builder()
                        .addHeader("Connection", "keep-alive")
                        //.addHeader("Cookie", mACache.getAsString("Cookie"))
                        .addHeader("Cookie", "DIV23Shiro=63e3b44b-1ce2-44f1-a45a-1cc788ea6719")
                        .url(RequestType.BASE_URL + "/process/listMyTask")
                        .post(requestBody)
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            String json = body.string();
                            Log.e("json:::", json);
                            Type type = new TypeToken<PageUtils<ToDoProcess>>() {
                            }.getType();
                            PageUtils<ToDoProcess> pageUtils = new Gson().fromJson(json, type);
                            if (pageUtils != null) {
                                toDoProcessList = (ArrayList<ToDoProcess>) pageUtils.getRows();
                                Log.e("toDoProcessList:::", toDoProcessList.toString());
                                total = pageUtils.getTotal();
                            }
                            Message message = new Message();
                            message.what = SHOW_R;
                            handler.sendMessage(message); // 将Message对象发送出去
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.collect_recyclerView);
        //创建adapter
        mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), toDoProcessList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new CollectRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ToDoProcess data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(),"我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
