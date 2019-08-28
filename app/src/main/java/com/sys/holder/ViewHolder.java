package com.sys.holder;

import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;

public class ViewHolder {

    private final SparseArray<View> views;
    private View convertView;
    private Adapter adapter;

    public ViewHolder(View convertView) {
        this.views = new SparseArray<View>();
        this.convertView = convertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public Adapter getAdapter() {

        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }
}
