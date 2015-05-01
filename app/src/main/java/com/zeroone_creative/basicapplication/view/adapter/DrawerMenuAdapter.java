package com.zeroone_creative.basicapplication.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeroone_creative.basicapplication.model.enumerate.DrawerMenu;

/**
 * Created by shunhosaka on 2015/03/21.
 * This Adapter is　contnet of {@link com.zeroone_creative.basicapplication.view.activity.NavigationDrawerFragment}.
 * This contnets is {@link com.zeroone_creative.basicapplication.model.enumerate.DrawerMenu}
 */
public class DrawerMenuAdapter extends BaseAdapter {

    public DrawerMenuAdapter() {

    }

    @Override
    public int getCount() {
        return DrawerMenu.values().length;
    }

    @Override
    public Object getItem(int position) {
        return DrawerMenu.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return null;
    }

    private class ViewHolder {
        TextView nameTextView;
        ImageView iconImageView;

        private ViewHolder(View rootView) {

        }
    }

}
