package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindFragment;
import com.smallcake.guess.databinding.FragmentGuessBinding;

/**
 * Date: 2020/1/3
 * author: SmallCake
 */
public class GuessFragment extends BaseBindFragment<FragmentGuessBinding>{
    private static final String TAG = "GuessFragment";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guess;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
    }

    @Override
    protected void onLazyLoad() {

    }
}
