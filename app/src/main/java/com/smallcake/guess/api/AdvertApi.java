package com.smallcake.guess.api;


import com.smallcake.guess.bean.AdBean;
import com.smallcake.guess.bean.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.smallcake.guess.base.Contants.AD_START_AD;

/**
 * Date: 2019/11/25
 * author: SmallCake
 * 广告相关
 */
public interface AdvertApi {

    //广告相关接口
    @GET(AD_START_AD)
    Observable<BaseResponse<AdBean>> startAd();
}
