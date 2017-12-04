package com.ly.test.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ly.test.R;

/**
 * Created by 20170117 on 2017/9/17.
 */

public class MyTopbar extends RelativeLayout
{
    private String mTitleText;
    private int mTitleTextColor;
    private float mTitleSize;
    private String mLeftButtonText;
    private int mLeftButtonTextColor;
    private String mRightButtonText;
    private int mRightButtonTextColor;

    private Button mBtn_left;
    private TextView mTv_title;
    private Button mBtn_right;

    public MyTopbar(Context mContext, AttributeSet attrs)
    {
        super(mContext, attrs);
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.Topbar);
        this.mTitleText = ta.getString(R.styleable.Topbar_title);
        this.mTitleSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 10);
        this.mTitleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
        this.mLeftButtonText = ta.getString(R.styleable.Topbar_leftButtonTest);
        this.mLeftButtonTextColor = ta.getColor(R.styleable.Topbar_leftButtonTextColor, 0);
        this.mRightButtonText = ta.getString(R.styleable.Topbar_rightButtonTest);
        this.mRightButtonTextColor = ta.getColor(R.styleable.Topbar_rightButtonTextColor, 0);
        ta.recycle();

        mBtn_left = new Button(mContext);
        mTv_title = new TextView(mContext);
        mBtn_right = new Button(mContext);
        //设置对应自定义属性
        mBtn_left.setText(mLeftButtonText);
        mBtn_left.setTextColor(mLeftButtonTextColor);
        mTv_title.setText(mTitleText);
        mTv_title.setTextSize(mTitleSize);
        mTv_title.setTextColor(mTitleTextColor);
        mBtn_right.setText(mRightButtonText);
        mBtn_right.setTextColor(mRightButtonTextColor);
        //设置布局
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(mBtn_left, leftParams);

        RelativeLayout.LayoutParams centerTitleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        centerTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mTv_title, centerTitleParams);

        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(mBtn_right, rightParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


}
