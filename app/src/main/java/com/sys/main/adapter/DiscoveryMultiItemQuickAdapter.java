package com.sys.main.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sys.R;
import com.sys.bean.MultipleItem;

import java.util.List;

public class DiscoveryMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public DiscoveryMultiItemQuickAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TYPE_HEADER, R.layout.layout_header);
        addItemType(MultipleItem.TYPE_LOGIN, R.layout.layout_login_sign_up);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultipleItem item) {
        switch (viewHolder.getItemViewType()) {
            case MultipleItem.TYPE_HEADER:
                viewHolder.setText(R.id.header_title, R.string.title_discovery);
                break;
            case MultipleItem.TYPE_LOGIN:
                viewHolder.addOnClickListener(R.id.login);
                viewHolder.addOnClickListener(R.id.signUp);
                break;
        }
    }

}
