package com.imooc.android5x;


import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by Mint on 2017/11/29.
 */

public class ClippingActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_clipping);
        View viewById1 = findViewById(R.id.tv_rect);
        View viewById2 = findViewById(R.id.tv_circle);
        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider()
        {
            @Override
            public void getOutline(View view, Outline outline)
            {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        };
        ViewOutlineProvider viewOutlineProvider2 = new ViewOutlineProvider()
        {
            @Override
            public void getOutline(View view, Outline outline)
            {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };
        viewById1.setOutlineProvider(viewOutlineProvider1);
        viewById2.setOutlineProvider(viewOutlineProvider2);
    }
}
