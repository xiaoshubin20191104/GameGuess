package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindFragment;
import com.smallcake.guess.databinding.FragmentChatBinding;
import com.smallcake.utils.L;

/**
 * Date: 2020/1/3
 * author: SmallCake
 */
public class ChatFragment extends BaseBindFragment<FragmentChatBinding> {
    private static final String TAG = "ChatFragment";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
        L.e(TAG,"onBindView");
    }

    @Override
    protected void onLazyLoad() {
        L.e(TAG,"onLazyLoad");
    }
}
