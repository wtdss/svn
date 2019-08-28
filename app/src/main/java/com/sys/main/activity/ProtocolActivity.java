package com.sys.main.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.sys.R;
import com.sys.cache.ACache;

public class ProtocolActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_protocol);

        TextView headerTitle = findViewById(R.id.header_title);
        headerTitle.setText("服务协议");

//        mViewPager = findViewById(R.id.assetsViewPager);
//        TextView  navigationMy = findViewById(R.id.navigation_my);
//        navigationMy.setGravity(Gravity.CENTER);
//        bottomNavigationView.setItemTextAppearanceActive(R.style.bottom_selected_text);
//        bottomNavigationView.setItemTextAppearanceInactive(R.style.bottom_normal_text);
    }
}
