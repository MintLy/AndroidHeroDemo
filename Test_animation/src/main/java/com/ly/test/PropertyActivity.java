package com.ly.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;

public class PropertyActivity extends Activity
{
    private static final String TAG = "fuck";
    private Context mContext;
    private Button mBtn_testProperty;
    private Button mBtn_testOrganizeProperty;
    private Button mBtn_testAnimatorValue;
    private Button mBtn_testListenerAnimatorEvent;
    private Button mBtn_testAnimatorSet;
    private Button mBtn_testXmlAnimator;
    private Button mBtn_testViewAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        this.mContext = this.getApplicationContext();
        initViews();
        initEvent();
    }

    private void initViews()
    {
        mBtn_testProperty = findViewById(R.id.btn_testProperty);
        mBtn_testOrganizeProperty = findViewById(R.id.btn_testOrganizeProperty);
        mBtn_testAnimatorValue = findViewById(R.id.btn_testValueAnimator);
        mBtn_testListenerAnimatorEvent = findViewById(R.id.btn_testListenerAnimatorEvent);
        mBtn_testAnimatorSet = findViewById(R.id.btn_testAnimatorSet);
        mBtn_testXmlAnimator = findViewById(R.id.btn_testXmlAnimator);
        mBtn_testViewAnimator = findViewById(R.id.btn_testViewAnimator);
    }

    private void initEvent()
    {
        View.OnClickListener btnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btn_testProperty:
                        testProperty();
                        break;
                    case R.id.btn_testOrganizeProperty:
                        testOrganizeProperty();
                        break;
                    case R.id.btn_testValueAnimator:
                        testAnimatorValue();
                        break;
                    case R.id.btn_testListenerAnimatorEvent:
                        testListenerAnimatorEvent();
                        break;
                    case R.id.btn_testAnimatorSet:
                        testAnimatorSet();
                        break;
                    case R.id.btn_testXmlAnimator:
                        testXmlAnimator();
                        break;
                    case R.id.btn_testViewAnimator:
                        testViewAnimator();
                        break;
                    default:
                        break;
                }
            }
        };
        mBtn_testProperty.setOnClickListener(btnClickListener);
        mBtn_testOrganizeProperty.setOnClickListener(btnClickListener);
        mBtn_testAnimatorValue.setOnClickListener(btnClickListener);
        mBtn_testListenerAnimatorEvent.setOnClickListener(btnClickListener);
        mBtn_testAnimatorSet.setOnClickListener(btnClickListener);
        mBtn_testXmlAnimator.setOnClickListener(btnClickListener);
        mBtn_testViewAnimator.setOnClickListener(btnClickListener);
    }

    /**
     * 属性动画
     */
    private void testProperty()
    {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mBtn_testProperty, "translationX", 300);
        oa.setDuration(1000);
        oa.start();
    }

    /**
     * 组合属性动画
     */
    private void testOrganizeProperty()
    {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBtn_testOrganizeProperty);
        objectAnimator.setValues(pvh1, pvh2, pvh3);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void testAnimatorValue()
    {
        ValueAnimator va = ValueAnimator.ofFloat(0, 100);
        va.setTarget(mBtn_testOrganizeProperty);
        va.setDuration(1000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                Float animatedValue = (Float) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: " + animatedValue);
            }
        });
        va.start();
    }

    /**
     * 测试动画事件监听
     */
    private void testListenerAnimatorEvent()
    {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mBtn_testListenerAnimatorEvent, "alpha", 0.5F);
        oa.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
                super.onAnimationStart(animation);
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
                super.onAnimationRepeat(animation);
                Log.d(TAG, "onAnimationRepeat: ");
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                Log.d(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
                super.onAnimationCancel(animation);
                Log.d(TAG, "onAnimationCancel: ");
            }
        });
        oa.setDuration(1000);
        oa.start();
    }

    private void testAnimatorSet()
    {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(mBtn_testAnimatorSet, "translationX", 300f);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(mBtn_testAnimatorSet, "scaleX", 1f, 0f, 1f);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(mBtn_testAnimatorSet, "scaleY", 1f, 0f, 1f);
        AnimatorSet as = new AnimatorSet();
        as.setDuration(1000);
        as.playTogether(oa1, oa2, oa3);
        as.start();
    }

    private void testXmlAnimator()
    {
        Animator ai = AnimatorInflater.loadAnimator(mContext, R.animator.scalex);
        ai.setTarget(mBtn_testXmlAnimator);
        ai.start();
    }

    private void testViewAnimator()
    {
        mBtn_testViewAnimator.animate().alpha(0).y(300).setDuration(300).withStartAction(new Runnable()
        {
            @Override
            public void run()
            {

            }
        }).withEndAction(new Runnable()
        {
            @Override
            public void run()
            {

            }
        }).start();
    }
}
