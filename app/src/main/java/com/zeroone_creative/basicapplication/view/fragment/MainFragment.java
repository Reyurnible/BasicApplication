package com.zeroone_creative.basicapplication.view.fragment;

import android.app.Fragment;
import android.content.Intent;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.activity.PagerActivity_;
import com.zeroone_creative.basicapplication.view.activity.RecyclerActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @AfterViews
    void onAfterViews() {
    }

    @Click(R.id.main_button_pager)
    void movePager() {
        Intent intent = new Intent(getActivity().getApplicationContext(), PagerActivity_.class);
        startActivity(intent);
}

    @Click(R.id.main_button_recycler)
    void moveRecycler() {
        Intent intent = new Intent(getActivity().getApplicationContext(), RecyclerActivity_.class);
        startActivity(intent);
    }

}

