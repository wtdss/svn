package com.sys.main.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sys.R;
import com.sys.bean.MultipleItem;
import com.sys.main.activity.LoginActivity;

import java.util.List;

public class HomeMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public HomeMultiItemQuickAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TYPE_HEADER, R.layout.layout_header);
        addItemType(MultipleItem.TYPE_HOME_TOOLS, R.layout.layout_home_tools);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultipleItem item) {
        switch (viewHolder.getItemViewType()) {
            case MultipleItem.TYPE_HEADER:
                viewHolder.setText(R.id.header_title, R.string.title_home);
                break;
            case MultipleItem.TYPE_BANNER:
                break;
            case MultipleItem.TYPE_HOME_TOOLS:
                if (item.ic != null) {
                    viewHolder.setImageDrawable(R.id.home_tools_image, ContextCompat.getDrawable(mContext, item.ic));
                } else {
                    viewHolder.setImageDrawable(R.id.home_tools_image, ContextCompat.getDrawable(mContext, R.drawable.ic_launcher));
                }
                viewHolder.setText(R.id.home_tools_title, item.title);
                viewHolder.addOnClickListener(R.id.home_tools);
                break;
        }
    }
}
