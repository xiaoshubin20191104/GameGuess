package com.smallcake.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/12/18 14:09.
 */

public class WebUtils {
    /**
     * 跳转到手机默认浏览器相关网页
     * @param context
     * @param url
     */
    public static void goWeb(Context context, String url){
        if (TextUtils.isEmpty(url)){
            ToastUtil.showLong(context.getString(R.string.link_is_empty));
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
