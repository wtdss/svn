package com.sys.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sys.MainActivity;
import com.sys.R;
import com.sys.bean.LoginInfo;
import com.sys.bean.Result;
import com.sys.cache.ACache;
import com.sys.constant.RequestType;
import com.sys.loader.GlideImageLoader;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {

    private EditText mAccount;
    private EditText mPassword;
    private CheckBox mPasswordRemember;
    private Button mLoginButton;
    private ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mACache = ACache.get(this);
        if (mACache.getAsString("Cookie") != null) {
            Toast.makeText(LoginActivity.this, R.string.login_already, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.login_banner1);
        images.add(R.drawable.login_banner2);
        images.add(R.drawable.login_banner3);
        images.add(R.drawable.login_banner4);
        images.add(R.drawable.login_banner5);

        Banner banner = findViewById(R.id.login_banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();

        mAccount = findViewById(R.id.account);
        mPassword = findViewById(R.id.password);
        mPasswordRemember = findViewById(R.id.password_remember);


        LoginInfo loginInfo = (LoginInfo) mACache.getAsObject("loginInfo");
        if (loginInfo != null) {
            if (loginInfo.getPasswordRemember()) {
                mAccount.setText(loginInfo.getAccount());
                mPassword.setText(loginInfo.getPassword());
                mPasswordRemember.setChecked(true);
            }
        }

        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_button:
                    login();
                    break;
            }
        }
    };

    public void login() {
        if (isAccountAndPasswordValid()) {
            new Thread() {
                public void run() {
                    String account = mAccount.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    OkHttpClient okHttpClient = new OkHttpClient();
                    try {
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("username", account)
                                .addFormDataPart("password", password)
                                .build();
                        Request request = new Request.Builder()
                                .url(RequestType.BASE_URL + "/login")
                                .post(requestBody)
                                .build();
                        Response response = okHttpClient.newCall(request).execute();
                        if (response.isSuccessful()) {
                            String json = response.body().string();
                            Result r = new Gson().fromJson(json, Result.class);
                            int code = r.getCode();
                            if (code == 0) {
                                List<String> cookies = response.headers().values("Set-Cookie");
                                for (String cookie : cookies) {
                                    if (cookie.contains("DIV23Shiro")) {
                                        System.out.println(cookie);
                                        String[] strings = cookie.split(";");
                                        for (String string : strings) {
                                            if (string.contains("DIV23Shiro")) {
                                                mACache.put("Cookie", string);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }

                                if (mPasswordRemember.isChecked()) {
                                    LoginInfo loginInfo = new LoginInfo();
                                    loginInfo.setAccount(account);
                                    loginInfo.setPassword(password);
                                    loginInfo.setPasswordRemember(true);
                                    mACache.put("loginInfo", loginInfo);
                                } else {
                                    mACache.remove("loginInfo");
                                }

                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, r.getMsg(), Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public boolean isAccountAndPasswordValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.password_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}