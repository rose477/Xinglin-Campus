package com.hnucm18jr.roseapp.Shouye.xuanzuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm18jr.roseapp.R;
import com.xuexiang.xui.widget.alpha.XUIAlphaTextView;
import com.xuexiang.xui.widget.layout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class XuanzuoActivity extends AppCompatActivity {

//    ZhuxiaoquFragment mZhuxiaoquFragment=new ZhuxiaoquFragment();
//    DongtangFragment mDongtangFragment=new DongtangFragment();
//    QitaFragment mQitaFragment=new QitaFragment();
XUIAlphaTextView mXUIAlphaTextView;
    ImageView mImageView;
    ExpandableLayout expandableLayout1;
    XUIAlphaTextView mXUIAlphaTextView2;
    ExpandableLayout expandableLayout2;
    XUIAlphaTextView mXUIAlphaTextView3;
    ExpandableLayout expandableLayout3;
    ConstraintLayout mConstraintLayout;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.activity_xuanzuo);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mZhuxiaoquFragment).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,mDongtangFragment).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout3,mQitaFragment).commit();
        mXUIAlphaTextView=findViewById(R.id.expand_button2);
        mXUIAlphaTextView2=findViewById(R.id.expand_button3);
        mXUIAlphaTextView3=findViewById(R.id.expand_button4);
        mTextView=findViewById(R.id.weizi);
        expandableLayout2=findViewById(R.id.expandable_layout_4);
        expandableLayout1=findViewById(R.id.expandable_layout_1);
        expandableLayout3=findViewById(R.id.expandable_layout_5);


        //注册EventBus
        EventBus.getDefault().register(this);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mXUIAlphaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableLayout1.isExpanded()) {
                    expandableLayout1.collapse();
                }  else {
                    expandableLayout1.expand();

                }

            }
        });

        mConstraintLayout=findViewById(R.id.x4);
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(XuanzuoActivity.this, ZuoweiActivity.class);
                startActivity(intent);
            }
        });
        mXUIAlphaTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableLayout2.isExpanded()) {
                    expandableLayout2.collapse();
                }  else {
                    expandableLayout2.expand();

                }

            }
        });
        mXUIAlphaTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableLayout3.isExpanded()) {
                    expandableLayout3.collapse();
                }  else {
                    expandableLayout3.expand();

                }

            }
        });

    }
    //方法在主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(String name){
        mTextView.setText(name);
    }

}