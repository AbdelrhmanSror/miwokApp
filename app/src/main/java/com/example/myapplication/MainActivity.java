package com.example.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mViewPager=findViewById(R.id.viewpager);
        pagerAdpater mPagerAdapter=new pagerAdpater(getSupportFragmentManager(),getApplicationContext());
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabs=findViewById(R.id.tabs);
        int normal=ContextCompat.getColor(getApplicationContext(),R.color.Black);
        tabs.setTabTextColors(ContextCompat.getColor(getApplicationContext(),R.color.tan_background),ContextCompat.getColor(getApplicationContext(),R.color.tan_background));
        tabs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.primary_color));
        tabs.setupWithViewPager(mViewPager);
    }

}
