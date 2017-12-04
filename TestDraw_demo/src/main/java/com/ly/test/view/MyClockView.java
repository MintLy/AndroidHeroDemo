package com.ly.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by 20170117 on 2017/10/31.
 */

public class MyClockView extends View
{
    private Context mContext;
    private int mScreenWidth;
    private int mScreenHeight;

    private Paint mBlackPaint;
    private Paint mBlackScaleLinePaint;
    private Paint mBlackHousePaint;
    private Paint mBlackMinutePaint;

    public MyClockView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        initParams();
        initPaint();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawCircle(mScreenWidth / 2, mScreenHeight / 2, mScreenWidth / 2, mBlackPaint);
        drawScaleLine(canvas);
        drawPointer(canvas);
    }

    private void initParams()
    {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;

    }

    private void initPaint()
    {
        mBlackPaint = new Paint();
        mBlackPaint.setColor(Color.BLACK);
        mBlackPaint.setStyle(Paint.Style.STROKE);
        mBlackPaint.setStrokeWidth(5);
        mBlackScaleLinePaint = new Paint();
        mBlackScaleLinePaint.setStrokeWidth(3);
        mBlackScaleLinePaint.setColor(Color.BLACK);
        mBlackScaleLinePaint.setStyle(Paint.Style.STROKE);
        mBlackHousePaint = new Paint();
        mBlackHousePaint.setStrokeWidth(20);
        mBlackMinutePaint = new Paint();
        mBlackMinutePaint.setStrokeWidth(10);
    }


    private void drawScaleLine(Canvas canvas)
    {
        for (int i = 0; i < 24; i++)
        {
            String s = String.valueOf(i);
            if (i == 0 || i == 6 || i == 12 || i == 18)
            {
                mBlackScaleLinePaint.setStrokeWidth(5);
                mBlackScaleLinePaint.setTextSize(30);
                canvas.drawLine(mScreenWidth / 2, mScreenHeight / 2 - mScreenWidth / 2, mScreenWidth / 2,
                        mScreenHeight / 2 - mScreenWidth / 2 + 60, mBlackScaleLinePaint);
                canvas.drawText(s, mScreenWidth / 2 - mBlackScaleLinePaint.measureText(s) / 2, mScreenHeight / 2 -
                        mScreenWidth / 2 + 90, mBlackScaleLinePaint);
            }
            else
            {
                mBlackScaleLinePaint.setStrokeWidth(3);
                mBlackScaleLinePaint.setTextSize(15);
                canvas.drawLine(mScreenWidth / 2, mScreenHeight / 2 - mScreenWidth / 2, mScreenWidth / 2,
                        mScreenHeight / 2 - mScreenWidth / 2 + 30, mBlackScaleLinePaint);
                canvas.drawText(s, mScreenWidth / 2 - mBlackScaleLinePaint.measureText(s) / 2, mScreenHeight / 2 -
                        mScreenWidth / 2 + 60, mBlackScaleLinePaint);
            }
            //旋转画布
            canvas.rotate(15, mScreenWidth / 2, mScreenHeight / 2);
        }
    }

    private void drawPointer(Canvas canvas)
    {
        canvas.save();
        //画布坐标平移
        canvas.translate(mScreenWidth / 2, mScreenHeight / 2);
        canvas.drawLine(0, 0, 100, 100, mBlackHousePaint);
        canvas.drawLine(0, 0, 100, 200, mBlackMinutePaint);
        canvas.restore();
    }
}
