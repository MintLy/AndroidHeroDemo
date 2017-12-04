package com.ly.test.listview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ly.test.R;
import com.ly.test.tool.Lg;
import com.ly.test.tool.ViewTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestListViewActivity_1 extends Activity
{
    private Context mContext;
    private MyListView_1 mLv_data;
    private MyBaseAdapter_1 mAdapter;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view_1);
        this.mContext = this.getApplicationContext();
        mTouchStop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        initViews();
        List<String> list_data = new ArrayList<String>();
        for (int i = 1; i <= 50; i++)
        {
            list_data.add("第" + i + "项item");
        }
        mAdapter = new MyBaseAdapter_1(this.mContext, list_data);
        mLv_data.setAdapter(mAdapter);
    }

    //最低滑动距离
    private int mTouchStop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private boolean mShow = true;
    private Animator mAnimator;
    private View.OnTouchListener mListviewTouchListener = new View.OnTouchListener()
    {

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
//                    Lg.d("按下");
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
//                    Lg.e("移动");
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchStop)
                    {
                        direction = 0;//down
                    }
                    else if (mFirstY - mCurrentY > mTouchStop)
                    {
                        direction = 1;//up
                    }
                    if (direction == 1)
                    {
                        if (mShow)
                        {
                            toolbarAnim(1);//show
                            mShow = !mShow;
                        }
                    }
                    else if (direction == 0)
                    {
                        if (!mShow)
                        {
                            toolbarAnim(0);//hide
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
//                    Lg.d("抬起");
                    break;
                default:
                    break;
            }
            return false;
        }
    };


    private AbsListView.OnScrollListener mListviewScrollListener = new AbsListView.OnScrollListener()
    {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState)
        {
            switch (scrollState)
            {
                case SCROLL_STATE_IDLE:
//                    Lg.d("滑动停止");
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
//                    Lg.d("滑动中");
                    break;
                case SCROLL_STATE_FLING:
//                    Lg.d("惯性滑动");
                    break;

                default:
                    break;
            }
        }

        /**
         *
         * @param view
         * @param firstVisibleItem  当前看见的第一项的id(从0开始)
         * @param visibleItemCount  当前能看见的项的总数
         * @param totalItemCount    整个ListView总项
         */
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
        {
            //滑动时一直调用
//            Lg.i("onScroll   firstVisibleItem:" + firstVisibleItem);
//            Lg.i("onScroll   visibleItemCount:" + visibleItemCount);
//            Lg.i("onScroll   totalItemCount:" + totalItemCount);
        }
    };
    private AdapterView.OnItemClickListener mListviewItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Lg.d("onItemClick position:" + position);
            mAdapter.setClickItemPosition(position - 1);
            mAdapter.notifyDataSetChanged();
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initViews()
    {
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        mLv_data = (MyListView_1) findViewById(R.id.lv_data);
        // TODO 设置ListView 分割线
        // 注意 ListView.setDivider 函数会重新设置分割线的高度为-1
        // 对应 android:divider="@android:color/darker_gray"
        mLv_data.setDivider(getDrawable(android.R.color.darker_gray));
        // 对应 android:dividerHeight="10dp"
        mLv_data.setDividerHeight(ViewTool.dip2px(mContext, 10));
        // TODO 隐藏ListView滚动条
        // 对应 layout.xml android:scrollbars="none"
//        mLv_data.setVerticalScrollBarEnabled(false);
        // TODO 取消ListView item点击效果
        mLv_data.setSelector(android.R.color.transparent);
        // TODO 处理空ListView
        // 并非真正意义上的处理空ListView,只不过是隐藏了ListView控件,把位置展示了ListView的下一个控件罢了
        View tv_empty = createEmptyListView();
        ((ViewGroup) mLv_data.getParent()).addView(tv_empty);
        mLv_data.setEmptyView(tv_empty);
        // ListView 滑动监听
        mLv_data.setOnTouchListener(mListviewTouchListener);
        mLv_data.setOnScrollListener(mListviewScrollListener);

        mLv_data.setOnItemClickListener(mListviewItemClickListener);
        createHeadView();
    }

    private void toolbarAnim(int flag)
    {
        if (mAnimator != null && mAnimator.isRunning())
        {
            mAnimator.cancel();
        }
        if (flag == 0)
        {
            mAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), 0);
        }
        else
        {
            mAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), -mToolbar.getHeight());
        }
        mAnimator.start();
    }


    @NonNull
    private View createEmptyListView()
    {
        TextView tv_empty = new TextView(mContext);
        tv_empty.setBackgroundColor(Color.RED);
        tv_empty.setGravity(Gravity.CENTER);
        tv_empty.setText("没有数据啦");
        tv_empty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return tv_empty;
    }

    private void createHeadView()
    {
        // 给 ListView 添加一个 HeaderView
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                // 高度根据屏幕不一样，大小不一
                // （此处可以使用 R.dimen.abc_action_bar_default_height_material）
                (int) getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material)));
        mLv_data.addHeaderView(header);
    }

    //按钮事件
    public void toTop(View v)
    {
        // TODO ListView 显示第 N 项
        mLv_data.setSelection(0);
    }

    public void addItem(View v)
    {
        // TODO ListView 动态添加项
        // 注意 Adapter里面的数据List对象不能变化
        mAdapter.addItem("我是新增项:" + new Random().nextInt(100));
        mAdapter.notifyDataSetChanged();
    }

    public void removeItem(View v)
    {
        // TODO ListView 动态删除项
        // 注意 Adapter里面的数据List对象不能变化
        mAdapter.removeItem();
        mAdapter.notifyDataSetChanged();
    }
}
