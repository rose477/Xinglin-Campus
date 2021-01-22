package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;

import java.util.List;


public class Caiadpter extends BaseQuickAdapter<Cai, BaseViewHolder> {

    public Caiadpter(@LayoutRes int layoutResId, @Nullable List<Cai> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Cai item) {
        //可链式调用赋值
        helper.setText(R.id.cai_center, item.getName())
                .setImageResource(R.id.top_cai, item.getImage())
                .addOnClickListener(R.id.imageView15)
                .addOnClickListener(R.id.imageView16)
                .setText(R.id.price,String.valueOf(item.getPrice()));

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
