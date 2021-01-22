package com.hnucm18jr.roseapp.Wode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hnucm18jr.roseapp.R;


public class WodeFragment extends Fragment {


    RelativeLayout mRelativeLayout;
    RelativeLayout mRelativeLayout2;
    RelativeLayout mRelativeLayout3;
    RelativeLayout mRelativeLayout4;
    RelativeLayout mRelativeLayout5;
    RelativeLayout mRelativeLayout6,mRelativeLayout7;

    ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wode, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniw();
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GerenxinxiActivity.class);
                startActivity(intent);
            }
        });
        mRelativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZhanghaoaquanActivity.class);
                startActivity(intent);
            }
        });
        mRelativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShezhiActivity.class);
                startActivity(intent);
            }
        });
        mRelativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        mRelativeLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        mRelativeLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        mRelativeLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QiandaoActivity.class);
                startActivity(intent);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), chaqinActivity.class);
                startActivity(intent);
            }
        });


    }

    private void iniw() {
        mRelativeLayout = getActivity().findViewById(R.id.infor);
        mRelativeLayout2 = getActivity().findViewById(R.id.safeLayout);
        mRelativeLayout3 = getActivity().findViewById(R.id.settingLayout);
        mRelativeLayout4 = getActivity().findViewById(R.id.helpLayout);
        mRelativeLayout5 = getActivity().findViewById(R.id.abortLayout);
        mRelativeLayout6 = getActivity().findViewById(R.id.quitLayout);
        mImageView=getActivity().findViewById(R.id.xiaoxi);
                mRelativeLayout7 = getActivity().findViewById(R.id.zuzhi);
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(android.R.drawable.ic_dialog_alert);
        normalDialog.setTitle("确定退出？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();

    }
}