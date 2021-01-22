package com.hnucm18jr.roseapp.Xuexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.R;

public class SuopingActivity extends AppCompatActivity {

    Button mButton;

    TextView mTextView;
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suoping);
        ImageView mImageView=findViewById(R.id.back);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mEditText=findViewById(R.id.editText6);
        mButton=findViewById(R.id.shangke);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((mEditText.getText().toString()).equals("请输入")){
                    Toast.makeText(SuopingActivity.this,"请输入控屏时长",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent =new Intent(SuopingActivity.this,LookActivity.class);
                    intent.putExtra("time",mEditText.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}