package com.zeroone_creative.basicapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by shunhosaka on 2015/02/27.
 */
public class HorizontalCardLayoutManager extends LinearLayoutManager {

    private float mScale = 0.7f;

    public HorizontalCardLayoutManager(Context context) {
        super(context);
        this.setOrientation(LinearLayoutManager.HORIZONTAL);
    }

    public HorizontalCardLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    /**
     * 座標からアイテムの拡大率の計算
     *
     * @param left
     * @param parentWidth
     * @return
     */
    private float getScale(int left, int parentWidth) {
        return 1.0f - ((1.0f - mScale) * left / parentWidth);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public View findViewByPosition(int position) {
        // Viewの重なり順を逆転しているため
        // LinearLayoutManager.findViewByPositionの動作を修正
        final int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        final int firstIndex = childCount - 1;
        final int firstPosition = getPosition(getChildAt(firstIndex));
        final int viewIndex = firstIndex - (position - firstPosition);
        if (0 <= viewIndex && viewIndex < childCount) {
            return getChildAt(viewIndex);
        }
        return null;
    }

    /**
     * 初回のViewレイアウト
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // LinearLayoutManagerの処理を通しておく
        super.onLayoutChildren(recycler, state);
        // LinearLayoutManager によって配置されている View はすべて捨てる
        detachAndScrapAttachedViews(recycler);

        int count = getItemCount();
        int parentTop = getPaddingTop();
        int parentLeft = getPaddingLeft();
        int parentRight = getWidth() - getPaddingRight();
        int parentHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int parentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int left = parentLeft, top = 0, bottom = 0, right = 0, height = 0, width = 0, middle = 0;
        float scale = 1.0f;
        for (int i = 0; (i < count) && (left < parentRight); i++, left = middle) {
            View view = recycler.getViewForPosition(i);
            addView(view, 0);
            measureChildWithMargins(view, 0, 0);
            scale = getScale(left, parentWidth);
            width = getDecoratedMeasuredWidth(view);
            height = getDecoratedMeasuredHeight(view);
            top = parentTop + (parentHeight - height) / 2;
            bottom = top + height;
            right = left + width;
            middle = left + width / 2;
            view.setScaleX(scale);
            view.setScaleY(scale);
            layoutDecorated(view, left, top, right, bottom);
        }
    }

    /**
     * スクロール時のView移動やサイズ変更を行う
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() <= 0) {
            return 0;
        }
        int count = getItemCount();
        int parentTop = getPaddingTop();
        int parentLeft = getPaddingLeft();
        int parentRight = getWidth() - getPaddingRight();
        int parentHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int parentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int firstPosition = findLastVisibleItemPosition();
        View firstView = findViewByPosition(firstPosition);
        int lastPosition = findFirstVisibleItemPosition();
        View lastView = findViewByPosition(lastPosition);
        int firstIndex = 0;
        int lastIndex = getChildCount() - 1;
        int left = parentLeft, top = 0, bottom = 0, right = 0, height = 0, width = 0, middle = 0;
        float scale = 1.0f;
        int scrolled = 0;
        if (dx < 0) {
            // 右方向スワイプスクロール
            while (dx < scrolled) {
                left = getDecoratedLeft(firstView);
                middle = left + getDecoratedMeasuredWidth(firstView);
                // loop一回で移動できる距離を算出
                int scrollable = (0 < firstPosition)
                        ? Math.max(parentLeft - middle, 0)
                        : Math.max(parentLeft - left, 0);
                int scrollBy = Math.min(scrolled - dx, scrollable);
                scrolled -= scrollBy;
                // すべてのViewを水平方向に移動する
                offsetChildrenHorizontal(scrollBy);
                for (int i = 0; i < getChildCount(); i++) {
                    View view = getChildAt(i);
                    if (parentRight <= getDecoratedLeft(view)) {
                        // 画面からのはみ出しを検出
                        firstIndex++;
                        lastPosition--;
                    }
                }
                if (dx < scrolled) {
                    if (0 < firstPosition) {
                        // 新しいViewの生成
                        firstView = recycler.getViewForPosition(firstPosition - 1);
                        addView(firstView, getChildCount());
                        firstPosition--;
                        lastIndex++;
                        measureChildWithMargins(firstView, 0, 0);
                        right = middle;
                        left = right - getDecoratedMeasuredWidth(firstView);
                        height = getDecoratedMeasuredHeight(firstView);
                        top = parentTop + (parentHeight - height) / 2;
                        layoutDecorated(firstView, left, top, right, top + height);
                    } else {
                        break;
                    }
                }
            }
        } else if (0 < dx) {
            // 左方向スワイプスクロール
            while (scrolled < dx) {
                left = getDecoratedLeft(lastView);
                middle = left + getDecoratedMeasuredWidth(lastView) / 2;
                // loop一回で移動できる距離を算出
                int scrollable = (lastPosition + 1 < count)
                        ? Math.max(middle - parentRight, 0)
                        : Math.max(left - parentRight, 0);
                int scrollBy = Math.min(dx - scrolled, scrollable);
                scrolled += scrollBy;
                // すべてのViewを水平方向に移動する
                offsetChildrenHorizontal(-scrollBy);
                for (int i = getChildCount() - 1; 0 <= i; i--) {
                    View view = getChildAt(i);
                    if (getDecoratedRight(view) <= parentLeft) {
                        // 画面からのはみ出しを検出
                        lastIndex--;
                        firstPosition++;
                    }
                }
                if (scrolled < dx) {
                    if (lastPosition + 1 < count) {
                        // 新しいViewの生成
                        lastView = recycler.getViewForPosition(lastPosition + 1);
                        addView(lastView, 0);
                        lastPosition++;
                        lastIndex++;
                        measureChildWithMargins(lastView, 0, 0);
                        left = middle;
                        height = getDecoratedMeasuredHeight(lastView);
                        top = parentTop + (parentHeight - height) / 2;
                        layoutDecorated(lastView, left, top,
                                left + getDecoratedMeasuredWidth(lastView),
                                top + getDecoratedMeasuredHeight(lastView));
                    } else {
                        break;
                    }
                }
            }
        }
        // 画面からはみ出たViewを削除
        for (int i = getChildCount() - 1; lastIndex < i; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        for (int i = firstIndex - 1; 0 <= i; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        for (int i = 0; i < getChildCount(); i++) {
            // 位置に応じてViewのリサイズ
            View view = getChildAt(i);
            left = getDecoratedLeft(view);
            scale = getScale(left, parentWidth);
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
        return scrolled;
    }

}
