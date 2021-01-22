package com.hnucm18jr.roseapp.Shouye.Xinshengzhinan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hnucm18jr.roseapp.R;
import com.xuexiang.xui.widget.imageview.IconImageView;

public class ZhinanActivity extends AppCompatActivity {

    IconImageView mIconImageView,mIconImageView2,mIconImageView3,mIconImageView4;
    ImageView mImageView,mImageView2,mImageView3;
    ConstraintLayout mConstraintLayout,constraintLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.activity_zhinan);
        mIconImageView=findViewById(R.id.xuexi);
        mIconImageView2=findViewById(R.id.iconImageView);
        mIconImageView3=findViewById(R.id.iconImageView2);
        mIconImageView4=findViewById(R.id.iconImageView3);
        mImageView=findViewById(R.id.imageView13);
        mImageView2=findViewById(R.id.imageView55);
        mImageView3=findViewById(R.id.imageView5);
        mConstraintLayout=findViewById(R.id.all);
        constraintLayout2=findViewById(R.id.constraintLayout28);
        ImageView mImageView5=findViewById(R.id.back);
        mImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ZhinanActivity.this,zhinan1Activity.class);
                startActivity(intent);
            }
        });
        mIconImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ZhinanActivity.this,zhinan2Activity.class);
                startActivity(intent);
            }
        });
        mIconImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhinanActivity.this,zhinan3Activity.class);
                startActivity(intent);
            }
        });
        mIconImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhinanActivity.this,zhinan4Activity.class);
                startActivity(intent);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhinanActivity.this,zhinan5Activity.class);
                startActivity(intent);
            }
        });
        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mImageView2.setVisibility(View.VISIBLE);
               mConstraintLayout.setBackground(getResources().getDrawable(R.drawable.heise));
                constraintLayout2.setVisibility(View.INVISIBLE);
            }
        });
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView2.setVisibility(View.INVISIBLE);
                constraintLayout2.setVisibility(View.VISIBLE);
                mConstraintLayout.setBackground(getResources().getDrawable(R.drawable.lanse));
            }
        });
    }
}