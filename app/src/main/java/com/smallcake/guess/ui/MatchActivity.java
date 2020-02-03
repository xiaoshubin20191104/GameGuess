package com.smallcake.guess.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.databinding.ActivityMatchBinding;
import com.smallcake.guess.fragment.MatchListFragment;
import com.smallcake.guess.utils.TabUtils;

import java.util.Arrays;

/**
 * 赛事主页
 */
public class MatchActivity extends BaseBindBarActivity<ActivityMatchBinding> {
    String [] tabNames = {"热门赛事","王者荣耀","英雄联盟","绝地求生"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_match;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        tvTitle.setText("赛事");
        //头部Tab
        TabUtils.setTriangleTab(this,db.magicIndicator, Arrays.asList(tabNames),db.viewPager);
        //底部列表
        final Fragment[] fragments = new Fragment[tabNames.length];
        for (int i = 0; i < tabNames.length; i++) {
            fragments[i] = new MatchListFragment();
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
