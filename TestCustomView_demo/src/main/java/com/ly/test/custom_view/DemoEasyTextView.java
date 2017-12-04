package com.ly.test.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 20170117 on 2017/9/11.
 */

public class DemoEasyTextView extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;

    public DemoEasyTextView(Context pContext, AttributeSet pAttributeSet) {
        super(pContext, pAttributeSet);
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = measureWh(widthMeasureSpec);
        int h = measureWh(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        canvas.drawRect(0, 0, measuredWidth, measuredHeight, mPaint1);
        canvas.drawRect(50, 50, measuredWidth - 50, measuredHeight - 50, mPaint2);
        canvas.save();
        canvas.translate(50, 50);
        super.onDraw(canvas);
        canvas.restore();
    }

    private int measureWh(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
