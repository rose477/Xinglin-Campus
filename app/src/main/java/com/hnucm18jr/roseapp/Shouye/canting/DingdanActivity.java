package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.FirstActivity;
import com.hnucm18jr.roseapp.R;

public class DingdanActivity extends AppCompatActivity {

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        ImageView mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mButton=findViewById(R.id.button111);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DingdanActivity.this,"支付成功~",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}