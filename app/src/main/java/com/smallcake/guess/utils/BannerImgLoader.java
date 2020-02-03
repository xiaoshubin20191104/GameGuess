package com.smallcake.guess.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smallcake.guess.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Date: 2020/1/7
 * author: SmallCake
 * Banner图片加载器
 */
public class BannerImgLoader extends ImageLoader {
    @SuppressLint("CheckResult")
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.no_banner).error(R.drawable.no_banner);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(path)
                .apply(options)
                .thumbnail(0.1f)
                .into(imageView);
    }
}
