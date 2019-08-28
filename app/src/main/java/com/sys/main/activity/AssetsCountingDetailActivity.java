package com.sys.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sys.R;
import com.sys.bean.AssetDetail;
import com.sys.bean.AssetsArea;
import com.sys.bean.AssetsAreaAddr;
import com.sys.bean.AssetsCountedAreaAddr;
import com.sys.bean.CountingTask;
import com.sys.bean.DetailCountView;
import com.sys.bean.PageUtils;
import com.sys.bean.SpinnerItem;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.main.adapter.CountingTaskDetailAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class AssetsCountingDetailActivity extends Activity {

    private View taskInfoView;
    private ListView taskListView;
    private TextView mIn;
    private TextView mFinish;
    private String countingState = "0";
    private List<AssetDetail> list;
    private int total;
    private CountingTask countingTask;
    private LinearLayout type1;
    private LinearLayout type2;
    private LinearLayout type3;
    private LinearLayout type4;
    private ACache mACache;

    private String countedAreaId;
    private String countedAddrId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
        setContentView(R.layout.activity_assets_counting_detail);
        TextView headerTitle = findViewById(R.id.header_title);
        headerTitle.setText("盘点详情-资产低耗");

        Intent intent = getIntent();
        countingTask = (CountingTask) intent.getSerializableExtra("counting_task");

        TextView countingTaskTitle = findViewById(R.id.counting_task_title);
        countingTaskTitle.setText(countingTask.getCountingTaskTime());

        mACache = ACache.get(this);

        taskListView = findViewById(R.id.asset_list_view);
        taskInfoView = this.getLayoutInflater().inflate(R.layout.counting_task_info, taskListView, false);
        TextView countingCreatorValue = taskInfoView.findViewById(R.id.counting_creator_value);
        TextView countingCreateTime = taskInfoView.findViewById(R.id.counting_create_time);
        TextView countingFinishTime = taskInfoView.findViewById(R.id.counting_finish_time);
        countingCreatorValue.setText(countingTask.getCountingTaskCreaterName() + "(" + countingTask.getCountingTaskCreaterId() + ")");
        countingCreateTime.setText("创建时间：" + format(countingTask.getCountingTaskTime()));
        if (countingTask.getCountingTaskState().equals("3")) {
            countingFinishTime.setText("完成时间：" + countingTask.getCountingTaskEditTime());
            LinearLayout foot = findViewById(R.id.foot);
            foot.setVisibility(View.GONE);
        } else {
            countingFinishTime.setVisibility(View.GONE);
        }

        taskListView.addHeaderView(taskInfoView);
        getAssetList();

        getDetailsCount();
        type1 = taskInfoView.findViewById(R.id.type_1);
        type1.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
        type1.setOnClickListener(mListener);
        type2 = taskInfoView.findViewById(R.id.type_2);
        type2.setOnClickListener(mListener);
        type3 = taskInfoView.findViewById(R.id.type_3);
        type3.setOnClickListener(mListener);
        type4 = taskInfoView.findViewById(R.id.type_4);
        type4.setOnClickListener(mListener);

        ZXingLibrary.initDisplayOpinion(this);
        isLogin();
        getCountedAreaAddr();
    }

    public void isLogin() {
        String url = RequestType.BASE_URL + "/user/isLogin";
        OkHttpUtils.get()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        if (json.equals("false")) {
                            mACache.remove("Cookie");
                            Toast.makeText(AssetsCountingDetailActivity.this, R.string.login_out_of_time, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AssetsCountingDetailActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    public void getCountedAreaAddr() {
        String url = RequestType.BASE_URL + "/assets/countedAreaAddr/get";
        OkHttpUtils.get()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        System.out.println(json);
                        if (json != null && !json.equals("")) {
                            AssetsCountedAreaAddr countedAreaAddr = new Gson().fromJson(json, AssetsCountedAreaAddr.class);
                            countedAreaId = countedAreaAddr.getAssetAreaId();
                            countedAddrId = countedAreaAddr.getAssetAddrId();
                            getAddrList(countedAddrId);
                        }
                        getAreaList();
                    }
                });
    }

    public void getAreaList() {
        String url = RequestType.BASE_URL + "/assets/area/getAreaList";
        OkHttpUtils.get()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        Type type = new TypeToken<List<AssetsArea>>() {
                        }.getType();
                        List<AssetsArea> areaList = new Gson().fromJson(json, type);

                        List<SpinnerItem> list = new ArrayList<>();
                        Integer position = null;
                        for (int i = 0; i < areaList.size(); i++) {
                            AssetsArea area = areaList.get(i);
                            if (countedAreaId != null) {
                                if (area.getAssetAreaId().equals(countedAreaId)) {
                                    position = i;
                                }
                            }
                            SpinnerItem spinnerItem = new SpinnerItem(area.getAssetAreaId(), area.getAssetAreaTitle());
                            list.add(spinnerItem);
                        }
                        ArrayAdapter<SpinnerItem> adapter = new ArrayAdapter<>(AssetsCountingDetailActivity.this, R.layout.simple_spinner_item, list);
                        Spinner countingAreaValue = findViewById(R.id.counting_area_value);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        countingAreaValue.setAdapter(adapter);
                        if (position != null) {
                            countingAreaValue.setSelection(position, true);
                        }
                        countingAreaValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int pos, long id) {
                                Spinner sp = findViewById(R.id.counting_area_value);
                                countedAreaId = ((SpinnerItem) sp.getSelectedItem()).getKey();
                                getAddrList(countedAreaId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                });
    }

    public void getAddrList(String assetAreaId) {
        String url = RequestType.BASE_URL + "/assets/area/getAddrList";
        OkHttpUtils.post()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .url(url)
                .addParams("assetAreaId", assetAreaId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        Type type = new TypeToken<List<AssetsAreaAddr>>() {
                        }.getType();
                        List<AssetsAreaAddr> addrList = new Gson().fromJson(json, type);

                        List<SpinnerItem> list = new ArrayList<>();
                        Integer position = null;
                        for (int i = 0; i < addrList.size(); i++) {
                            AssetsAreaAddr addr = addrList.get(i);
                            if (countedAddrId != null) {
                                if (addr.getAssetAddrId().equals(countedAddrId)) {
                                    position = i;
                                }
                            }
                            SpinnerItem spinnerItem = new SpinnerItem(addr.getAssetAddrId(), addr.getAssetAreaAddr());
                            list.add(spinnerItem);
                        }
                        ArrayAdapter<SpinnerItem> adapter = new ArrayAdapter<>(AssetsCountingDetailActivity.this, R.layout.simple_spinner_item, list);
                        Spinner countingAddrValue = findViewById(R.id.counting_addr_value);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        countingAddrValue.setAdapter(adapter);
                        if (position != null) {
                            countingAddrValue.setSelection(position, true);
                        }
                        countingAddrValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int pos, long id) {
                                Spinner sp = findViewById(R.id.counting_addr_value);
                                countedAddrId = ((SpinnerItem) sp.getSelectedItem()).getKey();
                                System.out.println(countedAddrId);
                                insertCountedAreaAddr(countedAreaId, countedAddrId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                });
    }

    public void insertCountedAreaAddr(final String countedAreaId, final String countedAddrId) {
        String url = RequestType.BASE_URL + "/assets/countedAreaAddr/insert";
        AssetsCountedAreaAddr assetsCountedAreaAddr = new AssetsCountedAreaAddr();
        assetsCountedAreaAddr.setAssetAreaId(countedAreaId);
        assetsCountedAreaAddr.setAssetAddrId(countedAddrId);
        OkHttpUtils.postString()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .url(url)
                .content(new Gson().toJson(assetsCountedAreaAddr))
                .mediaType(RequestType.MEDIA_TYPE_JSON)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        System.out.println(json);
                    }
                });
    }

    public void getDetailsCount() {
        String url = RequestType.BASE_URL + "/assets/countingTask/getDetailsCount/" + countingTask.getCountingTaskId();
        OkHttpUtils.post()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .id(countingTask.getCountingTaskId())
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        DetailCountView detailCountView = new Gson().fromJson(json, DetailCountView.class);
                        TextView countingTotalValue = taskInfoView.findViewById(R.id.counting_total_value);
                        TextView countingType1Value = taskInfoView.findViewById(R.id.counting_type_1_value);
                        TextView countingType2Value = taskInfoView.findViewById(R.id.counting_type_2_value);
                        TextView countingType3Value = taskInfoView.findViewById(R.id.counting_type_3_value);
                        TextView countingType4Value = taskInfoView.findViewById(R.id.counting_type_4_value);
                        countingTotalValue.setText(detailCountView.getTotalQuantity());
                        countingType1Value.setText(detailCountView.getUncountedQuantity());
                        countingType2Value.setText(detailCountView.getCountedQuantity());
                        countingType3Value.setText(detailCountView.getCountedQuantityOutOfPosition());
                        countingType4Value.setText(detailCountView.getCountedQuantityByOther());
                    }
                });
    }

    private String format(String time) {
        return time.substring(0, 4) + '-' + time.substring(4, 6) + '-' + time.substring(6, 8) + ' ' + time.substring(8, 10) + ':' + time.substring(10, 12) + ':' + time.substring(12, 14);
    }

    private void getAssetList() {
        String url = RequestType.BASE_URL + "/assets/countingTask/getMyDetailsList/" + countingTask.getCountingTaskId();
        OkHttpUtils.post()
                .addHeader("Cookie", mACache.getAsString("Cookie"))
                .id(countingTask.getCountingTaskId())
                .url(url)
                .addParams("pageNum", "1")
                .addParams("limit", "10")
                .addParams("countingState", countingState)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        list = new ArrayList<>();
                        Type type = new TypeToken<PageUtils<AssetDetail>>() {
                        }.getType();
                        PageUtils<AssetDetail> pageUtils = new Gson().fromJson(json, type);
                        if (pageUtils != null) {
                            list = pageUtils.getRows();
                            total = pageUtils.getTotal();
                        }
                        handler.sendMessage(handler.obtainMessage(R.layout.asset_item, list));
                    }
                });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            taskListView.setAdapter(new CountingTaskDetailAdapter(AssetsCountingDetailActivity.this, msg.what, (List<AssetDetail>) msg.obj));
        }
    };

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.type_1:
                    countingState = "0";
                    type1 = taskInfoView.findViewById(R.id.type_1);
                    type1.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    type2 = taskInfoView.findViewById(R.id.type_2);
                    type2.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type3 = taskInfoView.findViewById(R.id.type_3);
                    type3.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type4 = taskInfoView.findViewById(R.id.type_4);
                    type4.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    getAssetList();
                    break;
                case R.id.type_2:
                    countingState = "1";
                    type1 = taskInfoView.findViewById(R.id.type_1);
                    type1.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type2 = taskInfoView.findViewById(R.id.type_2);
                    type2.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    type3 = taskInfoView.findViewById(R.id.type_3);
                    type3.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type4 = taskInfoView.findViewById(R.id.type_4);
                    type4.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    getAssetList();
                    break;
                case R.id.type_3:
                    countingState = "2";
                    type1 = taskInfoView.findViewById(R.id.type_1);
                    type1.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type2 = taskInfoView.findViewById(R.id.type_2);
                    type2.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type3 = taskInfoView.findViewById(R.id.type_3);
                    type3.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    type4 = taskInfoView.findViewById(R.id.type_4);
                    type4.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    getAssetList();
                    break;
                case R.id.type_4:
                    countingState = "3";
                    type1 = taskInfoView.findViewById(R.id.type_1);
                    type1.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type2 = taskInfoView.findViewById(R.id.type_2);
                    type2.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type3 = taskInfoView.findViewById(R.id.type_3);
                    type3.setBackground(getDrawable(R.drawable.line_bottom_gray_2dp));
                    type4 = taskInfoView.findViewById(R.id.type_4);
                    type4.setBackground(getDrawable(R.drawable.line_bottom_blue_2dp));
                    getAssetList();
                    break;
            }
        }
    };
}
