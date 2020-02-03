package com.smallcake.guess.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smallcake.guess.R;
import com.smallcake.guess.bean.InfomationBean;
import com.smallcake.guess.utils.DataBindingAdapter;

/**
 * Date: 2020/1/19
 * author: SmallCake
 * 赛事列表适配器
 */
public class InfomationAdapter extends BaseQuickAdapter<InfomationBean, BaseViewHolder> {
    public InfomationAdapter() {
        super(R.layout.item_infomation);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, InfomationBean item) {
        ImageView view = helper.getView(R.id.iv_cover);
        DataBindingAdapter.bindImageRoundUrl(view,R.mipmap.banner2,5);
    }
}
