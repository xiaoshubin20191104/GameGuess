package com.smallcake.guess.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.smallcake.guess.R;
import com.smallcake.guess.adapter.MatchAdapter;
import com.smallcake.guess.base.BaseFragment;
import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.bean.BaseResponse;
import com.smallcake.guess.bean.MatchBean;
import com.smallcake.guess.utils.OnSuccessAndFailListener;
import com.smallcake.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/1/19
 * author: SmallCake
 * 单个赛事列表项的Fragment
 */
public class MatchListFragment extends BaseFragment {
    private static final String TAG = "MatchListFragment";
    private SwipeRefreshLayout refreshLayout;
    private MatchAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onBindView(View view, ViewGroup container, Bundle savedInstanceState) {
        L.e(TAG,"onBindView");
        mAdapter = new MatchAdapter();
        refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        recyclerView.setAdapter(mAdapter);



    }

    @Override
    protected void onLazyLoad() {
        L.e(TAG,"onLazyLoad");
        List<MatchBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new MatchBean());
        }
        mAdapter.setNewData(datas);
        loadData();
    }

    private void loadData() {
        dataProvider.ad.startAd().subscribe(new OnSuccessAndFailListener<BaseResponse<AdBean>>(refreshLayout) {
            @Override
            protected void onSuccess(BaseResponse<AdBean> adBeanBaseResponse) {

            }
        });

    }
}
