package com.hnucm18jr.roseapp.Shouye.ershou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TaoActivity extends AppCompatActivity {

    ImageView mImageView;
    EditText mEditText,mEditText2;
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        setContentView(R.layout.activity_tao);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mEditText=findViewById(R.id.editText2);
        mEditText2=findViewById(R.id.bott);
        mConstraintLayout=findViewById(R.id.constraintLayout27);
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Goods2 p2 = new Goods2();
              p2.setTitle(mEditText.getText().toString());
        p2.setPrice(mEditText2.getText().toString());
        p2.setName("猪猪侠");
        p2.setTime(DataUtil.DATA());

        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(TaoActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TaoActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        finish();
            }
        });
    }
}