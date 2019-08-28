package com.sys.bean;

import android.widget.AdapterView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_INFO = 2;
    public static final int TYPE_APP_UPDATE = 3;
    public static final int TYPE_MY_TOOLS_HEADER = 4;
    public static final int TYPE_MY_TOOLS = 5;
    public static final int TYPE_SWITCH = 6;
    public static final int TYPE_QUIT = 7;
    public static final int TYPE_CLOSE = 8;
    public static final int TYPE_BANNER = 9;
    public static final int TYPE_HOME_TOOLS = 10;
    public static final int TYPE_LOGIN = 11;

    private int itemType;

    private int spans;

    /**
     * 标题
     */
    public String title;

    /**
     * 图标
     */
    public Integer ic;

    public AdapterView.OnItemClickListener listener;

    public MultipleItem(int itemType, int spans) {
        this.itemType = itemType;
        this.spans = spans;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpans() {
        return spans;
    }

    public void setSpans(int spans) {
        this.spans = spans;
    }
}
