package com.sys.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sys.R;
import com.sys.bean.CountingTask;
import com.sys.main.activity.AssetsCountingDetailActivity;

import java.util.List;

public class AssetInfoListAdapter extends BaseAdapter {

    public List<String> list;
    public LayoutInflater inflater;


    public AssetInfoListAdapter() {
    }

    public AssetInfoListAdapter(Context context, List<String> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateView(List<String> nowList) {
        this.list = nowList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.asset_info_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            holder.textView = (TextView) view.findViewById(R.id.textView);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(R.drawable.login_banner1);
        holder.textView.setText(list.get(position));
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}