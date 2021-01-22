package com.hnucm18jr.roseapp.Xuexi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.Shiwuzhao.Shiwu;
import com.hnucm18jr.roseapp.Shouye.ershou.Eradapter;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LookActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Suoping> datas=new ArrayList<>();;
    private Spdapter adapter;
    PullToRefreshView mPullToRefreshView;
    int j=60;

    TextView mTextView,mTextView1,mTextView2,mTextView3;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int a = 39;
           
            if (j<0){

                
                a--;
                j=60;
            }else {
                j--;

            }
            adapter.notifyDataSetChanged();

            mTextView3.setText(String.valueOf(a)+"分"+String.valueOf(j)+"秒");
            mHandler.sendEmptyMessageDelayed(1,1000);//3秒后发送消息

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        ImageView mImageView=findViewById(R.id.back);
       recyclerView=findViewById(R.id.ketangrecler);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mTextView=findViewById(R.id.textView43);
        mTextView1=findViewById(R.id.textView41);
        mTextView2=findViewById(R.id.textView36);
        mTextView3=findViewById(R.id.textView46);
        BmobQuery<Suoping> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("name ",null);
        categoryBmobQuery.findObjects(new FindListener<Suoping>() {
            @Override
            public void done(List<Suoping> object, BmobException e) {
                if (e == null) {
                    StringBuffer stringBuffer=new StringBuffer();//StringBuffer本质上是一个字符数组
                    for (int i=0;i<object.size();i++){
                        Suoping model;

                        String b=String.valueOf(object.get(i).getName()+"同学已进入课堂");
                        String f=String.valueOf(object.get(i).getTime());
                        model = new Suoping();
                        model.setName(b);
                        model.setTime(f);
                        datas.add(model);
                        stringBuffer.append(object.get(i).getName()+" ");
                    }

                    mTextView1.setText("已进入课堂"+"("+String.valueOf(object.size())+")");
                    mTextView2.setText("未进入课堂"+"("+String.valueOf(43-object.size())+")");
                    mTextView.setText(stringBuffer);
                    adapter.notifyDataSetChanged();



                } else {
                    Log.e("BMOB", e.toString());

                }
            }
        });




        mHandler.sendEmptyMessage(1);

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        datas.clear();

                        BmobQuery<Suoping> categoryBmobQuery = new BmobQuery<>();
                        categoryBmobQuery.addWhereEqualTo("name ",null);
                        categoryBmobQuery.findObjects(new FindListener<Suoping>() {
                            @Override
                            public void done(List<Suoping> object, BmobException e) {
                                if (e == null) {

                                    for (int i=0;i<object.size();i++){
                                        Suoping model;

                                        String b=String.valueOf(object.get(i).getName());
                                        String f=String.valueOf(object.get(i).getTime());
                                        model = new Suoping();
                                        model.setName(b);
                                        model.setTime(f);
                                        datas.add(model);
                                    }


                                    adapter.notifyDataSetChanged();



                                } else {
                                    Log.e("BMOB", e.toString());

                                }
                            }
                        });

                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new Spdapter(R.layout.suoping, datas);

        recyclerView.scrollToPosition(adapter.getItemCount() - 1);

        //给RecyclerView设置适配器

        recyclerView.setAdapter(adapter);
        datas.clear();
    }
}