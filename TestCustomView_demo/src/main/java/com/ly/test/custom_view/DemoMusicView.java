package com.ly.test.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 20170117 on 2017/9/12.
 */

public class DemoMusicView extends View
{
    private int mRectCount = 10;
    private double mWidth;
    private int mRectWidth;
    private double offset = 20;
    private float currentHegiht;
    private float mRectHeight;
    private Paint mPaint;
    private double mRandom;
    private LinearGradient mLinearGradient;

    public DemoMusicView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++)
        {
            mRandom = Math.random();
            currentHegiht = (float) (mRectHeight * mRandom);
            canvas.drawRect((float) (mWidth * 0.4 / 2 + mRectWidth * i + offset), currentHegiht, (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)), mRectHeight, mPaint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6 / mRectCount);
        mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight, Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }


}
