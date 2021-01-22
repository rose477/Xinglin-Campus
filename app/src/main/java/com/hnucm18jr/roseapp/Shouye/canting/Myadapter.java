package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.annotation.Nullable;
import androidx.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;

import java.util.List;

public class Myadapter extends BaseQuickAdapter<Food1, BaseViewHolder> {

    public Myadapter(@LayoutRes int layoutResId, @Nullable List<Food1> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Food1 item) {
        //可链式调用赋值
        helper.setText(R.id.textView177, item.getName())
                .setImageResource(R.id.imageView3, item.getBgimage());


        int position = helper.getLayoutPosition();
    }
}
