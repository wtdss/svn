package com.sys.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sys.R;
import com.sys.cache.ACache;
import com.sys.main.adapter.AssetInfoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AssetsActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private TextView mCountingButton;
    private TextView mMyButton;
    ACache mACache;

    public View mLoadingView;
    public LayoutInflater mInflater;
    public int last_index;
    public int total_index;
    public ListView listView;
    public boolean isLoading = false;
    public boolean hasMore = true;
    public AssetInfoListAdapter mAdapter;
    public List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mACache = ACache.get(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_assets);

        TextView headerTitle = findViewById(R.id.header_title);
        headerTitle.setText("资产低耗");

        mCountingButton = findViewById(R.id.assets_counting);
        mCountingButton.setOnClickListener(mListener);
        mMyButton = findViewById(R.id.assets_my);
        mMyButton.setOnClickListener(mListener);

        mInflater = LayoutInflater.from(this);

        listView = findViewById(R.id.asset_list_view);
        list = initList(1, 10);
        mAdapter = new AssetInfoListAdapter(this, list);

        mLoadingView = mInflater.inflate(R.layout.loading_view, null);
        listView.addFooterView(mLoadingView);
        listView.setOnScrollListener(this);
        listView.setAdapter(mAdapter);
    }

    public List<String> initList(int i, int count) {
        List<String> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            list.add("第" + i + "次刷新 , 开始加载" + (j + 1) + "个");
        }
        return list;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        last_index = firstVisibleItem + visibleItemCount;
        total_index = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
            if (!isLoading && hasMore) {
//                listView.addFooterView(mLoadingView);
                isLoading = true;
                mLoadingView.setVisibility(View.VISIBLE);
                onLoad();
            }
        }
    }

    public void onLoad() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (1==1) {
            listView.removeFooterView(mLoadingView);
            mLoadingView = mInflater.inflate(R.layout.load_end_view, null);
            listView.addFooterView(mLoadingView);
            hasMore = false;
        } else {
            list.addAll(initList(2, 5));
            mAdapter.updateView(list);
        }
        loadComplete();
    }

    public void loadComplete() {
        isLoading = false;
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.assets_counting:
                    intent = new Intent(AssetsActivity.this, AssetsCountingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.assets_my:
                    intent = new Intent(AssetsActivity.this, AssetsMyActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
