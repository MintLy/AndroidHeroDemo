package com.ly.test.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.ly.test.tool.Lg;

import static android.R.attr.y;

/**
 * Created by 20170117 on 2017/9/13.
 */

public class DemoScrollView extends ViewGroup
{
    private int mScreenHeight;
    private int mLastY;
    private Scroller mScroller;
    private int mEnd;
    private int mStart;

    public DemoScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int childCount = getChildCount();
        //设置Viewgroup高度
        MarginLayoutParams viewgroupLayoutParams = (MarginLayoutParams) getLayoutParams();
        viewgroupLayoutParams.height = mScreenHeight * childCount;
        setLayoutParams(viewgroupLayoutParams);
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE)
            {
                childView.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Lg.d(event.getY() + "");
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0)
                {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight)
                {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //TODO  start
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0)
                {
                    if (dScrollY < mScreenHeight / 3)
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                    else
                    {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    }
                }
                else
                {
                    if (-dScrollY < mScreenHeight / 3)
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                    else
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    }
                }
                //TODO stop
                break;
            default:
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
