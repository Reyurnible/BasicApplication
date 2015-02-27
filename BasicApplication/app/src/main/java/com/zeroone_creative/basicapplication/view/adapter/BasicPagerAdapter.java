package com.zeroone_creative.basicapplication.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shunhosaka on 2015/02/27.
 */
public class BasicPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mContentsFragments;

    public BasicPagerAdapter(FragmentManager fm) {
        super(fm);
        mContentsFragments = new ArrayList<>();
    }

    public void setPages(List<Fragment> contetsFragment) {
        this.mContentsFragments = contetsFragment;
    }

    public void addPages(Fragment fragment) {
        this.mContentsFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mContentsFragments.get(position);
    }

    @Override
    public int getCount() {
        return mContentsFragments.size();
    }
}
