package com.hnucm18jr.roseapp.Xuexi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.Eradapter;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;
import com.hnucm18jr.roseapp.Shouye.zixi.Suojictivity;
import com.yalantis.taurus.PullToRefreshView;


import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class BianqianActivity extends AppCompatActivity {
    ImageView mImageView;
    private RecyclerView recyclerView;
    private List<Bianqian> datas=new ArrayList<>();
     Bqadapter adapter;
    PullToRefreshView mPullToRefreshView;
    Button mButton,mButton2;
    TextView bqtime;
    FloatingActionButton mFloatingActionButton;
    int stragger_random_height=0;
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("test.db")
            // 不设置dbDir时, 默认存储在app的私有目录.
            // .setDbDir(new File("/sdcard")) // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
            .setDbVersion(2)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 开启WAL, 对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // TODO: ...
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                    // or
                    // db.dropDb();
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianqian);
        recyclerView=findViewById(R.id.bqrecycler);
        mImageView=findViewById(R.id.back);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用


        mButton=findViewById(R.id.button);
        mButton2=findViewById(R.id.button2);
        mFloatingActionButton=findViewById(R.id.fab11);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(BianqianActivity.this,WriteActivity.class);
                i.putExtra("key","n");
                startActivity(i);

            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        DbManager db = null;
        try {
            db = x.getDb(daoConfig);
            List<Bianqian> users = new ArrayList<>();
            users= db.selector(Bianqian.class).findAll();
            for (Bianqian user : users){
                Log.i("testdb","test" + user);

                user.setContext(user.getContext());
                user.setTime(user.getTime());
                datas.add(user);

            }

        } catch (DbException e) {
            e.printStackTrace();
            return;
        }





        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.clear();
                        DbManager db = null;
                        try {// 删除1
                            db = x.getDb(daoConfig);
                            List<Bianqian> users = new ArrayList<>();
                            users= db.selector(Bianqian.class).findAll();
                            for (Bianqian user : users){
                                Log.i("testdb","test" + user);


                                user.setContext(user.getContext());
                                user.setTime(user.getTime());
                                datas.add(user);
                            }

                        } catch (DbException e) {
                            e.printStackTrace();
                            return;
                        }

                        adapter.notifyDataSetChanged();


                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutCompat.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        //创建适配器
        adapter = new Bqadapter(R.layout.bianqian_item, datas);
//        adapter.setStragger_random_height(100);
        //条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent i =new Intent(BianqianActivity.this,WriteActivity.class);
                TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.context);
                i.putExtra("key",tv_title.getText().toString());
                startActivity(i);


            }
        });
        //条目长按事件
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BianqianActivity.this)
                        .setTitle("要删除吗")
                        .setMessage("删除了不可恢复奥")
                        .setPositiveButton("删除",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.context);
                                        DbManager db = null;
                                        try {
                                            db = x.getDb(daoConfig);
                                            db.delete(Bianqian.class,
                                                    WhereBuilder.b("context", "=",tv_title.getText().toString()));

                                        } catch (DbException e) {
                                            e.printStackTrace();
                                            return;
                                        }


                                        Toast.makeText(BianqianActivity.this, "删除成功", Toast.LENGTH_LONG).show();

                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();



                return false;
            }
        });

        //开启动画（默认为渐显效果）
        adapter.openLoadAnimation();
        adapter.notifyDataSetChanged();
        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);

    }

}