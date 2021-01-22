package com.hnucm18jr.roseapp.Xuexi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm18jr.roseapp.R;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LaoshiActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laoshi);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        NiceSpinner niceSpinner=findViewById(R.id.askBuy_nice_spinner1);
        List<String> dataset=new LinkedList<>(Arrays.asList("男", "女"));
        niceSpinner.attachDataSource(dataset);
        NiceSpinner niceSpinner2=findViewById(R.id.askBuy_nice_spinner2);
        List<String> dataset2=new LinkedList<>(Arrays.asList("信息科学与工程学院", "中医学院","针灸推拿学院","药学院","第一中医临床学院","第二中医临床学院","中西医结合学院","医学院","湘杏学院","人文与管理学院","护理学院"));
        niceSpinner2.attachDataSource(dataset2);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout5);
        final TextView textView = findViewById(R.id.result);
        final TextView textView2 = findViewById(R.id.result2);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("空闲中");
                textView2.setText("办公室");
            }
        });
    }
}