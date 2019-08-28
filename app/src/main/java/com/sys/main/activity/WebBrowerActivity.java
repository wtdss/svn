package com.sys.main.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.sys.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LIN on 2018/8/1.
 * web浏览器
 */

public class WebBrowerActivity extends AppCompatActivity {

    WebView webView;
    //private String url="http://10.17.191.55/wms/manage/wwApplyDetailM";
   //private String url="http://10.17.191.55/wms/manage/wwApplyDetailM?taskId=1217666&businessKey=wmsWw101:4&nodeName=%E8%BE%93%E5%8D%95%E5%91%98";
    //private String url="http://10.17.191.55/wms/manage/wwApplyDetailM?taskId=1217666&businessKey=wmsWw101:4&nodeName=输单员";
    private String url="http://10.17.191.55/process/toDoList";
    private CookieManager mCookieManager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web_brower);
        setTitle("加载中...");

        webView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        //settings.setPluginState(true);//支持插件
        settings.setUseWideViewPort(false);//将图片调整到适合webview的大小
        settings.setSupportZoom(true);//支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        settings.supportMultipleWindows();//多窗口
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        settings.setBuiltInZoomControls(true); //设置支持缩放
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setLoadsImagesAutomatically(true);//支持自动加载图片
        final String cookie="DIV23Shiro=17527be7-917e-4ff1-8d4c-3ad6c631dfe1";
       // webView.setWebViewClient(new WebViewClient());

        syncCookie2(url,cookie);
//        Map<String,String> map=new HashMap<>();
//        map.put("taskId","1217666");
//        map.put("businessKey","wmsWw101:4");
//        map.put("nodeName","输单员");
//        webView.loadUrl(url,map);


        //test1();
        //webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //这里一定要是url不能是网页
                CookieManager cookieManager = CookieManager.getInstance();
                String cookielj = cookieManager.getCookie(url);
                System.out.println("cookie1:::::::::"+cookielj);
                System.out.println("url1:::::::::"+url);
                syncCookie2(url,cookie);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                CookieManager cookieManager = CookieManager.getInstance();
                String cookielj = cookieManager.getCookie(url);
                System.out.println("url2:::::::::"+url);
                System.out.println("cookie2:::::::::"+cookielj);
            }
            //证书的设置支持所有的证书

//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
//            }
        });
        webView.loadUrl(url);
    }

    /**
     * 将cookie同步到WebView
     * @param url WebView要加载的url
     * @param cookie 要同步的cookie
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */
    public  boolean syncCookie2(String url,String cookie) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //CookieSyncManager.createInstance();
            CookieSyncManager.createInstance(this);//创建一个cookie管理器
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.setCookie(url, cookie);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
        String newCookie = cookieManager.getCookie(url);
        System.out.println("newCookie::::::::"+newCookie);
        return TextUtils.isEmpty(newCookie) ? false : true;
    }
    void test1(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance();
        CookieManager cookieMgr = CookieManager.getInstance();
        cookieMgr.setAcceptCookie(true);
        cookieMgr.setCookie("http://10.17.191.55/process/toDoList", "DIV23Shiro=62045587-c39f-46a8-b86b-8885476af9b6");// this place should add the login host address(not the login index address)
       //:http://10.17.191.55/process/materielApplyTask
        cookieMgr.setCookie("http://10.17.191.55/process/materielApplyTask", "DIV23Shiro=62045587-c39f-46a8-b86b-8885476af9b6");
        CookieSyncManager.getInstance().sync();
        webView.loadUrl("http://10.17.191.55/process/toDoList");// login index address
    }


    private void syncCookie(String url, String cookie) {
        try {
            CookieSyncManager.createInstance(this);//创建一个cookie管理器
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            //cookieManager.removeSessionCookie();// 移除以前的cookie
            //cookieManager.removeAllCookie();
            StringBuilder sbCookie = new StringBuilder();//创建一个拼接cookie的容器,为什么这么拼接，大家查阅一下http头Cookie的结构
            sbCookie.append(cookie);
            //这里的两个key很重要，如果不知道是什么可以抓包得到
            sbCookie.append(String.format(";domain=%s", "."));
            sbCookie.append(String.format(";path=%s", "/"));
            String cookieValue = sbCookie.toString();
            cookieManager.setCookie(url, cookieValue);//为url设置cookie
            cookieManager.setCookie("http://10.17.191.55/process/materielApplyTask", "DIV23Shiro=62045587-c39f-46a8-b86b-8885476af9b6");
            CookieSyncManager.getInstance().sync();//同步cookie
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //防止退出的时候抛Zoom异常
    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
