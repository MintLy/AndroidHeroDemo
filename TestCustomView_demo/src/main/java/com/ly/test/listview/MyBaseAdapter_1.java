package com.ly.test.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.test.R;
import com.ly.test.tool.Lg;

import java.util.List;

/**
 * Created by 20170117 on 2017/10/14.
 */

public class MyBaseAdapter_1 extends BaseAdapter
{
    private Context mContext;
    private List<String> mList_data;
    private int mClickItemPosition;

    public MyBaseAdapter_1(Context pContext, List<String> pList_data)
    {
        this.mContext = pContext;
        this.mList_data = pList_data;
    }

    public void addItem(String items)
    {
        mList_data.add(items);
    }

    public void removeItem()
    {
        if (mList_data.size() > 0)
        {
            mList_data.remove(mList_data.size() - 1);
        }
    }

    public void setClickItemPosition(int mClickItemPosition)
    {
        this.mClickItemPosition = mClickItemPosition;
    }

    @Override
    public int getCount()
    {
        return mList_data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList_data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (mClickItemPosition == position)
        {
            Lg.d("getView position :" + position);
            ImageView iv = new ImageView(mContext);
            iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher));
            return iv;
        }
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_test_list_view1_item, null);
            holder = new ViewHolder();
            holder.tv_lv_item_title = (TextView) convertView.findViewById(R.id.tv_lv_data_item_title);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        if (holder == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_test_list_view1_item, null);
            holder = new ViewHolder();
            holder.tv_lv_item_title = (TextView) convertView.findViewById(R.id.tv_lv_data_item_title);
            convertView.setTag(holder);
        }
        holder.tv_lv_item_title.setText(mList_data.get(position));

        return convertView;
    }

    class ViewHolder
    {
        TextView tv_lv_item_title;
    }
}
