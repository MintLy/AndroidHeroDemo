package com.ly.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 20170117 on 2017/11/4.
 */

public class MyDraw extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private static final String TAG = "fuck";
    private SurfaceHolder mSurfaceHolder;
    private Path mPath;
    private boolean mIsDrawing;
    private Canvas mCanvas;
    private Paint mPaint;

    public MyDraw(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
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
        long start = System.currentTimeMillis();
        while (mIsDrawing)
        {
            draw();
        }
        long end = System.currentTimeMillis();
        if (end - start > 100)
        {
            try
            {
                Thread.sleep(100 - (end - start));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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
