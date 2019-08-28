package com.sys.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sys.R;
import com.sys.bean.AssetDetail;
import com.sys.bean.CountingTask;
import com.sys.main.activity.AssetsCountingDetailActivity;

import java.util.List;

public class CountingTaskDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<AssetDetail> mList;
    private Integer mTotal;
    private int mView;
    private LayoutInflater layoutInflater;

    public CountingTaskDetailAdapter(Context mContext, int mView, List<AssetDetail> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mView = mView;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(mView, null);
            viewHolder = new ViewHolder();
            viewHolder.inventoryNo = convertView.findViewById(R.id.inventory_no);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.mainCoding = convertView.findViewById(R.id.main_coding);
            viewHolder.secCoding = convertView.findViewById(R.id.sec_coding);
            viewHolder.deptTitle = convertView.findViewById(R.id.dept_title);
            viewHolder.roomName = convertView.findViewById(R.id.room);
            viewHolder.areaTitle = convertView.findViewById(R.id.area);
            viewHolder.areaAddr = convertView.findViewById(R.id.addr);
            viewHolder.secManagerName = convertView.findViewById(R.id.sec_manager);
            viewHolder.actualResponsible = convertView.findViewById(R.id.actual_responsible);
            viewHolder.actualAreaAddr = convertView.findViewById(R.id.actual_area_addr);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AssetDetail detail = mList.get(position);
        viewHolder.inventoryNo.setText("资产存货号  " + detail.getAssetInventoryNo());
        viewHolder.title.setText(detail.getAssetTitle());
        viewHolder.mainCoding.setText(detail.getAssetMainCoding());
        viewHolder.secCoding.setText(detail.getAssetSecCoding());
        viewHolder.deptTitle.setText(detail.getAssetDeptTitle());
        viewHolder.roomName.setText(detail.getAssetRoomName());
        viewHolder.areaTitle.setText(detail.getAssetAreaTitle());
        viewHolder.areaAddr.setText(detail.getAssetAreaAddr());
        viewHolder.secManagerName.setText(detail.getAssetSecManagerName());
        if (detail.getAssetActualResponsibleId() != null && !detail.getAssetActualResponsibleId().equals("")) {
            viewHolder.actualResponsible.setText(detail.getAssetActualResponsibleName() + "(" + detail.getAssetActualResponsibleId() + ")");
            viewHolder.actualAreaAddr.setText(detail.getAssetActualAreaTitle() + "-" + detail.getAssetActualAreaAddr());
        } else {
            LinearLayout assetActualResponsible = convertView.findViewById(R.id.asset_actual_responsible);
            LinearLayout assetActualAreaAddr = convertView.findViewById(R.id.asset_actual_area_addr);
            assetActualResponsible.setVisibility(View.GONE);
            assetActualAreaAddr.setVisibility(View.GONE);
        }
        return convertView;
    }

    private String format(String time) {
        return time.substring(0, 4) + '-' + time.substring(4, 6) + '-' + time.substring(6, 8) + ' ' + time.substring(8, 10) + ':' + time.substring(10, 12) + ':' + time.substring(12, 14);
    }

    private class ViewHolder {
        private TextView inventoryNo;
        private TextView title;
        private TextView mainCoding;
        private TextView secCoding;
        private TextView deptTitle;
        private TextView roomName;
        private TextView areaTitle;
        private TextView areaAddr;
        private TextView secManagerName;
        private TextView actualResponsible;
        private TextView actualAreaAddr;
    }
}