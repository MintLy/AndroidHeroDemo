package com.imooc.android5x.recycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.imooc.android5x.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends Activity
{
    private static final String TAG = "fuck";
    private Context mContext;
    private RecyclerView.LayoutManager mRcManager;
    private RecyclerAdapter mAdapter;
    private List<String> mData = new ArrayList<String>();

    private RecyclerView mRc_listdata;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        this.mContext = this.getApplicationContext();
        initViews();
        init();
        initEvent();
    }


    private void initViews()
    {
        mRc_listdata = findViewById(R.id.rc_listdata);
        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    private void initEvent()
    {
        RecyclerAdapter.OnItemClickListener rcItemClickListener = new RecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Log.d(TAG, "onItemClick: " + mData.get(position));
                Toast.makeText(mContext, mData.get(position), Toast.LENGTH_SHORT).show();
            }
        };
        mAdapter.setOnItemClickListener(rcItemClickListener);
    }

    private void init()
    {
        mRcManager = new LinearLayoutManager(mContext);
        mRc_listdata.setLayoutManager(mRcManager);
        mRc_listdata.setHasFixedSize(true);
        //设置显示动画
        mRc_listdata.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < 10; i++)
        {
            mData.add("RecyclerViewItem：" + (i + 1));
        }
        mAdapter = new RecyclerAdapter(mData);
        mRc_listdata.setAdapter(mAdapter);
        //设置模式切换
        mSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id)
                    {
                        if (position == 0)
                        {
                            mRc_listdata.setLayoutManager(
                                    // 设置为线性布局
                                    new LinearLayoutManager(
                                            mContext));
                        }
                        else if (position == 1)
                        {
                            mRc_listdata.setLayoutManager(
                                    // 设置为表格布局
                                    new GridLayoutManager(
                                            mContext, 3));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
    }

    public void addRecycler(View view)
    {
        mData.add("Recycler");
        int position = mData.size();
        if (position > 0)
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void delRecycler(View view)
    {
        int position = mData.size();
        if (position > 0)
        {
            mData.remove(position - 1);
            mAdapter.notifyDataSetChanged();
        }
    }
}

