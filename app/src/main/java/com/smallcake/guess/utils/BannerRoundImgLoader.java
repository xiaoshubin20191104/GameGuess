package com.smallcake.guess.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.smallcake.guess.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Date: 2020/1/7
 * author: SmallCake
 * Banner图片加载器
 */
public class BannerRoundImgLoader extends ImageLoader {
    @SuppressLint("CheckResult")
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(),new RoundedCorners(18))
                .placeholder(R.drawable.no_banner)
                .error(R.drawable.no_banner);
        Glide.with(context).load(path)
                .apply(options)
                .thumbnail(0.1f)
                .into(imageView);
    }
}
