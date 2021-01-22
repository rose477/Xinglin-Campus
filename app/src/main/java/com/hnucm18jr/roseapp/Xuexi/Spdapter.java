package com.hnucm18jr.roseapp.Xuexi;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.Shiwuzhao.Shiwu;

import java.util.List;


public class Spdapter extends BaseQuickAdapter<Suoping, BaseViewHolder> {
    public Spdapter(@LayoutRes int layoutResId, @Nullable List<Suoping> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Suoping item) {
        //可链式调用赋值
        helper.setText(R.id.textView44, item.getName())
                .setText(R.id.textView45, item.getTime());

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
