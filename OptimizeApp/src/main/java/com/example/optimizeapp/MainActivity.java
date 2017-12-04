package com.example.optimizeapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

public class MainActivity extends Activity
{
    private static final String TAG = "fuck";
    private ViewStub mVs_content;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        Debug.startMethodTracing();
        setContentView(R.layout.activity_main);
        mVs_content = findViewById(R.id.vs_contento);
    }

    public void getPhoneMemory(View v)
    {
        mVs_content.setVisibility(View.VISIBLE);
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int largeMemoryClass = manager.getLargeMemoryClass();
        Log.d(TAG, "getPhoneMemory: " + largeMemoryClass);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
//        Debug.stopMethodTracing();
    }
}