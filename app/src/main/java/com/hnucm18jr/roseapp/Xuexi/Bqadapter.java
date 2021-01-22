package com.hnucm18jr.roseapp.Xuexi;

import android.opengl.Visibility;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;

import java.util.List;

public class Bqadapter extends BaseQuickAdapter<Bianqian, BaseViewHolder> {

    public Bqadapter(@LayoutRes int layoutResId, @Nullable List<Bianqian> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bianqian item) {
        //可链式调用赋值
        helper.setText(R.id.context, item.getContext())
                .setText(R.id.bqtime, item.getTime());
        int position = helper.getLayoutPosition();

    }


}
