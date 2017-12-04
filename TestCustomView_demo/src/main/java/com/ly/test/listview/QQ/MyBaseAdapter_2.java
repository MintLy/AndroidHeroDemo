package com.ly.test.listview.QQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.test.R;

import java.util.List;

/**
 * Created by 20170117 on 2017/10/14.
 */

public class MyBaseAdapter_2 extends BaseAdapter
{
    private Context mContext;
    private List<QQ> mList_data;
    private LayoutInflater mInflater;

    public MyBaseAdapter_2(Context pContext, List<QQ> pList_data)
    {
        this.mContext = pContext;
        this.mList_data = pList_data;
        this.mInflater = LayoutInflater.from(pContext);
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
    public int getItemViewType(int position)
    {
        QQ qq = this.mList_data.get(position);
        return qq.getType();
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            holder = new ViewHolder();
            //判断item类型
            if (getItemViewType(position) == 0)
            {
                convertView = mInflater.inflate(R.layout.activity_test_list_view2_item1, null);
            }
            else if (getItemViewType(position) == 1)
            {
                convertView = mInflater.inflate(R.layout.activity_test_list_view2_item2, null);
            }
            holder.tv_lv_item_title = (TextView) convertView.findViewById(R.id.tv_lv_data_item_title);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_lv_item_title.setText(mList_data.get(position).getMsg());
        holder.tv_lv_item_title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, mList_data.get(position).getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        TextView tv_lv_item_title;
    }
}
