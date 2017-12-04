package com.ly.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.ly.test.tool.Lg;

/**
 * Created by 20170117 on 2017/10/16.
 */

public class MyView5 extends View
{
    private Context mContext;
    private Scroller mScroller;

    private int lastX;
    private int lastY;

    public MyView5(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        this.mScroller = new Scroller(mContext);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                View parent = (View) getParent();
                int scrollX = parent.getScrollX();
                int scrollY = parent.getScrollY();
                Lg.d("ACTION_UP --- scrollX:" + scrollX + " --- scrollY:" + scrollY);
                mScroller.startScroll(scrollX, scrollY, -scrollX, -scrollY);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset())
        {
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            Lg.d("computeScroll --- Currx:" + currX + " --- Curry:" + currY);
            ((View) getParent()).scrollTo(currX, currY);
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }
}
