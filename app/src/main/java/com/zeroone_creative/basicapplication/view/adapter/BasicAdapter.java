package com.zeroone_creative.basicapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zeroone_creative.basicapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shunhosaka on 2014/12/04.
 */
public class BasicAdapter extends BaseAdapter {

    private List<String> mContent = new ArrayList<String>();
    private LayoutInflater mInflater;

    public BasicAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public Object getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_basic_adapter, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.basicadapter_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mContent.get(position));

        return convertView;
    }

    public void setContent(List<String> strList) {
        this.mContent = strList;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView textView;
    }

}
