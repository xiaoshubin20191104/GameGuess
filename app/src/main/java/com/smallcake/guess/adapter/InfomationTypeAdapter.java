package com.smallcake.guess.adapter;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smallcake.guess.R;

import java.util.Random;

/**
 * Date: 2020/1/20
 * author: SmallCake
 */
public class InfomationTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public InfomationTypeAdapter() {
        super(R.layout.item_infomation_type);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text,item);
        helper.setBackgroundColor(R.id.tv_text, Color.parseColor(getRandColor()));
    }

    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
