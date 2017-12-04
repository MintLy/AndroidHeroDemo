package com.ly.test.listview.QQ;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.ly.test.R;
import com.ly.test.listview.MyListView_1;
import com.ly.test.tool.ViewTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestListViewActivity_2 extends Activity
{
    private Context mContext;
    private MyListView_1 mLv_data;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view_2);
        this.mContext = this.getApplicationContext();
        initViews();
        List<QQ> list_data = new ArrayList<QQ>();
        Random random = new Random();
        for (int i = 0; i < 50; i++)
        {
            QQ qq = new QQ();
            int rr = random.nextInt(2);
            qq.setType(rr);
            qq.setMsg("第" + (i + 1) + "条消息");
            list_data.add(qq);
        }
        MyBaseAdapter_2 mAdapter = new MyBaseAdapter_2(mContext, list_data);
        mLv_data.setAdapter(mAdapter);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initViews()
    {
        mLv_data = (MyListView_1) findViewById(R.id.lv_data);
        // TODO 设置ListView 分割线
        // 注意 ListView.setDivider 函数会重新设置分割线的高度为-1
        // 对应 android:divider="@android:color/darker_gray"
        mLv_data.setDivider(getDrawable(android.R.color.darker_gray));
        // 对应 android:dividerHeight="10dp"
        mLv_data.setDividerHeight(ViewTool.dip2px(mContext, 10));
        // TODO 隐藏ListView滚动条
        // 对应 layout.xml android:scrollbars="none"
        mLv_data.setVerticalScrollBarEnabled(false);
        // TODO 取消ListView item点击效果
        mLv_data.setSelector(android.R.color.transparent);
    }

}
