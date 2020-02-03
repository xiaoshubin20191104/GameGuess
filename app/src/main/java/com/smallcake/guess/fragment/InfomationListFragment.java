package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.smallcake.guess.R;
import com.smallcake.guess.adapter.InfomationAdapter;
import com.smallcake.guess.base.BaseFragment;
import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.bean.BaseResponse;
import com.smallcake.guess.bean.InfomationBean;
import com.smallcake.guess.custom.InfomationTypePop;
import com.smallcake.guess.ui.InfomationDetailsActivity;
import com.smallcake.guess.utils.OnSuccessAndFailListener;
import com.smallcake.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/1/19
 * author: SmallCake
 * 单个资讯列表项的Fragment
 */
public class InfomationListFragment extends BaseFragment {
    private static final String TAG = "MatchListFragment";
    private SwipeRefreshLayout refreshLayout;
    private TextView tvType;
    private InfomationAdapter mAdapter;
    private  BasePopupView typePop;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infomation_list;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
        L.e(TAG,"onBindView");
        mAdapter = new InfomationAdapter();
        refreshLayout = view.findViewById(R.id.refresh);
        tvType = view.findViewById(R.id.tv_type);
        refreshLayout.setOnRefreshListener(() -> loadData());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(mAdapter);




    }



    @Override
    protected void onLazyLoad() {
        L.e(TAG,"onLazyLoad");
        List<InfomationBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new InfomationBean());
        }
        mAdapter.setNewData(datas);
        loadData();


        List<String> typeNames = new ArrayList<>();
        for (int i = 0; i <16 ; i++) typeNames.add("筛选条件");
        typePop = new XPopup.Builder(InfomationListFragment.this.getContext())
                .hasShadowBg(false)
                .dismissOnTouchOutside(true)
                .atView(tvType)
                .asCustom(new InfomationTypePop(InfomationListFragment.this.getContext(), typeNames));


        onEvent();

    }
    private void onEvent() {
        tvType.setOnClickListener(v -> {
            if (!typePop.isShow())typePop.show();
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goActivity(InfomationDetailsActivity.class);
            }
        });
    }

    private void loadData() {
        dataProvider.ad.startAd().subscribe(new OnSuccessAndFailListener<BaseResponse<AdBean>>(refreshLayout) {
            @Override
            protected void onSuccess(BaseResponse<AdBean> adBeanBaseResponse) {

            }
        });

    }
}
