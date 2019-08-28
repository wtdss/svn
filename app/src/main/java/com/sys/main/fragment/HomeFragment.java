package com.sys.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sys.R;
import com.sys.cache.ACache;
import com.sys.main.activity.AssetsActivity;
import com.sys.main.activity.ProcessActivity;
import com.sys.main.activity.WebBrowerActivity;
import com.sys.main.adapter.HomeMultiItemQuickAdapter;
import com.sys.bean.MultipleItem;
import com.sys.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<MultipleItem> mMultipleItemList;
    private HomeMultiItemQuickAdapter mHomeMultiItemQuickAdapter;
    private ACache mACache;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mACache = ACache.get(getActivity());

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner1);

        Banner banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView = getActivity().findViewById(R.id.homeView);
        initMultipleItemList();
        initQuickAdapter();
        initListener();
    }

    private void initMultipleItemList() {
        mMultipleItemList = new ArrayList<>();

        MultipleItem multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "会议室预定";
        multipleItem.ic = R.drawable.ic_boardroom;
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "人员出勤状况";
        multipleItem.ic = R.drawable.ic_attendance;
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "流程管理";
        multipleItem.ic = R.drawable.ic_activiti;
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "资产低耗";
        multipleItem.ic = R.drawable.ic_assets;
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "品质管理";
        multipleItem.ic = R.drawable.ic_quality;
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_HOME_TOOLS, 1);
        multipleItem.title = "系统操作公告";
        multipleItem.ic = R.drawable.ic_notice;
        mMultipleItemList.add(multipleItem);
    }

    private void initQuickAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mHomeMultiItemQuickAdapter = new HomeMultiItemQuickAdapter(mMultipleItemList);
        mRecyclerView.setAdapter(mHomeMultiItemQuickAdapter);
    }

    private void initListener() {
        mHomeMultiItemQuickAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mMultipleItemList.get(position).getSpans();
            }
        });

        mHomeMultiItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        mHomeMultiItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout tool = view.findViewById(R.id.home_tools);
                if (tool != null) {
                    TextView textView = view.findViewById(R.id.home_tools_title);
                    if (textView != null) {
                        if (textView.getText().equals("会议室预定")) {
                            System.out.println("会议室预定");
                        } else if (textView.getText().equals("人员出勤状况")) {
                            System.out.println("人员出勤状况");
                        } else if (textView.getText().equals("流程管理")) {
                            System.out.println("流程管理");
                            Intent intent = new Intent(getActivity(), ProcessActivity.class);
                            startActivity(intent);
//                            Intent intent = new Intent(getActivity(), WebBrowerActivity.class);
//                            startActivity(intent);
                        } else if (textView.getText().equals("资产低耗")) {
                            Intent intent = new Intent(getActivity(), AssetsActivity.class);
                            startActivity(intent);
                        } else if (textView.getText().equals("品质管理")) {
                            System.out.println("品质管理");
                        } else if (textView.getText().equals("系统操作公告")) {
                            System.out.println("系统操作公告");
                        }
                    }
                }
            }
        });
    }
}
