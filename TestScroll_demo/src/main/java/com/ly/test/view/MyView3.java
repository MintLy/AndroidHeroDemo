package com.ly.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ly.test.tool.Lg;

/**
 * Created by 20170117 on 2017/10/16.
 */

public class MyView3 extends View
{
    public MyView3(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void layout(int l, int t, int r, int b)
    {
        super.layout(l, t, r, b);
    }

    private int lastX;
    private int lastY;

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
                Lg.d("x:" + ((View) getParent()).getScrollX());
                Lg.d("y:" + ((View) getParent()).getScrollY());
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }
}
