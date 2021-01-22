package com.hnucm18jr.roseapp.Shouye.ershou;

import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.canting.Food1;

import java.util.List;

public class Eradapter extends BaseQuickAdapter<Goods,BaseViewHolder> {
    public Eradapter(@LayoutRes int layoutResId, @Nullable List<Goods> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item) {
        //可链式调用赋值
        helper.setText(R.id.textView18, item.getTitle())
                .setText(R.id.textView19, item.getPrice())
                .setText(R.id.textView20, item.getUser())
                .setText(R.id.textView21, item.getTime())
                .setImageResource(R.id.thing, item.getImage());


        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
