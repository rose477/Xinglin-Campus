package com.hnucm18jr.roseapp.Wow;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.hnucm18jr.roseapp.R;
import com.hnucm18jr.roseapp.Wode.chaqinActivity;

import java.util.ArrayList;


public class WowFragment extends Fragment {
     TabLayout tabLayout;
     ViewPager viewPager;
    private FragmentPagerAdapter mPageAdapter;
    private ArrayList<String> titleList = new ArrayList<>();

    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    FloatingActionButton mFloatingActionButton;
    W1Fragment mW1Fragment=new W1Fragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wow, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout=getActivity().findViewById(R.id.tabLayout2);
        viewPager=getActivity().findViewById(R.id.viewPager);
        mFloatingActionButton=getActivity().findViewById(R.id.fab);

        titleList.clear();
        titleList.add("推荐");
        titleList.add("WOW时刻");
        titleList.add("学科知识");
        titleList.add("经典实验");
        titleList.add("项目经验");
        titleList.add("比赛感想");
        titleList.add("答疑解惑");



        fragmentList.clear();
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());
        fragmentList.add(new W1Fragment());


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),BianjiActivity.class);
                startActivity(intent);
            }
        });
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewPager,mW1Fragment).commit();//点击切换到fragment界面
        mPageAdapter=new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {


                return fragmentList.get(i);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);

            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }
        };
//        changeTabsFont();

        viewPager.setAdapter(mPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        mPageAdapter.notifyDataSetChanged();

    }
//    public void changeTabsFont() {
//
//        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
//        int tabsCount = vg.getChildCount();
//        for (int j = 0; j < tabsCount; j++) {
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
//            int tabChildsCount = vgTab.getChildCount();
//            for (int i = 0; i < tabChildsCount; i++) {
//                View tabViewChild = vgTab.getChildAt(i);
//                if (tabViewChild instanceof TextView) {
//                    Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/STKAITI.TTF");
//                    ((TextView) tabViewChild).setTypeface(mTypeface);
//                }
//            }
//        }
//    }
}