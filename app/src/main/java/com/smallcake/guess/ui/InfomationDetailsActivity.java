package com.smallcake.guess.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.smallcake.guess.R;
import com.smallcake.guess.adapter.MultipleItemQuickAdapter;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.bean.HeaderItem;
import com.smallcake.guess.bean.MultipleItem;
import com.smallcake.guess.databinding.ActivityInfomationDetailsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯详情页
 */
public class InfomationDetailsActivity extends BaseBindBarActivity<ActivityInfomationDetailsBinding> {
    private MultipleItemQuickAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_infomation_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        tvTitle.setText("资讯详情");
        mAdapter = new MultipleItemQuickAdapter();
        mAdapter.openLoadAnimation();
        GridLayoutManager manager = new GridLayoutManager(this, 20, GridLayoutManager.VERTICAL, false);
        db.recyclerView.setAdapter(mAdapter);
        db.recyclerView.setLayoutManager(manager);

        List<MultipleItem> datas = new ArrayList<>();
        datas.add(new HeaderItem("相关竞猜 "));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.GUESS));
        datas.add(new HeaderItem("相关资讯 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.NEWS));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));
        datas.add(new HeaderItem("相关视频 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.NEWS));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
//                int type = (position==0||position==datas.size()+1)?0:datas.get(position-1).getItemType();
                int type = datas.get(position).getItemType();
                switch (type){
                    case MultipleItem.GAME_LIST:return 5;
                    case MultipleItem.HOT_MATCH:return 10;
                    case MultipleItem.HOT_TEAM:return 4;
                    default:return 20;
                }
            }
        });
        mAdapter.setNewData(datas);

    }
}
