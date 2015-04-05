package com.zeroone_creative.basicapplication.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.fragment.LocationManagerFragment;
import com.zeroone_creative.basicapplication.view.fragment.MainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements LocationManagerFragment.OnUpdateLocationListener {

    @AfterViews
    public void onAfterViews() {
        setFragment();
        getFragmentManager().beginTransaction().add(LocationManagerFragment.newInstance(1000), "LocationManagerFragment").commit();
    }

    private void setFragment() {
        Fragment fragment = MainFragment_.builder().build();
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    @Override
    public void onUpdateLocation(Location location) {
        Log.d(this.getClass().getSimpleName(), location.toString());
    }
}
