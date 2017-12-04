package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ly.test.custom.CustomAnimation;
import com.ly.test.custom.CustomAnimation3D;

public class CustomActivity extends Activity
{
    private Button mBtn_customAnimator;
    private Button mBtn_custom3DAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initViews();
        testCustomAnimator();
        testCustom3DAnimator();
    }

    private void initViews()
    {
        mBtn_customAnimator = findViewById(R.id.btn_customanimator);
        mBtn_custom3DAnimator = findViewById(R.id.btn_custom3danimator);
    }

    private void testCustomAnimator()
    {
        CustomAnimation ca = new CustomAnimation();
        mBtn_customAnimator.setAnimation(ca);
        ca.start();
    }

    private void testCustom3DAnimator()
    {
        CustomAnimation3D ca3 = new CustomAnimation3D(10);
        mBtn_custom3DAnimator.setAnimation(ca3);
        ca3.start();
    }

}
