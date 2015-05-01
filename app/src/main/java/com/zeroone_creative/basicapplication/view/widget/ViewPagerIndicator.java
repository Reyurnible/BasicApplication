package com.zeroone_creative.basicapplication.view.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zeroone_creative.basicapplication.R;


public class ViewPagerIndicator extends RadioGroup {

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    private int mCount;

    /*
     * ページ数をセットする
     */
    public void setCount(int count) {
        mCount = count;
        removeAllViews();
        //指定されたページ数分だけRadioButtonを追加
        for (int i = 0; i < count; i++) {
            //RadioButtonにインディケータの画像をセット
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setButtonDrawable(R.drawable.ic_indicator);
            radioButton.setAlpha(0.7f);
            addView(radioButton);
        }
        setCurrentPosition(-1);

        //２つ以下は見えないようにする
        if (mCount < 2) {
            this.setVisibility(View.INVISIBLE);
        } else {
            this.setVisibility(View.VISIBLE);
        }
    }

    /*
     * 現在の位置をセットする
     */
    public void setCurrentPosition(int position) {
        if (position >= mCount) {
            position = mCount - 1;
        }
        if (position < 0) {
            position = mCount > 0 ? 0 : -1;
        }
        if (position >= 0 && position < mCount) {
            //現在の位置のRadioButtonをチェック状態にする
            RadioButton rb = (RadioButton) getChildAt(position);
            rb.setChecked(true);
        }
    }
}
