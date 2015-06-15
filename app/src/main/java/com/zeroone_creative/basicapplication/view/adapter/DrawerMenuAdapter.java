package com.zeroone_creative.basicapplication.view.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeroone_creative.basicapplication.R;
import com.zeroone_creative.basicapplication.model.enumerate.DrawerMenu;

public class DrawerMenuAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;

    public DrawerMenuAdapter(final Context context) {
        super();
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DrawerMenu.values().length;
    }

    @Override
    public DrawerMenu getItem(int position) {
        return DrawerMenu.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_drawer_menu, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DrawerMenu drawerMenu = getItem(position);
        viewHolder.iconImageView.setImageResource(drawerMenu.imageId);
        viewHolder.nameTextView.setText(drawerMenu.nameId);
        return convertView;
    }

    private class ViewHolder {
        TextView nameTextView;
        ImageView iconImageView;

        private ViewHolder(View view) {
            nameTextView = (TextView) view.findViewById(R.id.item_drawer_textview_name);
            iconImageView = (ImageView) view.findViewById(R.id.item_drawer_imageview_icon);
        }
    }

}
