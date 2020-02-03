package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindFragment;
import com.smallcake.guess.databinding.FragmentMineBinding;

/**
 * Date: 2020/1/3
 * author: SmallCake
 */
public class MineFragment extends BaseBindFragment<FragmentMineBinding>{
    private static final String TAG = "MineFragment";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
    }

    @Override
    protected void onLazyLoad() {
    }
}
