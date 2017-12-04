package com.ly.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 20170117 on 2017/11/2.
 */

public class MySurfaceview extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private static final String TAG = "fuck";
    private SurfaceHolder mSurfaceHolder;
    //用于绘图的Canvas
    private Canvas mCanvas;
    //子线程标志
    private boolean mIsDrawing;
    private int x;
    private int y;
    private Path mPath;
    private Paint mPaint;

    public MySurfaceview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        this.mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        this.mIsDrawing = false;
    }

    @Override
    public void run()
    {
        while (mIsDrawing)
        {
            draw();
            x += 1;
            int sin = (int) (100 * Math.sin(2 * x * Math.PI / 180));
            Log.d(TAG, "run: sin=" + sin);
            y = sin + 400;
            mPath.lineTo(x, y);
        }
    }

    /**
     * 初始化参数
     */
    private void init()
    {
        this.mSurfaceHolder = getHolder();
        this.mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        //        mSurfaceHolder.setFormat(PixelFormat.OPAQUE);
        this.mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }

    /**
     * 绘制内容
     */
    private void draw()
    {
        try
        {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mCanvas != null)
            {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

}
