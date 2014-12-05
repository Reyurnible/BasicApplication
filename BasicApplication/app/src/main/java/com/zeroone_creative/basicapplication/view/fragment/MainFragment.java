package com.zeroone_creative.basicapplication.view.fragment;

import android.app.Fragment;
import android.content.Intent;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.activity.PagerActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @Click(R.id.main_rippleview_rect)
    void moveDrawer() {
        Intent intent = new Intent(getActivity().getApplicationContext(), PagerActivity_.class);
        startActivity(intent);
    }

}
