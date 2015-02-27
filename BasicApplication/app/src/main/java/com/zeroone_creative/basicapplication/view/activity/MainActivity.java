package com.zeroone_creative.basicapplication.view.activity;

import android.app.Activity;
import android.app.Fragment;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.fragment.MainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @AfterViews
    public void onAfterViews() {
        setFragment();
    }

    private void setFragment() {
        Fragment fragment = MainFragment_.builder().build();
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

}
