package com.example.dongkibae.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dongkibae on 2017. 2. 16..
 */

public class MyPagerAdapter extends FragmentPagerAdapter{

    private Fragment[] arrFragments;

    public MyPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
        super(fm);
        this.arrFragments = arrFragments;
    }
    @Override
    public Fragment getItem(int position) {
        return arrFragments[position];
    }

    @Override
    public int getCount() {
        return arrFragments.length;
    }
}
