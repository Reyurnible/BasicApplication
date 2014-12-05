package com.zeroone_creative.basicapplication.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.fragment.MainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @AfterViews
    public void afterViews(){
        setFragment();
    }

    private void setFragment(){
        Fragment fragment = MainFragment_.builder().build();
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
