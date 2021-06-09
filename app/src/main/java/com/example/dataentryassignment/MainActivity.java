package com.example.dataentryassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dataentryassignment.adaptor.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdaptor viewPagerAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    public void init(){
        tabLayout =findViewById(R.id.tab_layout);
        viewPager= findViewById(R.id.view_pager);
        viewPagerAdaptor= new ViewPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);
    }
}