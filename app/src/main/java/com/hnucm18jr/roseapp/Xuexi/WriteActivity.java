package com.hnucm18jr.roseapp.Xuexi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

public class WriteActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mButton;
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

    EditText mEditText;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }


        setContentView(R.layout.activity_write);
        mButton=findViewById(R.id.mButton);

        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用

        mEditText=findViewById(R.id.editText3);
        String s = getIntent().getStringExtra("key");
        if (!s.equals("n")){
            mEditText.setText(s);
        }else {

            i++;
        }
        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getIntent().getStringExtra("key");
                if (!s.equals("n")){


                    try {
                        DbManager db = null;
                        db = x.getDb(daoConfig);
                        db.update(Bianqian.class, WhereBuilder.b("context", "=", s),
// WhereBuilder.b("id", "=", 1).and("isAdmin", "=", true).,
                                new KeyValue("context", mEditText.getText().toString()));
                        Toast.makeText(WriteActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (DbException e) {
                        e.printStackTrace();
                        return;
                    }


                }else {
                    DbManager db = null;
                    try {
                        db = x.getDb(daoConfig);
                        Bianqian user = new Bianqian();
                        user.setContext(mEditText.getText().toString());
                        user.setTime(DataUtil.DATA());
                        Toast.makeText(WriteActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                        db.save(user);
                    } catch (DbException e) {
                        e.printStackTrace();
                        return;
                    }
                    Log.i("click11111","点击添加了");
                    finish();
                }


            }
        });
    }
}