package com.hnucm18jr.roseapp.Xuexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class KechengActivity extends AppCompatActivity {

    Button mButton;
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecheng);
        ImageView  mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mEditText=findViewById(R.id.editText4);
        mButton=findViewById(R.id.button3);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suoping p2 = new Suoping();
              p2.setName(mEditText.getText().toString());

        p2.setTime(DataUtil.DATA3());

        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
                Intent intent =new Intent(KechengActivity.this,Suoji2Activity.class);
                startActivity(intent);

            }
        });

    }
}