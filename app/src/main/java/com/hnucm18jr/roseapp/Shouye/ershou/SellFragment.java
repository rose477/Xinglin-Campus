package com.hnucm18jr.roseapp.Shouye.ershou;

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

import com.hnucm18jr.roseapp.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SellFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Goods2> data2s=new ArrayList<>();;
    private Eradapyer2 adapter;
    PullToRefreshView mPullToRefreshView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);


        BmobQuery<Goods2> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("name ",null);
        categoryBmobQuery.findObjects(new FindListener<Goods2>() {
            @Override
            public void done(List<Goods2> object, BmobException e) {
                if (e == null) {

                    data2s.clear();
                    for (int i=0;i<object.size();i++){
                        Goods2 model;

                        String b=String.valueOf(object.get(i).getTitle());
                        String c=String.valueOf("$ "+object.get(i).getPrice()+" 之内");
                        String d=String.valueOf(object.get(i).getName());
                        String f=String.valueOf(object.get(i).getTime());
                        model = new Goods2();

                        model.setTitle(b);
                        model.setPrice(c);
                        model.setName(d);
                        model.setTime(f);
                        data2s.add(model);
                    }


                    adapter.notifyDataSetChanged();


                } else {
                    Log.e("BMOB", e.toString());
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
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
                        BmobQuery<Goods2> categoryBmobQuery = new BmobQuery<>();
                        categoryBmobQuery.addWhereEqualTo("name ",null);
                        categoryBmobQuery.findObjects(new FindListener<Goods2>() {
                            @Override
                            public void done(List<Goods2> object, BmobException e) {
                                if (e == null) {

                                    data2s.clear();
                                for (int i=0;i<object.size();i++){
                                        Goods2 model;

                                        String b=String.valueOf(object.get(i).getTitle());
                                        String c=String.valueOf("$ "+object.get(i).getPrice()+" 之内");
                                        String d=String.valueOf(object.get(i).getName());
                                        String f=String.valueOf(object.get(i).getTime());
                                        model = new Goods2();

                                        model.setTitle(b);
                                        model.setPrice(c);
                                        model.setName(d);
                                        model.setTime(f);
                                        data2s.add(model);
                                    }


                                    adapter.notifyDataSetChanged();


                                } else {
                                    Log.e("BMOB", e.toString());
                                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        adapter.notifyDataSetChanged();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        recyclerView=getActivity().findViewById(R.id.buyrecler);

//            Goods2 p2 = new Goods2();
//              p2.setTitle("想买一个篮球");
//        p2.setPrice("100");
//        p2.setName("小熊猫");
//        p2.setTime(DataUtil2.DATA());
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



        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new Eradapyer2(R.layout.sell_item, data2s);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);

    }
}