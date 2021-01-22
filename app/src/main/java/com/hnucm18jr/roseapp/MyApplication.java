package com.hnucm18jr.roseapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;



import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.xuexiang.xui.XUI;

import java.lang.reflect.Field;

import cn.bmob.v3.Bmob;


    public class MyApplication extends Application {
    private static MyApplication mInstance = null;
    public BMapManager mBMapManager = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
//        BmobConfig config =new BmobConfig.Builder(this)
//        //设置appkey
//        .setApplicationId("2b2c320653c1acf917bff5ba2985a3ee")
//        //请求超时时间（单位为秒）：默认15s
//        .setConnectTimeout(30)
//        //文件分片上传时每片的大小（单位字节），默认512*1024
//        .setUploadBlockSize(1024*1024)
//        //文件的过期时间(单位为秒)：默认1800s
//        .setFileExpiration(2500)
//        .build();
//        Bmob.initialize(config);
        initEngineManager(this);
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志


        //第一：默认初始化
        Bmob.initialize(this, "2b2c320653c1acf917bff5ba2985a3ee");
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/STKAITI.TTF");

        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, mTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(new MyGeneralListener())) {
            Toast.makeText(
                    MyApplication.getInstance().getApplicationContext(),
                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
        Log.d("ljx", "initEngineManager");
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    public static class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetPermissionState(int iError) {
            // 非零值表示key验证未通过
            if (iError != 0) {
                // 授权Key错误：
                Toast.makeText(
                        MyApplication.getInstance()
                                .getApplicationContext(),
                        "请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: "
                                + iError, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        MyApplication.getInstance()
                                .getApplicationContext(), "欢迎使用",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}