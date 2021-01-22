package com.hnucm18jr.roseapp.Wow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.ershou.Eradapter;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;
import com.hnucm18jr.roseapp.Wode.DataUtil2;
import com.yalantis.taurus.PullToRefreshView;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class W1Fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Dongtai> datas=new ArrayList<>();;
    private Dongtaiadpter adapter;
    PullToRefreshView mPullToRefreshView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_w1, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//            Dongtai p2 = new Dongtai();
//              p2.setName("许意坚");
//        p2.setGrade("大三");
//        p2.setZhuanye("计算机科学与技术");
//        p2.setContent("你好世界啊");
//        p2.setA(3);
//        p2.setB(15);
//        p2.setTime(DataUtil.DATA());
//
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //对ui的操作 只能放到主线程



                BmobQuery<Dongtai> categoryBmobQuery = new BmobQuery<>();
                categoryBmobQuery.addWhereEqualTo("name ",null);
                categoryBmobQuery.findObjects(new FindListener<Dongtai>() {
                    @Override
                    public void done(List<Dongtai> object, BmobException e) {
                        if (e == null) {

                            for (int i=0;i<object.size();i++){
                                Dongtai model;
                                String a=String.valueOf(object.get(i).getZhuanye());
                                String b=String.valueOf(object.get(i).getName());
                                String c=String.valueOf(object.get(i).getGrade());
                                String d=String.valueOf(object.get(i).getContent());
                                String f=String.valueOf(object.get(i).getTime());
                                int g=object.get(i).getA();
                                int h=object.get(i).getB();
                                model = new Dongtai();
                                model.setZhuanye(a);
                                model.setName(b);
                                model.setGrade(c);
                                model.setContent(d);
                                model.setTime(f);
                                model.setA(g);
                                model.setB(h);
                                datas.add(model);
                            }


                            adapter.notifyDataSetChanged();
                            Log.i("BMOB111", "111111");


                        } else {

                            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                try {

                }catch (Exception e){

                }
            }
        });
        mPullToRefreshView = getActivity().findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        datas.clear();

                        BmobQuery<Dongtai> categoryBmobQuery = new BmobQuery<>();
                        categoryBmobQuery.addWhereEqualTo("name ",null);
                        categoryBmobQuery.findObjects(new FindListener<Dongtai>() {
                            @Override
                            public void done(List<Dongtai> object, BmobException e) {
                                if (e == null) {

                                    for (int i=0;i<object.size();i++){
                                        Dongtai model;
                                        String a=String.valueOf(object.get(i).getZhuanye());
                                        String b=String.valueOf(object.get(i).getName());
                                        String c=String.valueOf("$ "+object.get(i).getGrade());
                                        String d=String.valueOf(object.get(i).getContent());
                                        String f=String.valueOf(object.get(i).getTime());
                                        model = new Dongtai();
                                        model.setZhuanye(a);
                                        model.setName(b);
                                        model.setGrade(c);
                                        model.setContent(d);
                                        model.setTime(f);
                                        datas.add(model);
                                    }


                                    adapter.notifyDataSetChanged();



                                } else {
                                    Log.e("BMOB", e.toString());
                                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        recyclerView=getActivity().findViewById(R.id.dtrecycler);


        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new Dongtaiadpter(R.layout.wow_item, datas);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);


    }
}