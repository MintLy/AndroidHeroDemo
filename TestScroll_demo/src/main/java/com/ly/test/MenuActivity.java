package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuActivity extends Activity
{
    private LinearLayout mLayout_main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mLayout_main = findViewById(R.id.layout_menu);
    }

    public void left(View v)
    {
        mLayout_main.scrollBy(-20, 0);
    }

    public void right(View v)
    {
        mLayout_main.scrollBy(20, 0);
    }
}
