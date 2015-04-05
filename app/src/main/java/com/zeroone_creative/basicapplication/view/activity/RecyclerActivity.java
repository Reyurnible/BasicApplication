package com.zeroone_creative.basicapplication.view.activity;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.view.adapter.BasicHorizontalRecyclerAdapter;
import com.zeroone_creative.basicapplication.view.adapter.HorizontalCardLayoutManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_recycler)
public class RecyclerActivity extends Activity {

    @ViewById(R.id.recycler_recyclerview)
    RecyclerView mRecyclerView;

    @AfterViews
    void onAfterViews() {

        StaggeredGridLayoutManager managerStaggered = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        BasicHorizontalRecyclerAdapter adapter = new BasicHorizontalRecyclerAdapter(getApplicationContext());
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contents.add("Position" + String.valueOf(i));
        }
        adapter.setContent(contents);
        mRecyclerView.setLayoutManager(managerStaggered);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_change_horizontal_card:
                HorizontalCardLayoutManager managerHorizontalCard = new HorizontalCardLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(managerHorizontalCard);
                break;
            case R.id.action_change_recycler:
                StaggeredGridLayoutManager managerStaggered = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(managerStaggered);
                break;
            case R.id.action_change_vertical:
                LinearLayoutManager managerVertical = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
                mRecyclerView.setLayoutManager(managerVertical);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
