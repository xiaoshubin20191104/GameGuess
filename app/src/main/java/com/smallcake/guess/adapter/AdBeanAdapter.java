package com.smallcake.guess.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smallcake.guess.R;
import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.databinding.ItemAdBinding;
import com.smallcake.guess.utils.DataBindBaseViewHolder;

/**
 * Date: 2020/1/13
 * author: SmallCake
 */
public class AdBeanAdapter extends BaseQuickAdapter<AdBean, DataBindBaseViewHolder> {
    public AdBeanAdapter() {
        super(R.layout.item_ad);
    }
    @Override
    protected void convert(@NonNull DataBindBaseViewHolder helper, AdBean item) {
        ItemAdBinding binding =  helper.getBinding();
        binding.setItem(item);
    }

}
