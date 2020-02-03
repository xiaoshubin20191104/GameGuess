package com.smallcake.guess.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smallcake.guess.R;
import com.smallcake.guess.custom.ZoomOutSlideNoAlphaTransformer;
import com.smallcake.utils.ScreenUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner工具类
 */
public class BannerUtils {
    static  int bannerHeight;//banner的高度
    static {
          bannerHeight  = (int) (ScreenUtils.getRealWidth() / 2f);
    }

    //通过广告数据动态创建Banner
    public static void initBanner(Banner banner, List<Object> imgBeans){
        if (imgBeans==null||imgBeans.size()==0);
        List<Object> imgUrls = new ArrayList<>();
        for (Object imgUrl : imgBeans) imgUrls.add(imgUrl);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams){
            banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, bannerHeight));
        }else if (layoutParams instanceof RelativeLayout.LayoutParams){
            banner.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, bannerHeight));
        }else if (layoutParams instanceof ConstraintLayout.LayoutParams){
            banner.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, bannerHeight));
        }else if (layoutParams instanceof CollapsingToolbarLayout.LayoutParams){
            banner.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, bannerHeight));
        }
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImageLoader(new BannerImgLoader());
        banner.setDelayTime(5000);
        banner.setImages(imgUrls);
        banner.start();
        banner.setOnBannerListener(position -> {});
    }

    //动态创建Banner
    public static Banner createBanner(Context context, List<Object> imgBeans){
        if (imgBeans==null||imgBeans.size()==0)return null;
        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout,null);
        Banner banner = view.findViewById(R.id.banner);
        banner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bannerHeight));
        banner.setIndicatorGravity(BannerConfig.CENTER)
        .setImageLoader(new BannerImgLoader())
        .setDelayTime(5000)
        .setImages(imgBeans)
        .start();
        return banner;
    }
    public static Banner createHomeBanner(Context context, List<Object> imgBeans){
        if (imgBeans==null||imgBeans.size()==0)return null;
        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, null);
        Banner banner = view.findViewById(R.id.banner);
        banner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bannerHeight));
        banner.setIndicatorGravity(BannerConfig.CENTER)
        .setBannerAnimation(ZoomOutSlideNoAlphaTransformer.class)
        .setImageLoader(new BannerRoundImgLoader())
        .setDelayTime(5000)
        .setImages(imgBeans)
        .start();
        return banner;
    }

}
