package com.ly.test.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;


/**
 * Created by 20170117 on 2017/9/16.
 */

public class MyScrollView extends ViewGroup
{
    private int mScreenHeight;
    private Context mContext;
    private Scroller mScroller;

    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++)
        {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int childCount = getChildCount();
        //设置Viewgroup高度
        MarginLayoutParams viewgroupLayoutParams = (MarginLayoutParams) getLayoutParams();
        viewgroupLayoutParams.height = mScreenHeight * childCount;
        setLayoutParams(viewgroupLayoutParams);

        //设置子view的位置
        int height = 0;
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            childView.layout(0, i * mScreenHeight, childView.getMeasuredWidth(), (i + 1) * mScreenHeight);
            height += childView.getMeasuredHeight();
        }
    }

    float lastY = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                int moveY = (int) (event.getY() - lastY) * 2;
                int top = getScrollY();
                int viewHeight = getHeight();
                int dy = top - moveY;
                if (dy > viewHeight - mScreenHeight)
                {
                    dy = viewHeight - mScreenHeight;
                }
                else if (dy < 0)
                {
                    dy = 0;
                }
                scrollTo(0, dy);
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int childCount = getChildCount();
                if (scrollY != 0)
                {
                    double result = scrollY * 1.0 / (childCount * mScreenHeight);
                    int round = (int) Math.round(result * childCount);
                    // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                    int currentTopY = round * mScreenHeight;
                    int huaDongJuLi = currentTopY - scrollY;
                    mScroller.startScroll(0, scrollY, 0, huaDongJuLi);
                    invalidate();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll()
    {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
