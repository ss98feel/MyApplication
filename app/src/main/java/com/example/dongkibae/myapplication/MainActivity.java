package com.example.dongkibae.myapplication;




import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomNavigationView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    android.support.v4.app.FragmentManager fragmentManager;
    Fragment fragment;
    RelativeLayout star, timeline, jjim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        star = (RelativeLayout) findViewById(R.id.star);
        timeline = (RelativeLayout) findViewById(R.id.timeline);
        jjim = (RelativeLayout) findViewById(R.id.jjim);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final Fragment[] arrFragments = new Fragment[3];
        arrFragments[0] = new Fragment1();
        arrFragments[1] = new Fragment2();
        arrFragments[2] = new Fragment3();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 40, 0);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(pagerAdapter);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        star.setOnClickListener(this);
        timeline.setOnClickListener(this);
        jjim.setOnClickListener(this);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Box:
                        fragment = arrFragments[0];
                        viewPager.setCurrentItem(0);

                        break;

                    case R.id.Movie:
                        fragment = arrFragments[1];
                        viewPager.setCurrentItem(1);

                        break;

                    case R.id.Actor:
                        fragment = arrFragments[2];
                        viewPager.setCurrentItem(2);

                        break;
                }
                return false;
            }


        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.star:
                Intent intent = new Intent(MainActivity.this, Stargrade.class);
                intent.putExtra("stargrade", "StarGrade" );
                startActivity(intent);

                break;
            case R.id.timeline:
                Intent intent1 = new Intent(MainActivity.this, Timeline.class);
                intent1.putExtra("time", "TimeLine" );
                startActivity(intent1);
                break;
            case R.id.jjim:
                Intent intent2 = new Intent(MainActivity.this, Jjimitem.class);
                intent2.putExtra("jjimitem", "jjimitem" );
                startActivity(intent2);
                break;
            case R.id.save:

                break;

        }
    }
}

