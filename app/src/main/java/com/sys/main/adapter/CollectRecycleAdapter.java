package com.sys.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sys.entity.ToDoProcess;

import java.util.ArrayList;
import com.sys.R;

public class CollectRecycleAdapter extends RecyclerView.Adapter<CollectRecycleAdapter.myViewHodler> {
    private Context context;
    private ArrayList<ToDoProcess> toDoProcessList;

    //创建构造函数
    public CollectRecycleAdapter(Context context, ArrayList<ToDoProcess> toDoProcessList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.toDoProcessList = toDoProcessList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item_layout, null);
        return new myViewHodler(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        ToDoProcess data = toDoProcessList.get(position);
//        holder.mItemGoodsImg;
        holder.processId.setText(data.processId);//获取实体类中的name字段并设置
        holder.processId.setVisibility(View.GONE);
        holder.taskId.setText(data.taskId);//获取实体类中的price字段并设置
        holder.taskId.setVisibility(View.GONE);
        holder.taskName.setText(data.taskName);
        holder.recieveTime.setText("接收时间："+data.recieveTime);
        holder.initiator.setText("申请人："+data.initiator);
        holder.processStartTime.setText("申请时间："+data.processStartTime);
        holder.bussinessKey.setText("流程编号："+data.bussinessKey);
        holder.activiName.setText("审批节点："+data.activiName);


    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return toDoProcessList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
        private TextView processId;
        private TextView taskId;
        private TextView taskName;
        private TextView recieveTime;
        private TextView initiator;
        private TextView processStartTime;
        private TextView bussinessKey;
        private TextView activiName;


        public myViewHodler(View itemView) {
            super(itemView);
            processId = (TextView) itemView.findViewById(R.id.processId);
            taskId = (TextView) itemView.findViewById(R.id.taskId);
            taskName = (TextView) itemView.findViewById(R.id.taskName);
            recieveTime = (TextView) itemView.findViewById(R.id.recieveTime);
            initiator = (TextView) itemView.findViewById(R.id.initiator);
            processStartTime= (TextView) itemView.findViewById(R.id.processStartTime);
            bussinessKey = (TextView) itemView.findViewById(R.id.bussinessKey);
            activiName = (TextView) itemView.findViewById(R.id.activiName);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, toDoProcessList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, ToDoProcess data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
