package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hnucm18jr.roseapp.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CaiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Cai> datas=new ArrayList<>();;
    private Caiadpter adapter;
    TextView mTextView,mTextView1,mTextView2;
    int i=0;
    int j =0;
    TextView tv_title;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai);
        ImageView mImageView=findViewById(R.id.back);
        mTextView1=findViewById(R.id.textView48);
        mTextView2=findViewById(R.id.textView49);
        mButton=findViewById(R.id.button5);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CaiActivity.this,DingdanActivity.class);
                startActivity(intent);

            }
        });
                mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        Thread thread1 = new Thread(new Runnable() {//方法2
            @Override
            public void run() {
                //子线程
                BmobQuery<Cai> categoryBmobQuery = new BmobQuery<>();
                categoryBmobQuery.addWhereEqualTo("name ",null);
                categoryBmobQuery.findObjects(new FindListener<Cai>() {
                    @Override
                    public void done(List<Cai> object, BmobException e) {
                        if (e == null) {

                            for (int i=0;i<object.size();i++){
                                Cai model;
                                int a=Integer.valueOf(object.get(i).getImage());
                                String b=String.valueOf(object.get(i).getName());
                                int c=object.get(i).getPrice();
                                model = new Cai();
                                model.setImage(a);
                                model.setPrice(c);
                                model.setName(b);
                                datas.add(model);
                            }


                            adapter.notifyDataSetChanged();


                        } else {

                        }
                    }
                });


            }
        });
        thread1.start();


        recyclerView=findViewById(R.id.recler_cai);
        mTextView=findViewById(R.id.textView16);
        String s=getIntent().getStringExtra("key2");
        mTextView.setText(s);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutCompat.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//创建适配器
        adapter = new Caiadpter(R.layout.cai_item, datas);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //判断id
                if (view.getId() == R.id.imageView15) {
                    i++;
                    mTextView1.setText(String.valueOf(i));
                    tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.price);
                    j+=Integer.parseInt(tv_title.getText().toString());
                    mTextView2.setText(String.valueOf(j));

                } else if (view.getId() == R.id.imageView16) {
                    if (i>0&&j>0){
                        i--;
                        mTextView1.setText(String.valueOf(i));
                        tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.price);
                        j-=Integer.parseInt(tv_title.getText().toString());
                        mTextView2.setText(String.valueOf(j));
                    }

                }


            }
        });

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
    }
}