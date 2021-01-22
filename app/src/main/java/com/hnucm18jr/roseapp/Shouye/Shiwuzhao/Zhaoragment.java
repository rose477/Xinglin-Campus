package com.hnucm18jr.roseapp.Shouye.Shiwuzhao;

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
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class Zhaoragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Shiwu2> data3s=new ArrayList<>();;
    private swzldapter2 adapter;
    PullToRefreshView mPullToRefreshView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zhaoragment, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //对ui的操作 只能放到主线程



                BmobQuery<Shiwu2> categoryBmobQuery = new BmobQuery<>();
                categoryBmobQuery.addWhereEqualTo("name ",null);
                categoryBmobQuery.findObjects(new FindListener<Shiwu2>() {
                    @Override
                    public void done(List<Shiwu2> object, BmobException e) {
                        if (e == null) {

                            for (int i=0;i<object.size();i++){
                                Shiwu2 model;
                                int a=Integer.valueOf(object.get(i).getImage());
                                String b=String.valueOf(object.get(i).getTitle());
                                String c=String.valueOf(object.get(i).getMiaoshu());
                                String d=String.valueOf(object.get(i).getUser());
                                String f=String.valueOf(object.get(i).getTime());
                                model = new Shiwu2();
                                model.setImage(a);
                                model.setTitle(b);
                                model.setMiaoshu(c);
                                model.setUser(d);
                                model.setTime(f);
                                data3s.add(model);
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

                        data3s.clear();
                        BmobQuery<Shiwu2> categoryBmobQuery = new BmobQuery<>();
                        categoryBmobQuery.addWhereEqualTo("name ",null);
                        categoryBmobQuery.findObjects(new FindListener<Shiwu2>() {
                            @Override
                            public void done(List<Shiwu2> object, BmobException e) {
                                if (e == null) {

                                    for (int i=0;i<object.size();i++){
                                        Shiwu2 model;
                                        int a= object.get(i).getImage();
                                        String b=String.valueOf(object.get(i).getTitle());
                                        String c=String.valueOf(object.get(i).getMiaoshu());
                                        String d=String.valueOf(object.get(i).getUser());
                                        String f=String.valueOf(object.get(i).getTime());
                                        model = new Shiwu2();
                                        model.setImage(a);
                                        model.setTitle(b);
                                        model.setMiaoshu(c);
                                        model.setUser(d);
                                        model.setTime(f);
                                        data3s.add(model);
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

//            Shiwu2 p2 = new Shiwu2();
//              p2.setTitle("丢了一个帽子");
//        p2.setMiaoshu("在操场丢的");
//        p2.setUser("小红");
//        p2.setTime(DataUtil2.DATA());
//       p2.setImage(R.mipmap.shiwu4);
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
        adapter = new swzldapter2(R.layout.swzl_item, data3s);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        data3s.clear();
    }
}
