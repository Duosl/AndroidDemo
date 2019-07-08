package com.duosl.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by duoshilin on 2019-06-25.
 */
public class SwipeDelView extends ViewGroup {

    private static final String TAG = "SwipeDelView";

    //按下时x，移动时x，移动的距离，额外选项的总宽度
    private int downX, moveX, moved, optionSumWidth;
    private Scroller scroller = new Scroller(getContext());
    private boolean haveShowRight = false;
    public SwipeDelView swipeDelView;


    public SwipeDelView(Context context) {
        super(context);
    }

    public SwipeDelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeDelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        View child = getChildAt(0);
        int margin = ((MarginLayoutParams) child.getLayoutParams()).topMargin
                + ((MarginLayoutParams) child.getLayoutParams()).bottomMargin;
        setMeasuredDimension(width, getChildAt(0).getMeasuredHeight() + margin);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(l, t, r, b);
        l = r;

//        View firstView, secondView;
//        if ((firstView  = getChildAt(1)) != null) {
//            firstView.layout(l, t, l + firstView.getMeasuredWidth(), b);
//            l = l + firstView.getMeasuredWidth();
//        }
//        if ((secondView = getChildAt(2)) != null) {
//            secondView.layout(l, t, l + secondView.getMeasuredWidth(), b);
//            l = l + secondView.getMeasuredWidth();
//        }

        for (int i = 1; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view != null) {
                view.layout(l, t, l + view.getMeasuredWidth(), b);
                l = l + view.getMeasuredWidth();

                optionSumWidth += view.getMeasuredWidth();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (swipeDelView != null && swipeDelView == this) {
            swipeDelView.closeMenus();
            swipeDelView = null;
        }
    }

    //缓慢滚动到指定位置
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000ms内滑动destX，效果就是慢慢滑动
        scroller.startScroll(scrollX, 0, delta, 0, 100);
        invalidate();
    }

    public void closeMenus() {
        smoothScrollTo(0, 0);
        haveShowRight = false;
    }

    public void closeMenu() {
        swipeDelView.closeMenus();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scroller.isFinished()) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
//                Log.d(TAG, "onTouchEvent--DOWN: " + downX);
                break;

            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "onTouchEvent--MOVE: " + downX);
//                if (swipeDelView != null && swipeDelView == this && haveShowRight) {
//                    closeMenu();
//                    return true;
//                }
                moveX = (int) ev.getRawX();
                moved = moveX - downX;
                System.out.println(downX+"===1==="+moveX);

                if (haveShowRight) {
                    moved -= optionSumWidth;
                }

                if (moved <= 0) {
                    scrollTo(-moved > optionSumWidth ? optionSumWidth : -moved, 0);
                }


//                Log.d(TAG, moved + " onTouchEvent: " + getScrollX());
                if (getScrollX() <= 0) {
                    scrollTo(0, 0);
                } else if (getScrollX() >= optionSumWidth) {
                    scrollTo(optionSumWidth, 0);
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "onTouchEvent--UP: " + downX);
//                if (swipeDelView != null) {
//                    closeMenu();
//                }
                if (getScrollX() >= optionSumWidth / 2) {
                    haveShowRight = true;
                    swipeDelView = this;
                    smoothScrollTo(optionSumWidth, 0);
                } else {
                    haveShowRight = false;
                    smoothScrollTo(0, 0);
                }
                if (moved != 0) {
                    return false;
                }

                break;

        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                System.out.println("downX===2==="+ev.getRawX());
            case MotionEvent.ACTION_MOVE:
                moveX = (int) ev.getRawX();
                moved = moveX - downX;
                System.out.println("moveX===2==="+ev.getRawX());

                if (haveShowRight) {
                    moved -= optionSumWidth;
                }

                if (moved <= 0) {
                    scrollTo(-moved > optionSumWidth ? optionSumWidth : -moved, 0);
                }


                Log.d(TAG, moved + " onTouchEvent: " + getScrollX());
                if (getScrollX() <= 0) {
                    scrollTo(0, 0);
                } else if (getScrollX() >= optionSumWidth) {
                    scrollTo(optionSumWidth, 0);
                }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (getScrollX() >= optionSumWidth / 2) {
                    haveShowRight = true;
                    swipeDelView = this;
                    smoothScrollTo(optionSumWidth, 0);
                } else {
                    haveShowRight = false;
                    smoothScrollTo(0, 0);
                }

        }
        if (moved != 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
