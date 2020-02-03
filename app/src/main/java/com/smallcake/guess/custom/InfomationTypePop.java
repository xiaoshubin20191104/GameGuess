package com.smallcake.guess.custom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.smallcake.guess.R;
import com.smallcake.guess.adapter.InfomationTypeAdapter;

import java.util.List;

/**
 * Date: 2020/1/20
 * author: SmallCake
 */
public class InfomationTypePop extends PartShadowPopupView {
    InfomationTypeAdapter mAdaper;
    public InfomationTypePop(@NonNull Context context, List<String> classfiys) {
        super(context);
        mAdaper = new InfomationTypeAdapter();
        mAdaper.setNewData(classfiys);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_recycler_view;
    }

    //精选跳24小时，全部跳转到主页的第二项分类
    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView recyclerViewGoodsType = findViewById(R.id.recycler_view);
        recyclerViewGoodsType.setLayoutManager(new GridLayoutManager(this.getContext(),4));
        recyclerViewGoodsType.setAdapter(mAdaper);
        mAdaper.setOnItemClickListener((adapter, view, position) -> {
            InfomationTypePop.this.dismiss();

        });
    }
}
