package com.hnucm18jr.roseapp.Wode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.canting.DingdanActivity;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class chaqinActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mTextView,mTextView1,mTextView2,mTextView3,mTextView4;
    int i=0;

    int x=0;
    int j=43;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int a=0;

            if (j>5){
                 a=DATA123();
            }else {
                 a=0;
            }
            x+=a;
            j-=a;
            if (j<=5){
                mTextView4.setVisibility(View.INVISIBLE);
                j=5;
            }else {

            }
            mTextView1.setText("未签到学生名单 "+"("+String.valueOf(j)+"人)");
            mTextView2.setText(String.valueOf(x));
            mHandler.sendEmptyMessageDelayed(1,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaqin);
        mImageView=findViewById(R.id.back);
        mTextView1=findViewById(R.id.bottom);
        mTextView2=findViewById(R.id.renshu4);
        mTextView3=findViewById(R.id.renshu2);
        mTextView4=findViewById(R.id.liujie);

        Button button=findViewById(R.id.button6);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        NiceSpinner niceSpinner=findViewById(R.id.askBuy_nice_spinner1);
        List<String> dataset=new LinkedList<>(Arrays.asList("","2017级计算机科学与技术一班", "2017级计算机科学与技术二班","2017级信息管理与信息系统班","2017级医学信息工程班","2018级计算机科学与技术一班", "2018级计算机科学与技术二班","2018级信息管理与信息系统班","2018级医学信息工程班"
        ,"2019级计算机科学与技术一班", "2019级计算机科学与技术二班","2019级信息管理与信息系统班","2019级医学信息工程班","2020级计算机科学与技术一班", "2020级计算机科学与技术二班","2020级信息管理与信息系统班","2020级医学信息工程班"));
        niceSpinner.attachDataSource(dataset);
        NiceSpinner niceSpinner2=findViewById(R.id.askBuy_nice_spinner2);
        List<String> dataset2=new LinkedList<>(Arrays.asList("","信息科学与工程学院", "中医学院","针灸推拿学院","药学院","第一中医临床学院","第二中医临床学院","中西医结合学院","医学院","湘杏学院","人文与管理学院","护理学院"));
        niceSpinner2.attachDataSource(dataset2);
        mTextView=findViewById(R.id.mindan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==0){
                    i++;
                    button.setText("智能查寝中");
                    mTextView3.setText("43");
                    mTextView.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessage(1);
                }else {
                    Toast.makeText(chaqinActivity.this,"正在智能查寝中",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public static int DATA123() {//int
        int min = 1;
        int max = 4;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
}