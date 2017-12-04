package com.ly.test.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by 20170117 on 2017/10/14.
 */

public class MyListView_1 extends ListView
{
    private Context mContext;
    private int mMaxOverDistance = 100;

    public MyListView_1(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    /**
     * 通过重新此方法，改变 maxOverScrollY 的值，达到弹性效果
     *
     * @param maxOverScrollY
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
    {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }

    private void initView()
    {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) density * mMaxOverDistance;
    }
}
