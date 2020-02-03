package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smallcake.guess.R;
import com.smallcake.guess.adapter.MultipleItemQuickAdapter;
import com.smallcake.guess.base.BaseBindFragment;
import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.bean.BaseResponse;
import com.smallcake.guess.bean.GameItem;
import com.smallcake.guess.bean.HeaderItem;
import com.smallcake.guess.bean.MultipleItem;
import com.smallcake.guess.databinding.FragmentHomeBinding;
import com.smallcake.guess.databinding.ItemMultipleBottomBinding;
import com.smallcake.guess.ui.GameActivity;
import com.smallcake.guess.ui.VideoInfoActivity;
import com.smallcake.guess.utils.BannerUtils;
import com.smallcake.guess.utils.OnSuccessAndFailListener;
import com.smallcake.utils.ToastUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/1/3
 * author: SmallCake
 * 包含十个部分
 * 1.banner
 * 2.游戏列表
 * 3.最新竞猜
 * 4.热门动态
 * 5.热门赛事
 * 6.热门战队
 * 7.热门视频
 * 8.最新资讯
 * 9.申请成为房主
 * 10.回到顶部，客服
 *
 */
public class HomeFragment extends BaseBindFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";
    private MultipleItemQuickAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
        mAdapter = new MultipleItemQuickAdapter();
        mAdapter.openLoadAnimation();
    }

    @Override
    protected void onLazyLoad() {
        initView();
        onEvent();
//        loadData();
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 20, GridLayoutManager.VERTICAL, false);
        db.recyclerView.setAdapter(mAdapter);
        db.recyclerView.setLayoutManager(manager);


        List<MultipleItem> datas = new ArrayList<>();
        datas.add(new MultipleItem(MultipleItem.ADVERT));

        datas.add(new MultipleItem(MultipleItem.TOP_ROUND_WHITE));
        for (int i = 0; i < 4; i++){
            GameItem gameItem = new GameItem();
            gameItem.setLogo(i%2==0?R.mipmap.game_logo1:R.mipmap.game_logo2);
            datas.add(gameItem);
        }
        datas.add(new MultipleItem(MultipleItem.BOTTOM_ROUND_WHITE));

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
                int type = (position==0||position==datas.size()+1)?0:datas.get(position-1).getItemType();
//                int type = datas.get(position).getItemType();
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
        Banner banner = BannerUtils.createHomeBanner(this.getContext(), imgs);
        //添加banner头部，添加底部
        mAdapter.addHeaderView(banner);
        View viewFooter = LayoutInflater.from(this.getContext()).inflate(R.layout.item_multiple_bottom, null);
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
    //网络请求
    private void loadData() {
        dataProvider.ad.startAd().subscribe(new OnSuccessAndFailListener<BaseResponse<AdBean>>(db.refresh) {
            @Override
            protected void onSuccess(BaseResponse<AdBean> adBeanBaseResponse) {

            }
        });
    }

    private void onEvent() {
        //下拉刷新
        db.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem item = (MultipleItem) adapter.getItem(position);
                int itemType = item.getItemType();
                switch (itemType){
                    case MultipleItem.GAME_LIST://游戏主页
                        goActivity(GameActivity.class);
                        break;
                    case MultipleItem.HOT_VIDEO:
                        goActivity(VideoInfoActivity.class);
                        break;
                }

            }
        });
    }

}
