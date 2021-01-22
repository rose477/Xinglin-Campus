package com.hnucm18jr.roseapp.Shouye.ershou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Shouye.canting.Food1;
import com.hnucm18jr.roseapp.Shouye.canting.Myadapter;
import com.hnucm18jr.roseapp.Shouye.canting.YilouActivity;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class BuyFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Goods> datas=new ArrayList<>();;
    private Eradapter adapter;
    PullToRefreshView mPullToRefreshView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //对ui的操作 只能放到主线程



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
                            Log.e("BMOB", e.toString());
                            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                try {

                }catch (Exception e){

                }
            }
        });


        mPullToRefreshView = (PullToRefreshView) getActivity().findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        datas.clear();


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
        recyclerView=getActivity().findViewById(R.id.buyrecler);
//
//            Goods p2 = new Goods();
//              p2.setTitle("想出一个相机，可小刀");
//        p2.setPrice("5");
//        p2.setUser("小明");
//        p2.setTime(DataUtil2.DATA());
//       p2.setImage(R.mipmap.ershou5);
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



        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new Eradapter(R.layout.buy_item, datas);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        datas.clear();
    }
}