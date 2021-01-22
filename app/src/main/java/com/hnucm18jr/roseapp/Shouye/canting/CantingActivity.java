package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class CantingActivity extends AppCompatActivity {
    ImageView mImageView;
    ConstraintLayout constraintLayout20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canting);
        constraintLayout20=findViewById(R.id.constraintLayout20);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        constraintLayout20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CantingActivity.this,YilouActivity.class);
                startActivity(intent);
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
//                galleryIntent.setType("image/*");//图片
//                startActivityForResult(galleryIntent, 0);
            }
        });
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        if (requestCode == 0 && resultCode == -1) {
//            Uri uri = data.getData();
//
//       mImageView.setImageURI(uri);
//
//
//
//        }
//
//
//
//
//
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}