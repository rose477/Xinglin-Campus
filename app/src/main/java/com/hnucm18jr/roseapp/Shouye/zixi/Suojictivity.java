package com.hnucm18jr.roseapp.Shouye.zixi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.HomeReceiver;
import com.hnucm18jr.roseapp.R;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Suojictivity extends AppCompatActivity {

    TextView mTextView;
    TextView mTextView2;
    TextView mTextView3;
    Button mButton;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {//重写handleMessage方法
            super.handleMessage(msg);
            String s2 = getIntent().getStringExtra("key");
            String s3= DataUtil.DATA3();
            int res = s2.compareTo(s3);

            mTextView.setText(DataUtil.DATA2());
            mHandler.sendEmptyMessage(1);

            if (res<0){
                finish();
            }

        }
    };
    private static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("Page01 -->onKeyDown: keyCode: " + keyCode);
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            System.out.println("HOME has been pressed yet ...");
            // android.os.Process.killProcess(android.os.Process.myPid());
            Toast.makeText(getApplicationContext(), "HOME 键已被禁用...",
                    Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event); // 不会回到 home 页面
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        setContentView(R.layout.activity_suojictivity);



        HomeReceiver innerReceiver = new HomeReceiver();//注册广播

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(innerReceiver, intentFilter);
        mTextView=findViewById(R.id.time);
        mTextView2=findViewById(R.id.time2);
        mTextView3=findViewById(R.id.time4);
        mButton=findViewById(R.id.tuichu);
        String s=getWeek(DataUtil.DATA());
        String s2 = getIntent().getStringExtra("key");

        mTextView3.setText(DataUtil.DATA3()+"-"+s2);
        mTextView2.setText(DataUtil.DATA()+"星期"+s);


        mHandler.sendEmptyMessage(1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Suojictivity.this)
                        .setTitle("要退出本次锁机吗？")
                        .setMessage("注意,退出会记一次违规，建议在不可抗力情况下再退出")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(Suojictivity.this, "结束锁机", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Suojictivity.this, "继续努力吧~", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();


            }
        });


    }




    // 这个函数主要是为了 屏蔽掉 recent APPS 按键
    @Override
    protected void onPause() {
        super.onPause();
        for (int j = 0; j < 50; j++){
            ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(this.ACTIVITY_SERVICE);
            activityManager.moveTaskToFront(getTaskId(), 0);
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param  pTime     设置的需要判断的时间  //格式如2012-09-08
     *

     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */

//  String pTime = "2012-03-12";
    private String getWeek(String pTime) {


        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }



        return Week;
    }
}