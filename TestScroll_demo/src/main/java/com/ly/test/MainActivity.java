package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity implements View.OnTouchListener
{
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.v_view);
        mView.setOnTouchListener(this);
    }

    private int lastX;
    private int lastY;

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
                layoutParams.leftMargin = mView.getLeft() + offsetX;
                layoutParams.topMargin = mView.getTop() + offsetY;
                mView.setLayoutParams(layoutParams);
                break;
            default:
                break;
        }
        return true;
    }
}
