package com.ly.test.custom;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by 20170117 on 2017/11/6.
 */

public class CustomAnimation3D extends Animation
{
    private int mCenterWidth;
    private int mCenterHeight;
    private Camera mCamera;
    private int mRotateY;

    public CustomAnimation3D(int rotate)
    {
        this.mRotateY = rotate;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight)
    {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        //设置默认时长
        setDuration(2000);
        //动画结束后保留状态
        setFillAfter(true);
        //设置默认插值器
        setInterpolator(new BounceInterpolator());
        this.mCenterWidth = width / 2;
        this.mCenterHeight = height / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        //        super.applyTransformation(interpolatedTime, t);

        Matrix matrix = t.getMatrix();
        mCamera.save();
        //使用Camera设置旋转的角度
        mCamera.rotateY(mRotateY);
        //将旋转变换作用到matrix上
        mCamera.getMatrix(matrix);
        mCamera.restore();
        //通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }
}
