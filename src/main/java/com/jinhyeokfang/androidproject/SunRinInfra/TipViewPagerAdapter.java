package com.jinhyeokfang.androidproject.SunRinInfra;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TipViewPagerAdapter extends FragmentPagerAdapter {
    public TipViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new com.jinhyeokfang.androidproject.SunRinInfra.TipPageItemFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
