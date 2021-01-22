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
import com.hnucm18jr.roseapp.Shouye.ershou.Eradapter;
import com.hnucm18jr.roseapp.Shouye.ershou.Goods;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class HuanFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Shiwu> data3s=new ArrayList<>();;
    private swzldapter adapter;
    PullToRefreshView mPullToRefreshView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_huan, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //对ui的操作 只能放到主线程



                BmobQuery<Shiwu> categoryBmobQuery = new BmobQuery<>();
                categoryBmobQuery.addWhereEqualTo("name ",null);
                categoryBmobQuery.findObjects(new FindListener<Shiwu>() {
                    @Override
                    public void done(List<Shiwu> object, BmobException e) {
                        if (e == null) {

                            for (int i=0;i<object.size();i++){
                                Shiwu model;
                                int a=Integer.valueOf(object.get(i).getImage());
                                String b=String.valueOf(object.get(i).getTitle());
                                String c=String.valueOf(object.get(i).getMiaoshu());
                                String d=String.valueOf(object.get(i).getUser());
                                String f=String.valueOf(object.get(i).getTime());
                                model = new Shiwu();
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
                        BmobQuery<Shiwu> categoryBmobQuery = new BmobQuery<>();
                        categoryBmobQuery.addWhereEqualTo("name ",null);
                        categoryBmobQuery.findObjects(new FindListener<Shiwu>() {
                            @Override
                            public void done(List<Shiwu> object, BmobException e) {
                                if (e == null) {

                                    for (int i=0;i<object.size();i++){
                                        Shiwu model;
                                        int a= object.get(i).getImage();
                                        String b=String.valueOf(object.get(i).getTitle());
                                        String c=String.valueOf(object.get(i).getMiaoshu());
                                        String d=String.valueOf(object.get(i).getUser());
                                        String f=String.valueOf(object.get(i).getTime());
                                        model = new Shiwu();
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

//            Shiwu p2 = new Shiwu();
//              p2.setTitle("捡到一个帽子");
//        p2.setMiaoshu("在操场捡的的");
//        p2.setUser("小明");
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
        adapter = new swzldapter(R.layout.swzl_item, data3s);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        data3s.clear();
    }
}
