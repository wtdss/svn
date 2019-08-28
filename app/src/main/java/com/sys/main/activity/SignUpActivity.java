package com.sys.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sys.R;
import com.sys.bean.LoginInfo;
import com.sys.bean.SignUpInfo;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends Activity {

    private EditText mAccount;
    private EditText mPassword;
    private CheckBox mAgreeProtocol;
    private TextView mProtocol;
    private Button mSignUpButton;
    private ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAccount = findViewById(R.id.account);
        mPassword = findViewById(R.id.password);
        mAgreeProtocol = findViewById(R.id.agree_protocol);

        mACache = ACache.get(this);

        mProtocol = findViewById(R.id.protocol);
        mProtocol.setOnClickListener(mListener);

        mSignUpButton = findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.protocol:
                    Intent intent = new Intent(SignUpActivity.this, ProtocolActivity.class);    //切换Login Activity至Register Activity
                    startActivity(intent);
                    break;
                case R.id.sign_up_button:
                    signUp();
                    break;
            }
        }
    };

    public void signUp() {
        if (!mAgreeProtocol.isChecked()) {
            Toast.makeText(this, getString(R.string.agree_protocol), Toast.LENGTH_SHORT).show();
        }

        if (isAccountAndPasswordValid()) {

            int result = 1;


            new Thread(){
                public void run(){
                    String account = mAccount.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    SignUpInfo signUpInfo = new SignUpInfo();
                    signUpInfo.setUserJobNo(account);
                    signUpInfo.setUserPassword(password);
                    String json = new Gson().toJson(signUpInfo);
                    
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(RequestType.MEDIA_TYPE_JSON, json);
                    Request request = new Request.Builder()
                            .url("http://172.30.18.85/api/signUp")
                            .post(requestBody)
                            .build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        if (response.isSuccessful()) {
                            System.out.println(response.body().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();



            if (result == 1) {
                //注册成功提示
                Toast.makeText(this, getString(R.string.sign_up_success), Toast.LENGTH_SHORT).show();
            } else if (result == 0) {
                //注册失败提示
                Toast.makeText(this, getString(R.string.sign_up_fail), Toast.LENGTH_SHORT).show();
            }
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
