package com.hnucm18jr.roseapp.Shouye.zixi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm18jr.roseapp.LineChart;
import com.hnucm18jr.roseapp.R;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class ZixiActivity extends AppCompatActivity {
    LineChart mLineChart;
    Calendar calendar = Calendar.getInstance();
    ImageView mImageView;
    TextView mTextView;
    TextView mTextView2;
    TextView mTextView3;
    Button mButton;
    TitanicTextView psp;

    ConstraintLayout mConstraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.activity_zixi);

        mTextView=findViewById(R.id.suotime);
        mTextView2=findViewById(R.id.suotime2);
        mTextView3=findViewById(R.id.suotime1);
        psp=findViewById(R.id.psps);
        new Titanic().start(psp);
        mButton=findViewById(R.id.kaishi);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
            }
        });

        mLineChart= findViewById(R.id.lineChart);
        mConstraintLayout=findViewById(R.id.suoji);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("TIME","-------"+month+day);

        Map<String ,Float> map=new LinkedHashMap<>() ;//一定要用有序的Map
        int min=3;
        int max=10;
        Random random = new Random();
        float num1 = random.nextInt(max)%(max-min+1) + min;
        float num2 = random.nextInt(max)%(max-min+1) + min;
        float num3 = random.nextInt(max)%(max-min+1) + min;
        float num4 = random.nextInt(max)%(max-min+1) + min;
        float num5 = random.nextInt(max)%(max-min+1) + min;
        float num6 = random.nextInt(max)%(max-min+1) + min;
        float num7 = random.nextInt(max)%(max-min+1) + min;
        if ((day-6)<=0){

            String s1= (month-1) + "." + (30+day-6);
            map.put(s1,num1);
        }else {
            String s1= month + "." + (day-6);
            map.put(s1,num1);
        }
        if ((day-5)<=0){

            String s2= (month-1) + "." + (30+day-5);

            map.put(s2,num2);
        }else {
            String s2= month + "." + (day-5);

            map.put(s2,num2);
        }
        if ((day-4)<=0){

            String s3= (month-1) + "." + (30+day-4);

            map.put(s3,num3);
        }else {
            String s3= month + "." + (day-4);

            map.put(s3,num3);
        }
        if ((day-3)<=0){

            String s4= (month-1) + "." + (30+day-3);

            map.put(s4,num4);
        }else {
            String s4= month + "." + (day-3);

            map.put(s4,num4);
        }
        if ((day-2)<=0){

            String s5= (month-1) + "." + (30+day-2);

            map.put(s5,num5);
        }else {
            String s5= month + "." + (day-2);

            map.put(s5,num5);
        }
        if ((day-1)<=0){

            String s6= (month-1) + "." + (30+day-1);

            map.put(s6,num6);
        }else {
            String s6= month + "." + (day-1);

            map.put(s6,num6);
        }





        String s7= month + "." + day;






        map.put(s7,num7);
        String[] a=new String[]{"06","12","18","24"};

        mLineChart.startDraw(map,a,"小时",40,10);//map为横坐标数据和点数据，a为纵坐标刻度（为数字类型的字符串，m/s为纵坐标单位,40为坐标轴距离边界的位置dp，14是坐标轴字体大小）

        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(ZixiActivity.this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT,  mTextView3, Calendar.getInstance());



                new Handler().postDelayed(new Runnable() {
     @Override
     public void run() {
    //要延时的程序 
         mButton.setVisibility(View.VISIBLE);
         mTextView2.setText(null);
         mTextView.setText("锁屏时间至：");
 }
                },1000); //8000为毫秒单位

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZixiActivity.this, Suojictivity.class);
                intent.putExtra("key",mTextView3.getText());
                Log.i("Main",mTextView3.getText()+"");
                startActivity(intent);
                finish();

            }
        });

    }
    public void showTimePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new TimePickerDialog(context
                , themeResId
                , (view, hourOfDay, minute) -> tv.setText(String.format("%d:%02d", hourOfDay, minute))
                // 设置初始日期
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , true)
                .show();




    }
}