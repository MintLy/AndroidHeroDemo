package com.imooc.android5x.animation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imooc.android5x.R;

public class StartAnimationActivity extends Activity
{
    private android.app.Activity mActivity;
    private Button mBtn_fj;
    private Button mBtn_hd;
    private Button mBtn_dr;
    private Button mBtn_test;
    public static final String SKIP_MODE = "skip_mode";
    public static final int SKIP_MODE_FJ = 1;
    public static final int SKIP_MODE_HD = 2;
    public static final int SKIP_MODE_DR = 3;
    public static final int SKIP_MODE_OTHER = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testanimation_start_activity);
        this.mActivity = this;
        mBtn_fj = findViewById(R.id.btn_fj);
        mBtn_hd = findViewById(R.id.btn_hd);
        mBtn_dr = findViewById(R.id.btn_dr);
        mBtn_test = findViewById(R.id.btn_test);
        View.OnClickListener btnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mActivity, TestAnimationActivity.class);
                switch (view.getId())
                {
                    case R.id.btn_fj:
                        intent.putExtra(SKIP_MODE, SKIP_MODE_FJ);
                        break;
                    case R.id.btn_hd:
                        intent.putExtra(SKIP_MODE, SKIP_MODE_HD);
                        break;
                    case R.id.btn_dr:
                        intent.putExtra(SKIP_MODE, SKIP_MODE_DR);
                        break;
                    case R.id.btn_test:
                        intent.putExtra(SKIP_MODE, SKIP_MODE_OTHER);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, mBtn_test, "testTransition").toBundle());
                        return;
                    default:
                        break;
                }
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle());
            }
        };
        mBtn_fj.setOnClickListener(btnClickListener);
        mBtn_hd.setOnClickListener(btnClickListener);
        mBtn_dr.setOnClickListener(btnClickListener);
        mBtn_test.setOnClickListener(btnClickListener);
    }
}
