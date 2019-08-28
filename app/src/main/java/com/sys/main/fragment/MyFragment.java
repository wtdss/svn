package com.sys.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sys.R;
import com.sys.bean.MultipleItem;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.main.activity.LoginActivity;
import com.sys.main.adapter.MyMultiItemQuickAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<MultipleItem> mMultipleItemList;
    private MyMultiItemQuickAdapter mMyMultiItemQuickAdapter;
    private Context mContext;
    private ACache mACache;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mContext = getActivity();
        mACache = ACache.get(mContext);
        mRecyclerView = getActivity().findViewById(R.id.myView);
        initMultipleItemList();
        initQuickAdapter();
        initListener();
    }

    private void initMultipleItemList() {
        mMultipleItemList = new ArrayList<>();
        MultipleItem multipleItem = new MultipleItem(MultipleItem.TYPE_HEADER, 5);
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_INFO, 5);
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_APP_UPDATE, 5);
        mMultipleItemList.add(multipleItem);

//        multipleItem = new MultipleItem(MultipleItem.TYPE_MY_TOOLS_HEADER, 5);
//        mMultipleItemList.add(multipleItem);
//        for (int i = 0; i < 5; i++) {
//            multipleItem = new MultipleItem(MultipleItem.TYPE_MY_TOOLS, 1);
//            multipleItem.title = "功能";
//            mMultipleItemList.add(multipleItem);
//        }

        multipleItem = new MultipleItem(MultipleItem.TYPE_SWITCH, 5);
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_QUIT, 5);
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_CLOSE, 5);
        mMultipleItemList.add(multipleItem);
    }

    private void initQuickAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMyMultiItemQuickAdapter = new MyMultiItemQuickAdapter(mMultipleItemList);
        mRecyclerView.setAdapter(mMyMultiItemQuickAdapter);
    }

    private void initListener() {
        mMyMultiItemQuickAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mMultipleItemList.get(position).getSpans();
            }
        });

        mMyMultiItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println("第  " + position);
                if (mMultipleItemList.get(position).getItemType() == MultipleItem.TYPE_MY_TOOLS) {
                    System.out.println("更新功能");
                }
            }
        });

        mMyMultiItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.my_header_image:
                        System.out.println("头像");
                        break;
                    case R.id.my_app_update:
                        System.out.println("检查更新");
                        break;
                    case R.id.my_all_tools:
                        System.out.println("更多功能");
                        break;
                    case R.id.my_switch:
                        new Thread() {
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .addHeader("Cookie", mACache.getAsString("Cookie"))
                                        .url(RequestType.BASE_URL + "/logout")
                                        .build();
                                try {
                                    Response response = okHttpClient.newCall(request).execute();
                                    if (response.isSuccessful()) {
                                        mACache.remove("Cookie");
                                        Looper.prepare();
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        Looper.loop();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case R.id.my_quit:
                        System.out.println("退出登录");
                        break;
                    case R.id.my_close:
                        System.exit(0);
                        break;
                }
            }
        });
    }
}
