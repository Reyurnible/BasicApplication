package com.zeroone_creative.basicapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeroone_creative.basicapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView用のAdapter
 * Created by shunhosaka on 2015/02/27.
 */
public class BasicHorizontalRecyclerAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflator;
    private List<String> mContents = new ArrayList<>();

    /**
     * コンストラクタ
     * @param context
     */
    public BasicHorizontalRecyclerAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
    }

    /**
     * データのセット
     * @param contents
     */
    public void setContent(List<String> contents) {
        this.mContents = contents;
    }

    // Viewを生成
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflator.inflate(R.layout.item_basic_horizontal_recycler_adapter, viewGroup, false));
    }

    // Viewにデータを設定する
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((ViewHolder) viewHolder).mTextView.setText(mContents.get(position));
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    // ViewHolder内部でIDと関連づけ
    private static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_horizontal_recycler_textview);
        }
    }

}
