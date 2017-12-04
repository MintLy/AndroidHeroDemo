package com.ly.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by 20170117 on 2017/10/23.
 */

public class MyView extends View
{
    private static final String TAG = "fuck";
    private Context mContext;
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mPaintRed;


    public MyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        initParams();
        initTools();
    }

    private void initParams()
    {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;

    }

    private void initTools()
    {
        mPaintRed = new Paint();
        mPaintRed.setColor(Color.RED);
        mPaintRed.setStyle(Paint.Style.STROKE);
        mPaintRed.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int width = mScreenWidth / 2;
//        int height = mScreenHeight / 2;
        int height = 858;
        //画圆
//        canvas.drawCircle(width, height, 200, mPaintRed);
        //画方
//        canvas.drawRect(width - 200, height - 200, width + 200, height + 200, mPaintRed);
        //画线
//        canvas.drawLine(0, height, mScreenWidth, height, mPaintRed);
//        canvas.drawLines(new float[]{width - 200, height - 200, width + 200, height - 200,
//                width + 200, height - 200, width + 200, height + 200,
//                width + 200, height + 200, width - 200, height + 200,
//                width - 200, height + 200, width - 200, height - 200}, mPaintRed);
        //画点
//        canvas.drawPoint(width, height, mPaintRed);
        //画圆角矩形
//        canvas.drawRoundRect(width - 200, height - 200, width + 200, height + 200, 100, 100, mPaintRed);
        //绘制弧形和扇形
//        boolean useCenter = true;//扇形
//        boolean useCenter = false;//弧形
//        canvas.drawArc(width - 200, height - 200, width + 200, height + 200, 0, 90, useCenter, mPaintRed);
        //绘制五角星
        Path path = new Path();
        path.moveTo(width - 150, height);
        path.lineTo(width + 150, height);
        path.lineTo(width - 100, height + 100);
        path.lineTo(width, height - 100);
        path.lineTo(width + 100, height + 100);
        path.lineTo(width - 150, height);
        canvas.drawPath(path, mPaintRed);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
