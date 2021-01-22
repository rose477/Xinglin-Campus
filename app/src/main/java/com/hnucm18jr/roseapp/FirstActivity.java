package com.hnucm18jr.roseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.hnucm18jr.roseapp.Shouye.Shiwuzhao.Shiwu;
import com.hnucm18jr.roseapp.Shouye.Shiwuzhao.Shiwu2;
import com.hnucm18jr.roseapp.Shouye.ShouyeFragment;
import com.hnucm18jr.roseapp.Shouye.canting.Cai;
import com.hnucm18jr.roseapp.Shouye.canting.CaiActivity;
import com.hnucm18jr.roseapp.Shouye.canting.Food1;
import com.hnucm18jr.roseapp.Shouye.canting.YilouActivity;
import com.hnucm18jr.roseapp.Shouye.ershou.Eradapter;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;
import com.hnucm18jr.roseapp.Wode.WodeFragment;
import com.hnucm18jr.roseapp.Wow.WowFragment;
import com.hnucm18jr.roseapp.Xinwen.Person;
import com.hnucm18jr.roseapp.Xinwen.XinwenFragment;
import com.hnucm18jr.roseapp.Xuexi.Bianqian;
import com.hnucm18jr.roseapp.Xuexi.XuexiFragment;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class FirstActivity extends AppCompatActivity {

    ConstraintLayout mConstraintLayout1;
    ConstraintLayout mConstraintLayout2;
    ConstraintLayout mConstraintLayout3;
    ConstraintLayout mConstraintLayout4;
    ConstraintLayout mConstraintLayout5;
    ShouyeFragment mShouyeFragment=new ShouyeFragment();
    XinwenFragment mXinwenFragment=new XinwenFragment();
    WodeFragment mWodeFragment=new WodeFragment();
    WowFragment mWowFragment=new WowFragment();
    XuexiFragment mXuexiFragment=new XuexiFragment();
    private RecyclerView recyclerView;
    private List<Goods> datas=new ArrayList<>();;
    private Eradapter adapter;
    boolean islogin1;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

                    SharedPreferences sharedPreferences= getSharedPreferences("user1", MODE_PRIVATE);
                     islogin1 = sharedPreferences.getBoolean("islogin1",false);//如果读不到文件或者当key不存在时 返回false
        if (islogin1) {


            i++;

        }else {
            add();
        }

        iniw();



        BmobQuery<Goods> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("name ",null);
        categoryBmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> object, BmobException e) {
                if (e == null) {

                    for (int i=0;i<object.size();i++){
                        Goods model;
                        int a=Integer.valueOf(object.get(i).getImage());
                        String b=String.valueOf(object.get(i).getTitle());
                        String c=String.valueOf("$ "+object.get(i).getPrice());
                        String d=String.valueOf(object.get(i).getUser());
                        String f=String.valueOf(object.get(i).getTime());
                        model = new Goods();
                        model.setImage(a);
                        model.setTitle(b);
                        model.setPrice(c);
                        model.setUser(d);
                        model.setTime(f);
                        datas.add(model);
                    }


                    adapter.notifyDataSetChanged();


                } else {


                }
            }
        });

        mConstraintLayout1.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
        mConstraintLayout2.setSelected(false);
        mConstraintLayout3.setSelected(false);
        mConstraintLayout4.setSelected(false);
        mConstraintLayout5.setSelected(false);

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,mShouyeFragment).commit();//点击切换到fragment界面




