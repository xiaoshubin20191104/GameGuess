package com.smallcake.guess;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.smallcake.guess.base.BaseBindActivity;
import com.smallcake.guess.databinding.ActivityMainBinding;
import com.smallcake.guess.fragment.ChatFragment;
import com.smallcake.guess.fragment.GuessFragment;
import com.smallcake.guess.fragment.HomeFragment;
import com.smallcake.guess.fragment.MineFragment;

/**
 * 主要包含：
 * 1.游戏 {@link HomeFragment}
 * 2.商城 {@link GuessFragment}
 * 3.聊天 {@link ChatFragment}
 * 4.我的 {@link MineFragment}
 */
public class MainActivity extends BaseBindActivity<ActivityMainBinding> {
    private String[] tabNames = new String[]{"首页", "竞猜", "我的"};
    private int[] tabImgSelect = new int[]{R.mipmap.home_hover,R.mipmap.guesse_hover,R.mipmap.user_hover};
    private int[] tabImgUnSelect = new int[]{R.mipmap.home,R.mipmap.guesse,R.mipmap.user};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewPager();
    }


    private void initViewPager() {
        final Fragment[] fragments = new Fragment[]{new HomeFragment(), new GuessFragment(), new MineFragment()};
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
       //底部导航栏
        int tabChildCount = db.layoutTab.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            RelativeLayout childAt = (RelativeLayout) db.layoutTab.getChildAt(i);
            int finalI = i;
            childAt.setOnClickListener(v -> selectTab(finalI));
        }

    }

    private void selectTab(int position) {
        db.viewPager.setCurrentItem(position);
        //选中项变色，未选中变灰
        for (int i = 0; i < db.layoutTab.getChildCount(); i++) {
            RelativeLayout childAt = (RelativeLayout) db.layoutTab.getChildAt(i);
            ImageView ivLogo = (ImageView) childAt.getChildAt(0);
            TextView tvName = (TextView) childAt.getChildAt(1);
            ivLogo.setImageResource(position==i?tabImgSelect[i]:tabImgUnSelect[i]);
            tvName.setTextColor(ContextCompat.getColor(this,position==i?R.color.colorAccent:R.color.gray));
        }
    }
}
