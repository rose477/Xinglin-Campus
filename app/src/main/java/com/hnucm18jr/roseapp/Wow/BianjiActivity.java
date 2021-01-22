package com.hnucm18jr.roseapp.Wow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class BianjiActivity extends AppCompatActivity {

    Button mButton;
    EditText mTextView;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianji);
        mButton=findViewById(R.id.fabu);
        mTextView=findViewById(R.id.editText3);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            Dongtai p2 = new Dongtai();
                String [] items = new String[]{"周小吕","许小许","袁小帅","张小宁","张小芝","李小明"};
                int a= (int) Math.floor(Math.random()*items.length);
                String i = items[a];

                p2.setName(i );
                String [] items2 = new String[]{"大三","大四","大一","大二"};
                int a2= (int) Math.floor(Math.random()*items2.length);
                String i2 = items2[a2];
        p2.setGrade(i2);
                String [] items3 = new String[]{"计算机科学与技术","信息管理与信息系统班","医学信息工程班"};
                int a3= (int) Math.floor(Math.random()*items3.length);
                String i3 = items3[a3];
        p2.setZhuanye(i3);
        p2.setContent(mTextView.getText().toString());
        p2.setA(0);
        p2.setB(0);
        p2.setTime(DataUtil.DATA());

        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(BianjiActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        finish();
            }
        });
    }
}