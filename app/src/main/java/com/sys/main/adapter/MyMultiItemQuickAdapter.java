package com.sys.main.adapter;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sys.R;
import com.sys.bean.MultipleItem;

import java.util.List;

public class MyMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MyMultiItemQuickAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TYPE_HEADER, R.layout.layout_my_header);
        addItemType(MultipleItem.TYPE_INFO, R.layout.layout_my_info);
        addItemType(MultipleItem.TYPE_APP_UPDATE, R.layout.layout_my_app_update);
        addItemType(MultipleItem.TYPE_MY_TOOLS_HEADER, R.layout.layout_my_tools_header);
        addItemType(MultipleItem.TYPE_MY_TOOLS, R.layout.layout_my_tools);
        addItemType(MultipleItem.TYPE_SWITCH, R.layout.layout_my_switch);
        addItemType(MultipleItem.TYPE_QUIT, R.layout.layout_my_quit);
        addItemType(MultipleItem.TYPE_CLOSE, R.layout.layout_my_close);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultipleItem item) {
        switch (viewHolder.getItemViewType()) {
            case MultipleItem.TYPE_HEADER:
                viewHolder.setText(R.id.header_title, R.string.title_my);
                viewHolder.addOnClickListener(R.id.setting);
                break;
            case MultipleItem.TYPE_INFO:
                viewHolder.setImageDrawable(R.id.my_header_image, ContextCompat.getDrawable(mContext, R.drawable.header_image));
                viewHolder.setText(R.id.my_name, "韩朋朋");
                viewHolder.setText(R.id.my_mobile, "18571181023");
                viewHolder.addOnClickListener(R.id.my_header_image);
                break;
            case MultipleItem.TYPE_APP_UPDATE:
                viewHolder.addOnClickListener(R.id.my_app_update);
                break;
            case MultipleItem.TYPE_MY_TOOLS_HEADER:
                viewHolder.addOnClickListener(R.id.my_all_tools);
                break;
            case MultipleItem.TYPE_MY_TOOLS:
                viewHolder.setImageDrawable(R.id.my_tools_image, ContextCompat.getDrawable(mContext, R.drawable.ic_launcher));
                viewHolder.setText(R.id.my_tools_title, item.title);
                break;
            case MultipleItem.TYPE_SWITCH:
                viewHolder.addOnClickListener(R.id.my_switch);
                break;
            case MultipleItem.TYPE_QUIT:
                viewHolder.addOnClickListener(R.id.my_quit);
                break;
            case MultipleItem.TYPE_CLOSE:
                viewHolder.addOnClickListener(R.id.my_close);
                break;
        }
    }
}
