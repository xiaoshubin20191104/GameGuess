package com.smallcake.guess.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smallcake.guess.R;
import com.smallcake.guess.bean.MatchBean;
import com.smallcake.guess.utils.DataBindingAdapter;

/**
 * Date: 2020/1/19
 * author: SmallCake
 * 赛事列表适配器
 */
public class MatchAdapter extends BaseQuickAdapter<MatchBean, BaseViewHolder> {
    public MatchAdapter() {
        super(R.layout.item_match);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MatchBean item) {
        ImageView view = helper.getView(R.id.iv_logo);
        DataBindingAdapter.bindImageTopRoundUrl(view,R.mipmap.banner2,5);
    }
}
