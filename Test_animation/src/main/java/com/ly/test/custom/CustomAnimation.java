package com.ly.test.custom;

import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 20170117 on 2017/11/6.
 */

public class CustomAnimation extends Animation
{
    private static final String TAG = "fuck";
    private int mCenterWidth;
    private int mCenterHeight;


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight)
    {
        super.initialize(width, height, parentWidth, parentHeight);
        Log.d(TAG, "initialize: ");
        setDuration(2000);
        this.mCenterWidth = width / 2;
        this.mCenterHeight = height / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}
