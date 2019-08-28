package com.sys.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sys.cache.ACache;
import com.sys.entity.ToDoProcess;
import com.sys.main.adapter.CollectRecycleAdapter;

import java.util.ArrayList;
import com.sys.R;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link CollectFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link CollectFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CollectFragment extends Fragment {
    private View view;//定义view用来设置fragment的layout
    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<ToDoProcess> toDoProcessList = new ArrayList<ToDoProcess>();
    //自定义recyclerveiw的适配器
    private CollectRecycleAdapter mCollectRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_collect, container, false);
        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();
        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
        for (int i=0;i<10;i++){
            ToDoProcess toDoProcess =new ToDoProcess();
            toDoProcess.setProcessId("模拟数据"+i);
            toDoProcess.setProcessStartTime("100"+i);
            toDoProcessList.add(toDoProcess);
        }
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.collect_recyclerView);
        //创建adapter
        mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), toDoProcessList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new CollectRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ToDoProcess data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(),"我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
