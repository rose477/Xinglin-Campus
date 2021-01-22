package com.hnucm18jr.roseapp.Xinwen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hnucm18jr.roseapp.BannerView;
import com.hnucm18jr.roseapp.R;

import java.util.ArrayList;


public class XinwenFragment extends Fragment {

    ConstraintLayout mConstraintLayout1;
    ConstraintLayout mConstraintLayout2;
    ConstraintLayout mConstraintLayout3;
    ConstraintLayout mConstraintLayout4;

    private BannerView bannerViewView;
    WebView mWebView;
    private ArrayList<Integer> images = new ArrayList<>(); //设置数据源
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_xinwen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniw();
//找到控件
        bannerViewView = (BannerView) getActivity().findViewById(R.id.bannerView);
        mWebView=getActivity().findViewById(R.id.xinwen);
        mWebView.loadUrl("https://news.hnucm.edu.cn/");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        mWebView.clearCache(true);
        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        mWebView.clearHistory();
        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        mWebView.clearFormData();
        WebSettings webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);//js开关默认不打开，网页中的所有js方法就无法执行，如果我们把这个方法设置为ture，网页中搞得js方法才可以执行
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(false); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        images.add(R.mipmap.i4);
        images.add(R.mipmap.i3);
        images.add(R.mipmap.i1);
        images.add(R.mipmap.i5);
        //设置点击事件监听
        bannerViewView.setBannerOnClickListener(new BannerView.BannerOnClickListener() {
            @Override
            public void onClick(int position) {
            }
        });
        bannerViewView.init(images,R.mipmap.a1,true,R.mipmap.y,R.mipmap.y2,false);


//设置循环播放的间隔时间，不设置就不会循环播放
        bannerViewView.setAutoLoop(2000);
        mConstraintLayout1.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
        mConstraintLayout2.setSelected(false);
        mConstraintLayout3.setSelected(false);
        mConstraintLayout4.setSelected(false);
        mConstraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout1.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout4.setSelected(false);
            }
        });
        mConstraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout2.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout1.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout4.setSelected(false);
            }
        });
        mConstraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout3.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout1.setSelected(false);
                mConstraintLayout4.setSelected(false);
            }
        });
        mConstraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout4.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout1.setSelected(false);
            }
        });

    }

    private void iniw() {
        mConstraintLayout1=getActivity().findViewById(R.id.constraintLayout7);
        mConstraintLayout2=getActivity().findViewById(R.id.constraintLayout6);
        mConstraintLayout3=getActivity().findViewById(R.id.constraintLayout8);
        mConstraintLayout4=getActivity().findViewById(R.id.constraintLayout9);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        images.clear();
        bannerViewView.removeHandler();//移除掉handler的Callbacks
    }

}