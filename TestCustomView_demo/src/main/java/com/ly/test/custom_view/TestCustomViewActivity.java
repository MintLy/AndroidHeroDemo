package com.ly.test.custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ly.test.R;

public class TestCustomViewActivity extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);
    }
}