//        //设置点击事件监听
//        bannerViewView.setBannerOnClickListener(new BannerView.BannerOnClickListener() {
//            @Override
//            public void onClick(int position) {
//            }
//        });
        mConstraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout1.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout4.setSelected(false);
                mConstraintLayout5.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mShouyeFragment).commit();//点击切换到fragment界面
            }
        });
        mConstraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout2.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout1.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout4.setSelected(false);
                mConstraintLayout5.setSelected(false);


                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mXinwenFragment).commit();//点击切换到fragment界面
            }
        });
        mConstraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout3.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout1.setSelected(false);
                mConstraintLayout4.setSelected(false);
                mConstraintLayout5.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mWowFragment).commit();//点击切换到fragment界面
            }
        });
        mConstraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout4.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout1.setSelected(false);
                mConstraintLayout5.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mXuexiFragment).commit();//点击切换到fragment界面
            }
        });
        mConstraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout5.setSelected(true);//表示布局被选中，里面的所有控件都为选中状态
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
                mConstraintLayout4.setSelected(false);
                mConstraintLayout1.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,mWodeFragment).commit();//点击切换到fragment界面
            }
        });


    }

    private void add() {
        Goods p2 = new Goods();
        p2.setTitle("想出一个相机，可小刀");
        p2.setPrice("5");
        p2.setUser("小明");
        p2.setTime(DataUtil.DATA());
        p2.setImage(R.mipmap.ershou5);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(FirstActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FirstActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        p2.setTitle("想出一个寝室电脑桌，可小刀");
        p2.setPrice("23");
        p2.setUser("小陈");
        p2.setTime(DataUtil.DATA());
        p2.setImage(R.drawable.ershou1);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                    // 点击按钮 代表登录成功
                    //获取SharedPreferences对象
                    SharedPreferences sharedPreferences = getSharedPreferences("user1",MODE_PRIVATE);
                    //获取Editor对象的引用
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //将登录的状态放入文件
                    editor.putBoolean("islogin1",true);
                    // 提交数据
                    editor.commit();

                    Toast.makeText(FirstActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });

                    p2.setTitle("想出一个相机，可小刀");
                    p2.setPrice("35");
                    p2.setUser("小明");
                    p2.setTime(DataUtil.DATA());
                    p2.setImage(R.mipmap.ershou5);
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){

                            }else{

                            }
                        }
                    });


                    p2.setTitle("出全新耳机，有需要的找我联系");
                    p2.setPrice("15");
                    p2.setUser("小万");
                    p2.setTime(DataUtil.DATA());
                    p2.setImage(R.drawable.ershou2);
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){

                            }else{

                            }
                        }
                    });


                    p2.setTitle("想出一本二手的医宗金鉴");
                    p2.setPrice("10");
                    p2.setUser("小金");
                    p2.setTime(DataUtil.DATA());
                    p2.setImage(R.mipmap.ershou4);
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){

                            }else{

                            }
                        }
                    });


                    p2.setTitle("出一个仰卧起坐辅助器，用的不多");
                    p2.setPrice("16");
                    p2.setUser("小鲍");
                    p2.setTime(DataUtil.DATA());
                    p2.setImage(R.mipmap.ershou3);
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){

                            }else{

                            }
                        }
                    });


            Shiwu p3 = new Shiwu();
        p3.setTitle("捡到一个帽子");
        p3.setMiaoshu("在操场捡的");
        p3.setUser("小明");
        p3.setTime(DataUtil.DATA());
        p3.setImage(R.mipmap.shiwu4);
        p3.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p3.setTitle("捡到一个充电头");
        p3.setMiaoshu("在6栋寝室教捡到的");
        p3.setUser("小曹");
        p3.setTime(DataUtil.DATA());
        p3.setImage(R.mipmap.shiwu1);
        p3.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p3.setTitle("捡到一个杯子");
        p3.setMiaoshu("在3教捡的的");
        p3.setUser("小周");
        p3.setTime(DataUtil.DATA());
        p3.setImage(R.mipmap.shiwu2);
        p3.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p3.setTitle("捡到一个杯子");
        p3.setMiaoshu("2206教室留下的");
        p3.setUser("小黄");
        p3.setTime(DataUtil.DATA());
        p3.setImage(R.mipmap.shiwu3);
        p3.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        Shiwu2 p4 = new Shiwu2();
        p4.setTitle("丢了一个帽子");
        p4.setMiaoshu("在操场丢的");
        p4.setUser("小明");
        p4.setTime(DataUtil.DATA());
        p4.setImage(R.mipmap.shiwu4);
        p4.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p4.setTitle("我丢了一个充电头");
        p4.setMiaoshu("在6栋寝室教丢的");
        p4.setUser("小曹");
        p4.setTime(DataUtil.DATA());
        p4.setImage(R.mipmap.shiwu1);
        p4.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p4.setTitle("丢了一个杯子");
        p4.setMiaoshu("在3教丢的");
        p4.setUser("小周");
        p4.setTime(DataUtil.DATA());
        p4.setImage(R.mipmap.shiwu2);
        p4.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p4.setTitle("丢了一个杯子");
        p4.setMiaoshu("2206教室丢的");
        p4.setUser("小黄");
        p4.setTime(DataUtil.DATA());
        p4.setImage(R.mipmap.shiwu3);
        p4.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });

        Food1 p5 = new Food1();
        p5.setName("津市牛肉粉");
        p5.setBgimage(R.drawable.cai2);
        p5.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p5.setName("好运套餐");
        p5.setBgimage(R.drawable.cai3);
        p5.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p5.setName("精品套餐");
        p5.setBgimage(R.drawable.cai4);
        p5.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p5.setName("广东肠粉");
        p5.setBgimage(R.drawable.cai5);
        p5.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });

        p5.setName("瑞膳鱼粉");
        p5.setBgimage(R.drawable.cai6);
        p5.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });


//
//        Food1 p5 = new Food1();
//        p5.setName("国雄面馆");
//        p5.setBgimage(R.mipmap.zi1);
//        p5.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });
//        p5.setName("小李汤家");
//        p5.setBgimage(R.mipmap.zi2);
//        p5.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });
//        p5.setName("陈记烤串");
//        p5.setBgimage(R.mipmap.zi3);
//        p5.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });
//        p5.setName("美食早餐");
//        p5.setBgimage(R.mipmap.zi4);
//        p5.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });
//
//        p5.setName("三姐妹套餐");
//        p5.setBgimage(R.mipmap.zi5);
//        p5.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });

        Cai p6 = new Cai();
        p6.setName("辣椒炒肉");
        p6.setPrice(3);
        p6.setImage(R.mipmap.shi1);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("剁椒鱼头");
        p6.setPrice(4);
        p6.setImage(R.mipmap.shi2);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("豆角炒茄子");
        p6.setPrice(3);
        p6.setImage(R.mipmap.shi3);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("卤汁鸭");
        p6.setPrice(3);
        p6.setImage(R.mipmap.shi4);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("红烧肉");
        p6.setPrice(3);
        p6.setImage(R.mipmap.shi5);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("凉拌皮蛋");
        p6.setPrice(3);
        p6.setImage(R.mipmap.shi6);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("梅菜扣肉");
        p6.setPrice(4);
        p6.setImage(R.mipmap.shi7);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });
        p6.setName("银耳莲子羹");
        p6.setPrice(4);
        p6.setImage(R.mipmap.shi8);
        p6.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{

                }
            }
        });

    }

    private void iniw() {

        mConstraintLayout1=findViewById(R.id.constraintLayout36);
        mConstraintLayout2=findViewById(R.id.constraintLayout35);
        mConstraintLayout3=findViewById(R.id.constraintLayout37);
        mConstraintLayout4=findViewById(R.id.constraintLayout38);
        mConstraintLayout5=findViewById(R.id.constraintLayout39);

    }


}