package com.sys.main.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sys.R;
import com.sys.bean.MultipleItem;
import com.sys.main.activity.LoginActivity;
import com.sys.main.activity.SignUpActivity;
import com.sys.main.adapter.DiscoveryMultiItemQuickAdapter;
import com.sys.util.CheckPermissionUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private RecyclerView mRecyclerView;
    private List<MultipleItem> mMultipleItemList;
    private DiscoveryMultiItemQuickAdapter mDiscoveryMultiItemQuickAdapter;

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    public Button button1 = null;
    public Button button2 = null;
    public Button button3 = null;
    public Button button4 = null;
    public Button button5 = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery, null, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSwipeRefreshLayout = getActivity().findViewById(R.id.fragment_discovery);
//        mRecyclerView = getActivity().findViewById(R.id.discoveryView);
        initSwipeRefreshLayout();
        initMultipleItemList();
        initQuickAdapter();
        initListener();

        initView();

        button5 = getActivity().findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(getContext());
                new AlertDialog.Builder(getContext()).setTitle("请输入消息")
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                System.out.println(et.getText());
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });
    }

    /**
     * 初始化组件
     */
    private void initView() {
        button1 = (Button) getActivity().findViewById(R.id.button1);
        button2 = (Button) getActivity().findViewById(R.id.button2);
        button3 = (Button) getActivity().findViewById(R.id.button3);
        button4 = (Button) getActivity().findViewById(R.id.button4);
        /**
         * 打开默认二维码扫描界面
         *
         * 打开系统图片选择界面
         *
         * 定制化显示扫描界面
         *
         * 测试生成二维码图片
         */
        button1.setOnClickListener(new ButtonOnClickListener(button1.getId()));
        button2.setOnClickListener(new ButtonOnClickListener(button2.getId()));
        button3.setOnClickListener(new ButtonOnClickListener(button3.getId()));
        button4.setOnClickListener(new ButtonOnClickListener(button4.getId()));
    }

    /**
     * 按钮点击事件处理逻辑
     * @param buttonId
     */
    private void onClick(int buttonId) {
        switch (buttonId) {
            case R.id.button1:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.button3:
                intent = new Intent(getContext(), SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(getContext());
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(getContext(), Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onClick(viewId);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(getContext(), "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getContext(), "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
    }

    /**
     * 按钮点击监听
     */
    class ButtonOnClickListener implements View.OnClickListener{

        private int buttonId;

        public ButtonOnClickListener(int buttonId) {
            this.buttonId = buttonId;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button2) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            } else if (v.getId() == R.id.button4) {
                Intent intent = new Intent(getContext(), ThreeActivity.class);
                startActivity(intent);
            } else {
                cameraTask(buttonId);
            }
        }
    }

    private void initMultipleItemList() {
        mMultipleItemList = new ArrayList<>();
        MultipleItem multipleItem = new MultipleItem(MultipleItem.TYPE_HEADER, 3);
        mMultipleItemList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_LOGIN, 3);
        mMultipleItemList.add(multipleItem);
    }

    private void initQuickAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
//        mRecyclerView.setLayoutManager(gridLayoutManager);
        mDiscoveryMultiItemQuickAdapter = new DiscoveryMultiItemQuickAdapter(mMultipleItemList);
//        mRecyclerView.setAdapter(mDiscoveryMultiItemQuickAdapter);
    }

    private void initListener() {
        mDiscoveryMultiItemQuickAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mMultipleItemList.get(position).getSpans();
            }
        });

        mDiscoveryMultiItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println("第  " + position);
                if (mMultipleItemList.get(position).getItemType() == MultipleItem.TYPE_MY_TOOLS) {
                    System.out.println("更新功能");
                }
            }
        });

        mDiscoveryMultiItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView login = view.findViewById(R.id.login);
                if (login != null) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                TextView signUp = view.findViewById(R.id.signUp);
                if (signUp != null) {
                    Intent intent = new Intent(getActivity(), SignUpActivity.class);
                    startActivity(intent);
                }
                System.out.println("第  " + position);
                if (mMultipleItemList.get(position).getItemType() == MultipleItem.TYPE_LOGIN) {
                    System.out.println("更新功能");
                }
            }
        });
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDiscoveryMultiItemQuickAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                        System.out.println("刷新完成");
                    }
                }, 2000);
            }
        });
    }
}
