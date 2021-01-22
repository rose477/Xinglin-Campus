package com.hnucm18jr.roseapp.Shouye.Shiwuzhao;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;

import java.util.List;

public class swzldapter2 extends BaseQuickAdapter<Shiwu2, BaseViewHolder> {
    public swzldapter2(@LayoutRes int layoutResId, @Nullable List<Shiwu2> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shiwu2 item) {
        //可链式调用赋值
        helper.setText(R.id.textView18, item.getTitle())
                .setText(R.id.textView19, item.getMiaoshu())
                .setText(R.id.textView20, item.getUser())
                .setText(R.id.textView21, item.getTime())
                .setImageResource(R.id.thing, item.getImage());

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
