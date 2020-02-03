package com.smallcake.guess.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.smallcake.guess.R;
import com.smallcake.guess.adapter.MultipleItemQuickAdapter;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.bean.HeaderItem;
import com.smallcake.guess.bean.MultipleItem;
import com.smallcake.guess.databinding.ActivityGameBinding;
import com.smallcake.guess.databinding.ItemMultipleBottomBinding;
import com.smallcake.guess.utils.BannerUtils;
import com.smallcake.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏单页
 */
public class GameActivity extends BaseBindBarActivity<ActivityGameBinding> {
    private MultipleItemQuickAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText("和平精英");
        Glide.with(this)
                .load("https://cdn.dribbble.com/users/63407/screenshots/9323216/media/84481f7fff0aa3029aaf1f325923a71c.png")
                .apply(new RequestOptions().transform(new CircleCrop()))
                .into(ivRight);
        initView();
        onEvent();
    }

    private void onEvent() {
        db.setClicklistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_tab1://赛事
                        goActivity(MatchActivity.class);
                        break;
                    case R.id.tv_tab2://资讯
                        goActivity(InfomationListActivity.class);
                        break;
                    case R.id.tv_tab3://视频
                        break;
                    case R.id.tv_tab4://竞猜
                        break;
                }
            }
        });
    }

    private void initView() {
        mAdapter = new MultipleItemQuickAdapter();
        mAdapter.openLoadAnimation();
        GridLayoutManager manager = new GridLayoutManager(this, 20, GridLayoutManager.VERTICAL, false);
        db.recyclerView.setAdapter(mAdapter);
        db.recyclerView.setLayoutManager(manager);


        List<MultipleItem> datas = new ArrayList<>();

        datas.add(new HeaderItem("最新竞猜 "));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.GUESS));

        datas.add(new HeaderItem("热门动态 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.HOT_TRENDS));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

        datas.add(new HeaderItem("热门赛事 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 4; i++)datas.add(new MultipleItem(MultipleItem.HOT_MATCH));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

        datas.add(new HeaderItem("热门战队 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 10; i++) datas.add(new MultipleItem(MultipleItem.HOT_TEAM));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

        datas.add(new HeaderItem("热门视频 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.HOT_VIDEO));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

        datas.add(new HeaderItem("最新资讯 ",MultipleItem.HEADER_RED_VIOLET));
        for (int i = 0; i < 2; i++)datas.add(new MultipleItem(MultipleItem.NEWS));
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));


        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = (position==datas.size())?0:datas.get(position).getItemType();
                switch (type){
                    case MultipleItem.GAME_LIST:return 5;
                    case MultipleItem.HOT_MATCH:return 10;
                    case MultipleItem.HOT_TEAM:return 4;
                    default:return 20;
                }
            }
        });

        List<Object> imgs = new ArrayList<>();
        imgs.add(R.mipmap.banner1);
        imgs.add(R.mipmap.banner2);
        imgs.add(R.mipmap.timg);
        BannerUtils.initBanner(db.banner, imgs);
        View viewFooter = LayoutInflater.from(this).inflate(R.layout.item_multiple_bottom, null);
        ItemMultipleBottomBinding bind = DataBindingUtil.bind(viewFooter);
        bind.setClicklistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_about_us:
                        ToastUtil.showShort("请联系客服QQ！");
                        break;
                    case R.id.tv_go_top:
                        db.recyclerView.smoothScrollToPosition(0);
                        break;
                    case R.id.layout_apply_house:
                        ToastUtil.showShort("恭喜你成为了房主！");
                        break;
                }
            }
        });
        mAdapter.addFooterView(viewFooter);
        mAdapter.setNewData(datas);




    }
}
