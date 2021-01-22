package com.hnucm18jr.roseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

public class LoginActivity extends AppCompatActivity {

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);


            SharedPreferences sp= getSharedPreferences("user120", MODE_PRIVATE);
            boolean islogin = sp.getBoolean("islogin",false);//如果读不到文件或者当key不存在时 返回false
            Log.i("intent111", String.valueOf(islogin));

            //判断用户是否登录过

            if (islogin) {  //如果登录过 跳转到 MainActivity
                Intent intent=new Intent(LoginActivity.this,FirstActivity.class);
                Log.i("intent111","跑起来了");
                startActivity(intent);
                finish();
            }else { //如果没有登录过 跳转到 LoginActivity
                Intent intent=new Intent(LoginActivity.this,LoginActivity2.class);
                Log.i("intent111","跑不起来了");
                startActivity(intent);
                finish();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置默认字体为华文行楷，这里写你的字体库

        setContentView(R.layout.activity_main);
        mHandler.sendEmptyMessageDelayed(1,2000);//3秒后发送消息
    }

}