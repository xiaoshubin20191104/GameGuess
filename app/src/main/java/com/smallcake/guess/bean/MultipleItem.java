package com.smallcake.guess.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Date: 2020/1/16
 * author: SmallCake
 * 多布局：实体类
 *  * 1.banner
 *  * 2.游戏列表
 *  * 3.最新竞猜
 *  * 4.热门动态
 *  * 5.热门赛事
 *  * 6.热门战队
 *  * 7.热门视频
 *  * 8.最新资讯
 *  * 9.申请成为房主
 *  * 10.回到顶部，客服
 */
public class MultipleItem implements MultiItemEntity {
    public static final int HEADER = 0;//头部
    public static final int BANNER = 1;
    public static final int GAME_LIST = 2;
    public static final int GUESS = 3;
    public static final int HOT_TRENDS = 4;
    public static final int HOT_MATCH = 5;
    public static final int HOT_TEAM = 6;
    public static final int HOT_VIDEO = 7;
    public static final int NEWS = 8;

    public static final int ADVERT = 9;//广告
    public static final int HEADER_RED_VIOLET = 10;//红紫头部
    public static final int BOTTOM_ROUND_WHITE = 11;//底部白色圆角
    public static final int TOP_ROUND_WHITE = 12;//顶部白色圆角
    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
