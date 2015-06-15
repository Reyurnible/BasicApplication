package com.zeroone_creative.basicapplication.view.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;

import com.zeroone_creative.basicapplication.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_drawer)
public class DrawerActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @FragmentById(R.id.navigation_drawer)
    NavigationDrawerFragment mNavigationDrawerFragment;
    @ViewById(R.id.drawer_layout_container)
    FrameLayout mContainerLayout;

    @AfterViews
    void onAfterViews() {
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //TODO Set Fragment

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
    }
}
