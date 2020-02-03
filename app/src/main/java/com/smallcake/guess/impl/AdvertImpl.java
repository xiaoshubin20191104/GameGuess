package com.smallcake.guess.impl;


import com.smallcake.guess.api.AdvertApi;
import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.bean.BaseResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.smallcake.okhttp.retrofit2.RetrofitComposeUtils.bindIoUI;

/**
 * Date: 2019/11/25
 * author: SmallCake
 */
public class AdvertImpl implements AdvertApi {
    @Inject
    AdvertApi advertApi;
    @Inject
    public AdvertImpl() {}

    @Override
    public Observable<BaseResponse<AdBean>> startAd() {
        return bindIoUI(advertApi.startAd());
    }

}
