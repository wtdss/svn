package com.sys.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sys.R;
import com.sys.bean.CountingTask;
import com.sys.main.activity.AssetsCountingDetailActivity;

import java.util.List;

public class CountingTaskListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CountingTask> mList;
    private int mView;
    private LayoutInflater layoutInflater;

    public CountingTaskListAdapter(Context mContext, int mView, List<CountingTask> mList) {
        this.mContext = mContext;
        this.mView = mView;
        this.mList = mList;
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
            viewHolder.countingTaskTime = convertView.findViewById(R.id.counting_task_time);
            viewHolder.countingType1Value = convertView.findViewById(R.id.counting_type_1_value);
            viewHolder.countingType2Value = convertView.findViewById(R.id.counting_type_2_value);
            viewHolder.countingType3Value = convertView.findViewById(R.id.counting_type_3_value);
            viewHolder.countingType4Value = convertView.findViewById(R.id.counting_type_4_value);
            viewHolder.countingCreatorValue = convertView.findViewById(R.id.counting_creator_value);
            viewHolder.countingTotalValue = convertView.findViewById(R.id.counting_total_value);
            viewHolder.countingCreateTime = convertView.findViewById(R.id.counting_create_time);
            viewHolder.countingFinishTime = convertView.findViewById(R.id.counting_finish_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CountingTask countingTask = mList.get(position);
        viewHolder.countingTaskTime.setText(countingTask.getCountingTaskTime());
        viewHolder.countingType1Value.setText(countingTask.getCountingTaskUncountedQuantity());
        viewHolder.countingType2Value.setText(countingTask.getCountingTaskCountedQuantity());
        viewHolder.countingType3Value.setText(countingTask.getCountingTaskCountedQuantityOutOfPosition());
        viewHolder.countingType4Value.setText(countingTask.getCountingTaskCountedQuantityByOther());
        viewHolder.countingCreatorValue.setText(countingTask.getCountingTaskCreaterName() + "(" + countingTask.getCountingTaskCreaterId() + ")");
        viewHolder.countingTotalValue.setText(countingTask.getCountingTaskTotalQuantity());
        viewHolder.countingCreateTime.setText("创建时间：" + format(countingTask.getCountingTaskTime()));
        if (viewHolder.countingFinishTime != null) {
            viewHolder.countingFinishTime.setText("完成时间：" + countingTask.getCountingTaskEditTime());
        }

        TextView countingDetail = convertView.findViewById(R.id.counting_detail);
        if (countingDetail != null) {
            countingDetail.setTag(countingTask);
            countingDetail.setOnClickListener(onClickListener);
        }
        return convertView;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            CountingTask countingTask = (CountingTask) v.getTag();
            Intent mIntent = new Intent(mContext, AssetsCountingDetailActivity.class);
            mIntent.putExtra("counting_task", countingTask);
            mContext.startActivity(mIntent);
        }
    };

    private String format(String time) {
        return time.substring(0, 4) + '-' + time.substring(4, 6) + '-' + time.substring(6, 8) + ' ' + time.substring(8, 10) + ':' + time.substring(10, 12) + ':' + time.substring(12, 14);
    }

    private class ViewHolder {
        private TextView countingTaskTime;
        private TextView countingType1Value;
        private TextView countingType2Value;
        private TextView countingType3Value;
        private TextView countingType4Value;
        private TextView countingCreatorValue;
        private TextView countingTotalValue;
        private TextView countingCreateTime;
        private TextView countingFinishTime;
    }
}