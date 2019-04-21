package com.example.week020420.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.week020420.R;
import com.example.week020420.view.fragment.OneFragment;
import com.example.week020420.view.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttomBar)
    BottomTabBar buttomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttomBar.init(getSupportFragmentManager())
                .setFontSize(10)
                .setImgSize(50,50)
                .setTabPadding(4,6,10)
                .addTabItem("首页",R.mipmap.ic_launcher_round,OneFragment.class)
                .addTabItem("足迹",R.mipmap.ic_launcher_round,TwoFragment.class);
    }

}
