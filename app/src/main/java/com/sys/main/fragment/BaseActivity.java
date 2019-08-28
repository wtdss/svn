package com.sys.main.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sys.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

}
