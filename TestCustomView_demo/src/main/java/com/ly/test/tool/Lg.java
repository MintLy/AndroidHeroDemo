package com.ly.test.tool;

import android.util.Log;

/**
 * Created by 20170117 on 2017/9/13.
 */

public class Lg
{
    private static final String TAG = "fuck";

    public static void d(String msg)
    {
        Log.d(TAG, msg);
    }

    public static void i(String msg)
    {
        Log.i(TAG, msg);
    }

    public static void e(String msg)
    {
        Log.e(TAG, msg);
    }
}
