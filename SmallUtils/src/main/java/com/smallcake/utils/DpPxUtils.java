package com.smallcake.utils;

public class DpPxUtils {
    private DpPxUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static float dp2pxFloat(float dpValue) {
        final float scale =  SmallUtils.getApp().getResources().getDisplayMetrics().density;
        return  (dpValue * scale + 0.5f);
    }
    public static int dp2px( float dpValue) {
        final float scale = SmallUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp( float pxValue) {
        final float scale = SmallUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
