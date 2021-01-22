package com.hnucm18jr.roseapp.Shouye;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hnucm18jr.roseapp.BannerView;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.Daka.DakaActivity;
import com.hnucm18jr.roseapp.Shouye.Fuwu.FuwuActivity;
import com.hnucm18jr.roseapp.Shouye.Shiwuzhao.ShiwuzhaoActivity;
import com.hnucm18jr.roseapp.Shouye.Xinshengzhinan.ZhinanActivity;
import com.hnucm18jr.roseapp.Shouye.canting.CantingActivity;
import com.hnucm18jr.roseapp.Shouye.ershou.ErshouActivity;
import com.hnucm18jr.roseapp.Shouye.xuanzuo.XuanzuoActivity;
import com.hnucm18jr.roseapp.Shouye.zixi.ZixiActivity;
import com.xuexiang.xui.widget.imageview.IconImageView;

import java.util.ArrayList;


public class ShouyeFragment extends Fragment {


    private ArrayList<Integer> images = new ArrayList<>();
    BannerView bannerViewView;
    IconImageView mIconImageView;
    IconImageView mIconImageView2;
    IconImageView mIconImageView3;
    IconImageView mIconImageView4;
    ConstraintLayout mConstraintLayout,constraintLayout3,mConstraintLayout2,mConstraintLayout4,mConstraintLayout5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shouye, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //找到控件
        bannerViewView = (BannerView) getActivity().findViewById(R.id.bannerView);
        mIconImageView =  getActivity().findViewById(R.id.xuexi);
        mIconImageView2 =  getActivity().findViewById(R.id.iconImageView);
        mIconImageView3 =  getActivity().findViewById(R.id.iconImageView3);
        mIconImageView4 =  getActivity().findViewById(R.id.iconImageView2);
        mConstraintLayout=getActivity().findViewById(R.id.canting);
        constraintLayout3=getActivity().findViewById(R.id.constraintLayout3);
        mConstraintLayout2=getActivity().findViewById(R.id.constraintLayout2);
        mConstraintLayout4=getActivity().findViewById(R.id.constraintLayout4);
        mConstraintLayout5=getActivity().findViewById(R.id.constraintLayout5);
        Button button=getActivity().findViewById(R.id.more);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ZhongyiActivity.class);
                startActivity(intent);
            }
        });
        images.add(R.mipmap.a1);
        images.add(R.mipmap.i9);
        images.add(R.mipmap.i10);
        images.add(R.mipmap.image2);
        /**
         * 初始化数据
         * @param list  传入的图片数据，本地图片id，网络图片地址都可以
         * @param defaultPic 默认的图片，在加载失败或者图片尚未加载完全时显示默认图片
         * @param needPoint 是否需要指示点
         * @param pointsUnFocused  指示点的未选中状态图片，不需要指示点设为0即可
         * @param pointsFocused  指示点的选中状态图片，不需要指示点设为0即可
         * @param isLoop 图片是否可以无限循环滑动
         */
        bannerViewView.init(images,R.mipmap.a1,true,R.mipmap.y,R.mipmap.y2,false);


//设置循环播放的间隔时间，不设置就不会循环播放
        bannerViewView.setAutoLoop(2000);
        mIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ZixiActivity.class);
                startActivity(intent);
            }
        });
        mIconImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), XuanzuoActivity.class);
                startActivity(intent);
            }
        });
        mIconImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DakaActivity.class);
                startActivity(intent);
            }
        });
        mIconImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), KebiaoActivity.class);
                startActivity(intent);
            }
        });
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CantingActivity.class);
                Log.i("click","点击了");
                startActivity(intent);
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ErshouActivity.class);

                startActivity(intent);
            }
        });

        mConstraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ShiwuzhaoActivity.class);

                startActivity(intent);
            }
        });
        mConstraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ZhinanActivity.class);

                startActivity(intent);
            }
        });
        mConstraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FuwuActivity.class);

                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        images.clear();
        bannerViewView.removeHandler();//移除掉handler的Callbacks
    }
}