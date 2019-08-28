package com.sys.main.adapter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sys.main.fragment.CollectFragment;
import com.sys.main.fragment.MsgContentFragment;
import com.sys.main.fragment.ToDo_ProcessFragment;
import com.sys.main.fragment.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息内容子页面适配器
 */
public class ProcessContentFragmentAdapter extends FragmentPagerAdapter {
    private List<String> names;

    public ProcessContentFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.names = new ArrayList<>();
    }

    /**
     * 数据列表
     *
     * @param datas
     */
    public void setList(List<String> datas) {
        this.names.clear();
        this.names.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            ToDo_ProcessFragment fragment =new ToDo_ProcessFragment();
            return fragment;
        }
        if(position==1){
            WebViewFragment fragment =WebViewFragment.newInstance("/process/apply",null);
            return fragment;
        }
        if(position==2){
            CollectFragment fragment =new CollectFragment();
            return fragment;
        }
        MsgContentFragment fragment = new MsgContentFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("name", names.get(position));
        bundle.putString("name", position+"");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = names.get(position);
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
