package com.hnucm18jr.roseapp.Shouye.Shiwuzhao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.BuyFragment;
import com.hnucm18jr.roseapp.Shouye.ershou.ErshouActivity;
import com.hnucm18jr.roseapp.Shouye.ershou.SellFragment;
import com.hnucm18jr.roseapp.Shouye.ershou.TaoActivity;

public class ShiwuzhaoActivity extends AppCompatActivity {
    ImageView mImageView;
    ConstraintLayout mConstraintLayout,mConstraintLayout2;
    HuanFragment mBuyFragment=new HuanFragment();
        Zhaoragment mSellFragment=new Zhaoragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.activity_shiwuzhao);
        mImageView=findViewById(R.id.back);
        mConstraintLayout=findViewById(R.id.constraintLayout30);
        mConstraintLayout2=findViewById(R.id.constraintLayout29);
        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShiwuzhaoActivity.this,"暂未开放",Toast.LENGTH_SHORT).show();

            }
        });
        //跳转到 FromPointToPoint 活动
        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShiwuzhaoActivity.this,"暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,mBuyFragment).commit();
        mConstraintLayout.setSelected(true);
        mConstraintLayout2.setSelected(false);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout.setSelected(true);
                mConstraintLayout2.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mBuyFragment).commit();
            }
        });
        mConstraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout2.setSelected(true);
                mConstraintLayout.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mSellFragment).commit();


            }
        });
    }
}