package com.smallcake.guess.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.databinding.ActivityInfomationListBinding;
import com.smallcake.guess.fragment.InfomationListFragment;
import com.smallcake.guess.utils.TabUtils;

import java.util.Arrays;

/**
 * 资讯列表页
 */
public class InfomationListActivity extends BaseBindBarActivity<ActivityInfomationListBinding> {
    String [] tabNames = {"最新资讯","王者荣耀","英雄联盟","DATA","绝地求生"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_infomation_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        tvTitle.setText("资讯列表");
        //头部Tab
        TabUtils.setTriangleTab(this,db.magicIndicator, Arrays.asList(tabNames),db.viewPager);
        //底部列表
        final Fragment[] fragments = new Fragment[tabNames.length];
        for (int i = 0; i < tabNames.length; i++) {
            fragments[i] = new InfomationListFragment();
        }
        final FragmentStatePagerAdapter fragmentPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager(),0){
            @Override
            public int getCount() {
                return fragments.length;
            }
            @NonNull
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabNames[position];
            }
        };
        db.viewPager.setAdapter(fragmentPagerAdapter);
        db.viewPager.setOffscreenPageLimit(fragments.length);

    }
}
