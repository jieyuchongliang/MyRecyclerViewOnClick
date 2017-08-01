package com.demo.android;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/16.
 */
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<RecyclerData> list;

    private int defItem = -1;
    private OnItemListener onItemListener;

    public HomeAdapter(Context context, ArrayList<RecyclerData> list) {
        Logger.t("111").d("list" + list);
        this.context = context;
        this.list = list;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public interface OnItemListener {
        void onClick(MyViewHolder holder, int position);
    }

    public void setDefSelect(int position) {
        this.defItem = position;
//        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(list.get(position).content);
        RecyclerData re = list.get(position);
        if (defItem != -1) {
            if (defItem == position) {
//              点击的位置
                if (re.isSelect == true) {
                    //              选中状态
                    holder.tv.setTextColor(Color.parseColor("#ffffff"));
                    holder.tv.setBackgroundResource(R.drawable.buttonstyle_ba_on);

                } else {
                    holder.tv.setTextColor(Color.parseColor("#000000"));
                    holder.tv.setBackgroundResource(R.drawable.buttonstyle_ba);
                }

            } else {
//              没有点击的位置都变成默认背景
                holder.tv.setTextColor(Color.parseColor("#000000"));
                holder.tv.setBackgroundResource(R.drawable.buttonstyle_ba);
                list.get(position).isSelect = false;
            }
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener != null) {
                    onItemListener.onClick(holder, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);


        }


    }
}
