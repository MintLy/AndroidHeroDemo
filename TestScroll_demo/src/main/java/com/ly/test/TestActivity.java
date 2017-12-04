package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends Activity
{
    private View mTv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTv_test = findViewById(R.id.v_view);
    }

    public void test(View v)
    {
        View parent = (View) mTv_test.getParent();
        parent.scrollBy(-10, -10);
    }
}
