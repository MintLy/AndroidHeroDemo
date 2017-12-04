package com.imooc.android5x.animation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.imooc.android5x.R;

public class TestAnimationActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int mode = getIntent().getExtras().getInt(StartAnimationActivity.SKIP_MODE);
        // 设置不同的动画效果
        switch (mode)
        {
            //分解模式
            case StartAnimationActivity.SKIP_MODE_FJ:
                getWindow().setEnterTransition(new Explode());
                getWindow().setExitTransition(new Explode());
                break;
            //滑动模式
            case StartAnimationActivity.SKIP_MODE_HD:
                getWindow().setEnterTransition(new Slide());
                getWindow().setExitTransition(new Slide());
                break;
            //淡出模式
            case StartAnimationActivity.SKIP_MODE_DR:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
            default:
                break;
        }
        setContentView(R.layout.testanimation_test_activity);
    }
}
