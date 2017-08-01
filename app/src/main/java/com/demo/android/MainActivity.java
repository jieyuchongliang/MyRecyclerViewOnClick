package com.demo.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class MainActivity extends Activity {
    //
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      初始化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//      RecyclerView设置展示的的样式（listView样子，gridView样子，瀑布流样子）
//        listView纵向滑动样子
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);


//      获取数据，向适配器传数据，绑定适配器
        final ArrayList<RecyclerData> datas = initData();
        final HomeAdapter honmeAdapter = new HomeAdapter(MainActivity.this,datas);
        mRecyclerView.setAdapter(honmeAdapter);
        honmeAdapter.setOnItemListener(new HomeAdapter.OnItemListener() {
            @Override
            public void onClick(HomeAdapter.MyViewHolder holder, int position) {
                honmeAdapter.setDefSelect(position);

                if(datas.get(position).isSelect) {
//                  选中状态点击之后改为未选中
                    datas.get(position).isSelect=false;
                    honmeAdapter.notifyDataSetChanged();
                    Log.e("111","选中后放开的内容"+datas.get(position).content);
                }else {
//                  未选中状态点击之后给为选中
                    datas.get(position).isSelect=true;
                    honmeAdapter.notifyDataSetChanged();
                    Log.e("111", "选中的内容"+datas.get(position).content);
                }
            }
        });

    }

    /**
     * 编写一套假数据
     */
    protected ArrayList<RecyclerData> initData() {
        ArrayList<RecyclerData> mDatas = new ArrayList<RecyclerData>();
        for (int i = 0; i < 31; i++) {
            if(i<10) {
                RecyclerData recyclerData=new RecyclerData();
                recyclerData.content="内容"+i;
                mDatas.add(recyclerData);
            }else{
                RecyclerData recyclerData=new RecyclerData();
                recyclerData.content="大于10的内容"+i;
                mDatas.add(recyclerData);
            }

        }
        return  mDatas;
    }

}