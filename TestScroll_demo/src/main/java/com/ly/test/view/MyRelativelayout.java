package com.ly.test.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ly.test.tool.ViewTool;

/**
 * Created by 20170117 on 2017/10/21.
 */

public class MyRelativelayout extends RelativeLayout
{
    private Context mContext;
    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mMainView;
    private int mScreenWidth;
    private int mMenuViewWidth;
    private int mMainViewWidth;
    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback()
    {
        @Override
        public boolean tryCaptureView(View child, int pointerId)
        {
            //当触摸的view是MainView时开始检测
            return mMainView == child;
        }


        /**
         * 处理水平滑动
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx)
        {
            return left;
        }

        /**
         * 拖动结束后调用
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel)
        {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢移动到指定位置
            if (mMainView.getLeft() < mScreenWidth / 3)
            {
                //关闭菜单
                //相当于Scroller的startScroll方法
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(MyRelativelayout.this);
            }
            else
            {
                //打开菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, mMenuViewWidth, 0);
                ViewCompat.postInvalidateOnAnimation(MyRelativelayout.this);
            }
        }

        /**
         * 用户触摸到view后回调
         * @param capturedChild
         * @param activePointerId
         */
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId)
        {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        /**
         * 拖动状态改变时回调
         * @param state
         */
        @Override
        public void onViewDragStateChanged(int state)
        {
            super.onViewDragStateChanged(state);
        }

        /**
         * 位置改变时回调,常用于滑动时更改scale进行缩放等效果
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy)
        {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
    };

    public MyRelativelayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        this.mViewDragHelper = ViewDragHelper.create(this, mCallback);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        this.mScreenWidth = outMetrics.widthPixels;
    }


    /**
     * 将事件传递给ViewDargHelper
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //将触摸事件传递给ViewDargHelper
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll()
    {
        if (mViewDragHelper.continueSettling(true))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        this.mMenuView = getChildAt(0);
        this.mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuViewWidth = mMenuView.getMeasuredWidth();
        mMainViewWidth = mMainView.getMeasuredWidth();
    }
}
