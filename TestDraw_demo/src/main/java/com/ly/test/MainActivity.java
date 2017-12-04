package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ly.test.view.MyView;

public class MainActivity extends Activity
{
    private static final String TAG = "fuck";
    private MyView mV_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv;
        mV_view = (MyView) findViewById(R.id.v_view);
    }


}
