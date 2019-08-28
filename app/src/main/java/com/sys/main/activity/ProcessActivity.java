package com.sys.main.activity;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.widget.Toast;
import com.sys.R;


import com.google.android.material.tabs.TabLayout;
import com.sys.main.adapter.ProcessContentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProcessActivity extends AppCompatActivity {

    private String TAG="ProcessActivity";

    private ProcessContentFragmentAdapter adapter;

    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        TabLayout tabLayout=findViewById(R.id.tab_layout);
        ViewPager viewPager=findViewById(R.id.view_pager);;

        adapter = new ProcessContentFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // 更新适配器数据
        initData();
        adapter.setList(names);
        //return view;


    }



    private void initData() {
        names = new ArrayList<>();
        names.add("待办");
        names.add("申请创建");
        names.add("我的申请");
        names.add("我的审批");
        names.add("草稿箱");
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//        Log.e(TAG, "onFragmentInteraction: uri.toString()");
//        Toast.makeText(this,"交流,角楼", Toast.LENGTH_LONG).show();
//    }
}
