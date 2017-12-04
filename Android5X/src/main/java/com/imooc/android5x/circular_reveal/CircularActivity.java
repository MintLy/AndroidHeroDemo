package com.imooc.android5x.circular_reveal;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.imooc.android5x.R;

public class CircularActivity extends Activity
{
    private static final int[] IMAGE_STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] IMAGE_STATE_UNCHECKED = new int[]{};
    private Context mContext;
    private boolean mIsCheck;
    private ImageView mImg_1;
    private ImageView mImg_2;
    private Button mBtn_test;
    private ImageView mImg_test;
    private Drawable mDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        this.mContext = this.getApplicationContext();
        mImg_1 = findViewById(R.id.img_1);
        mImg_2 = findViewById(R.id.img_2);
        mBtn_test = findViewById(R.id.btn_select);
        mImg_test = findViewById(R.id.img_test);
        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(mContext, R.drawable.select_change);
        mBtn_test.setStateListAnimator(stateListAnimator);
        View.OnClickListener imgClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.img_1:
                        Animator animator1 = ViewAnimationUtils.createCircularReveal(mImg_1, mImg_1.getWidth() / 2, mImg_1.getHeight() /
                                2, mImg_1.getWidth(), 0);
                        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator1.setDuration(2000);
                        animator1.start();
                        break;
                    case R.id.img_2:
                        Animator animator2 = ViewAnimationUtils.createCircularReveal(mImg_2, 0, 0, 0, (float) Math.hypot(mImg_2.getWidth
                                (), mImg_2.getHeight()));
                        animator2.setInterpolator(new AccelerateInterpolator());
                        animator2.setDuration(2000);
                        animator2.start();
                        break;
                    case R.id.img_test:
                        imageClickAnimator();
                        break;
                    default:
                        break;
                }
            }
        };
        mImg_1.setOnClickListener(imgClickListener);
        mImg_2.setOnClickListener(imgClickListener);
        mImg_test.setOnClickListener(imgClickListener);
        mDrawable = getResources().getDrawable(R.drawable.select_fram_change);
        mImg_test.setImageDrawable(mDrawable);
    }

    private void imageClickAnimator()
    {
        if (mIsCheck)
        {
            mImg_test.setImageState(IMAGE_STATE_UNCHECKED, true);
            mIsCheck = false;
        }
        else
        {
            mImg_test.setImageState(IMAGE_STATE_CHECKED, true);
            mIsCheck = true;
        }
    }
}
