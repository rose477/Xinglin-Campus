package com.hnucm18jr.roseapp.Shouye.canting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.hnucm18jr.roseapp.FirstActivity;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Xuexi.BianqianActivity;
import com.hnucm18jr.roseapp.Xuexi.WriteActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class YilouActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Food1> datas=new ArrayList<>();;
    private Myadapter adapter;

    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yilou);

        recyclerView=findViewById(R.id.recycle);
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
      //  模拟的数据（实际开发中一般是从网络获取的）
//        Food1 p2 = new Food1();
//              p2.setName("瑞膳鱼粉");
//        p2.setBgimage(R.drawable.cai6);
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    Toast.makeText(YilouActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(YilouActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        Thread thread1 = new Thread(new Runnable() {//方法2
            @Override
            public void run() {
                //子线程
                BmobQuery<Food1> categoryBmobQuery = new BmobQuery<>();
                categoryBmobQuery.addWhereEqualTo("name ",null);
                categoryBmobQuery.findObjects(new FindListener<Food1>() {
                    @Override
                    public void done(List<Food1> object, BmobException e) {
                        if (e == null) {

                            for (int i=0;i<object.size();i++){
                                Food1 model;
                                int a=Integer.valueOf(object.get(i).getBgimage());
                                String b=String.valueOf(object.get(i).getName());
                                model = new Food1();
                                model.setBgimage(a);
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

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutCompat.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


//        //创建布局管理
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        adapter = new Myadapter(R.layout.yilou_item, datas);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent i =new Intent(YilouActivity.this, CaiActivity.class);
                TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.textView177);
                i.putExtra("key2",tv_title.getText().toString());
                startActivity(i);


            }
        });


        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);

    }
}