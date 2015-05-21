package com.zeroone_creative.basicapplication.view.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.Toast;

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

    @Click(R.id.main_button_dialog)
    void showDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(getActivity(),
                        String.valueOf(year) + "/" +
                                String.valueOf(monthOfYear + 1) + "/" +
                                String.valueOf(dayOfMonth),
                        Toast.LENGTH_SHORT).show();
            }
        },
        2015, 5, 4);
        datePickerDialog.show();
    }

}

