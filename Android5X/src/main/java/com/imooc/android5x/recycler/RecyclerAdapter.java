package com.imooc.android5x.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imooc.android5x.R;

import java.util.List;

/**
 * Created by Mint on 2017/11/29.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<String> mData;
    private OnItemClickListener itemClickListener;

    public RecyclerAdapter(List<String> pData)
    {
        this.mData = pData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.tv_text.setText(mData.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView tv_text;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tv_text = (TextView) itemView;
            tv_text.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(view, getPosition());
            }
        }
    }


}
