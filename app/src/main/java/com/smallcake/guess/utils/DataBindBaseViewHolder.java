package com.smallcake.guess.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseViewHolder;

public class DataBindBaseViewHolder extends BaseViewHolder {
    private View itemView;
    public DataBindBaseViewHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView.getTag()!=null)this.itemView = itemView;
    }
    public <DB extends ViewDataBinding>DB getBinding() {
        return DataBindingUtil.bind(itemView);
    }
}
