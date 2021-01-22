package com.hnucm18jr.roseapp.Wow;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;

import java.util.List;


public class Dongtaiadpter extends BaseQuickAdapter<Dongtai, BaseViewHolder> {
    public Dongtaiadpter(@LayoutRes int layoutResId, @Nullable List<Dongtai> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Dongtai item) {
        //可链式调用赋值
        helper.setText(R.id.textView52, item.getName())
                .setText(R.id.textView53, item.getGrade())
                .setText(R.id.textView54, item.getZhuanye())
                .setText(R.id.textView55, item.getContent())
                .setText(R.id.dongtai3,String.valueOf(item.getA()))
                .setText(R.id.dongtai6,String.valueOf(item.getB()))
                .setText(R.id.dttiem,item.getTime());



        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
