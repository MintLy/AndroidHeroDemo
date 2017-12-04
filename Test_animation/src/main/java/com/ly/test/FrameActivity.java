package com.ly.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class FrameActivity extends Activity
{
    private static final String TAG = "fuck";
    private Button mBtn_testTransparency;
    private Button mBtn_testRotate;
    private Button mBtn_testTranslation;
    private Button mBtn_testScale;
    private Button mBtn_testOrganize;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        mBtn_testTransparency = findViewById(R.id.btn_testTransparency);
        mBtn_testRotate = findViewById(R.id.btn_testRotate);
        mBtn_testTranslation = findViewById(R.id.btn_testTranslation);
        mBtn_testScale = findViewById(R.id.btn_testScale);
        mBtn_testOrganize = findViewById(R.id.btn_testOrganize);
        View.OnClickListener btnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btn_testTransparency:
                        testTransparency();
                        break;
                    case R.id.btn_testRotate:
                        testRotate();
                        break;
                    case R.id.btn_testTranslation:
                        testTranslation();
                        break;
                    case R.id.btn_testScale:
                        testScale();
                        break;
                    case R.id.btn_testOrganize:
                        testOrganize();
                        break;
                    default:
                        break;
                }
            }
        };
        mBtn_testTransparency.setOnClickListener(btnClickListener);
        mBtn_testRotate.setOnClickListener(btnClickListener);
        mBtn_testTranslation.setOnClickListener(btnClickListener);
        mBtn_testScale.setOnClickListener(btnClickListener);
        mBtn_testOrganize.setOnClickListener(btnClickListener);
    }

    /**
     * 透明
     */
    public void testTransparency()
    {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        //绑定监听事件
        aa.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                Log.d(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
                Log.d(TAG, "onAnimationRepeat: ");
            }
        });
        mBtn_testTransparency.startAnimation(aa);
    }

    /**
     * 旋转
     */
    public void testRotate()
    {
        //左上角旋转
        //        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        //        ra.setDuration(1000);
        //        mV_test.startAnimation(ra);
        //中心旋转
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation
                .RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        mBtn_testRotate.startAnimation(ra);
    }

    /**
     * 平移
     */
    public void testTranslation()
    {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(1000);
        mBtn_testTranslation.startAnimation(ta);
    }

    /**
     * 缩放
     */
    public void testScale()
    {
        //左上角缩放
        //        Animation ta = new ScaleAnimation(0, 2, 0, 2);
        //        ta.setDuration(1000);
        //        mV_test.startAnimation(ta);
        //中心缩放
        Animation ta = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF,
                0.5F);
        ta.setDuration(1000);
        mBtn_testScale.startAnimation(ta);

    }

    /**
     * 组合动画
     */
    public void testOrganize()
    {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);
        //透明动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        as.addAnimation(aa);
        //平移动画
        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        as.addAnimation(ta);
        mBtn_testOrganize.startAnimation(as);
    }
}
