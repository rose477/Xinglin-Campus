package com.hnucm18jr.roseapp.Shouye.ershou;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;

import java.util.List;

public class Eradapyer2 extends BaseQuickAdapter<Goods2, BaseViewHolder> {
    public Eradapyer2(@LayoutRes int layoutResId, @Nullable List<Goods2> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods2 item) {
        //可链式调用赋值
        helper.setText(R.id.textView18, item.getTitle())
                .setText(R.id.textView19, item.getPrice())
                .setText(R.id.textView20, item.getName())
                .setText(R.id.textView21, item.getTime());

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }


}
