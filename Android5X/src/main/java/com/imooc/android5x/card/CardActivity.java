package com.imooc.android5x.card;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.imooc.android5x.R;

public class CardActivity extends Activity
{
    private static final String TAG = "fuck";
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        this.mContext = this.getApplicationContext();
    }
}