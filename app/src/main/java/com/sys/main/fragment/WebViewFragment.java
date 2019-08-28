package com.sys.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sys.MainActivity;
import com.sys.R;
import com.sys.bean.CountingTask;
import com.sys.bean.PageUtils;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.main.activity.AssetsCountingActivity;
import com.sys.main.activity.LoginActivity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class WebViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ACache mACache;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(String param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_web_view, container, false);
        WebView webview=view.findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        CookieSyncManager.createInstance(view.getContext());
        CookieManager.getInstance();
        CookieManager cookieMgr = CookieManager.getInstance();
        cookieMgr.setAcceptCookie(true);
        String cookie="DIV23Shiro=63e3b44b-1ce2-44f1-a45a-1cc788ea6719";
        String url=RequestType.BASE_URL+getArguments().getString(ARG_PARAM1);
        Log.e("url::::", url );
        cookieMgr.setCookie("http://10.17.191.55/process/toDoList",cookie);// this place should add the login host address(not the login index address)
        CookieSyncManager.getInstance().sync();
        webview.loadUrl(url);// login index address
        System.out.println("kaishi：：：：：：：：" );
        //test2();
        mACache = ACache.get(view.getContext());
        return view;
    }
        public void test()  {
            RequestBody requestBody = new FormBody.Builder()
                    .add("pageNum", "1")
                    .add("limit", "10")
                    //.add("countingTaskState", countingTaskState)
                    .build();
            Request request = new Request.Builder()
                    .addHeader("Connection", "keep-alive")
                    //.addHeader("Cookie", mACache.getAsString("Cookie"))
                    .addHeader("Cookie", "DIV23Shiro=62045587-c39f-46a8-b86b-8885476af9b6")
                    // .url(RequestType.BASE_URL + "/process/listMyTask")
                    .url(RequestType.BASE_URL + "/assets/countingTask/getMyList")
                    .post(requestBody)
                    .build();
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String json = body.string();
                        Type type = new TypeToken<PageUtils<CountingTask>>() {
                        }.getType();
                        PageUtils<CountingTask> pageUtils = new Gson().fromJson(json, type);
                        if (pageUtils != null) {
//                            list = pageUtils.getRows();
////                            total = pageUtils.getTotal();
                            System.out.println("结果：：：：：：：：" + pageUtils.getTotal());
                        }
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    private void test2() {
        new Thread() {
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
//                    Request request = new Request.Builder()
//                            .addHeader("Connection", "keep-alive")
//                            .addHeader("Cookie", mACache.getAsString("Cookie"))
//                            .url(RequestType.BASE_URL + "/user/isLogin")
//                            .build();
//                    Response response = okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        String json = response.body().string();
//                        if (json.equals("false")) {
//                            mACache.remove("Cookie");
//                            Looper.prepare();
//                            Toast.makeText(AssetsCountingActivity.this, R.string.login_out_of_time, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(AssetsCountingActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            Looper.loop();
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                list = new ArrayList<>();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("pageNum", "1")
                            .add("limit", "10")
                            //.add("countingTaskState", countingTaskState)
                            .build();
                    Request request = new Request.Builder()
                            .addHeader("Connection", "keep-alive")
                            //.addHeader("Cookie", mACache.getAsString("Cookie"))
                            .addHeader("Cookie", "DIV23Shiro=62045587-c39f-46a8-b86b-8885476af9b6")
                            .url(RequestType.BASE_URL + "/process/listMyTask")
                            .post(requestBody)
                            .build();
//                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            String json = body.string();
                            Type type = new TypeToken<PageUtils<CountingTask>>() {
                            }.getType();
                            PageUtils<CountingTask> pageUtils = new Gson().fromJson(json, type);
                            if (pageUtils != null) {
                                System.out.println("结果：：：：：：：：" + pageUtils.getTotal());
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                if (countingTaskState.equals("2")) {
//                    handler.sendMessage(handler.obtainMessage(R.layout.counting_task_in_item, list));
//                } else {
//                    handler.sendMessage(handler.obtainMessage(R.layout.counting_task_finish_item, list));
//                }
//            }
            }
        }.start();
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
