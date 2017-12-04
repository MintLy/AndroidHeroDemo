package com.ly.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutActivity extends Activity
{

    private Context mContext;
    private LinearLayout mLayout_group;
    private Button mBtn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        this.mContext = getApplicationContext();
        initViews();
        initEvent();
        //设置过度动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        //设置布局动画的显示属性
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        mLayout_group.setLayoutAnimation(lac);
    }

    private void initViews()
    {
        mLayout_group = findViewById(R.id.layout_group);
        mBtn_test = findViewById(R.id.btn_test);
    }

    private void initEvent()
    {
        View.OnClickListener btnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View view = new View(mContext);
                view.setBackgroundColor(Color.RED);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                layoutParams.setMargins(5, 5, 5, 5);
                view.setLayoutParams(layoutParams);
                mLayout_group.addView(view);
            }
        };
        mBtn_test.setOnClickListener(btnClickListener);
    }
}
