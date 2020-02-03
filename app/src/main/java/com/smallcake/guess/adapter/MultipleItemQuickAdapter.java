package com.smallcake.guess.adapter;

import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smallcake.guess.R;
import com.smallcake.guess.bean.GameItem;
import com.smallcake.guess.bean.HeaderItem;
import com.smallcake.guess.bean.MultipleItem;
import com.smallcake.guess.databinding.ItemMultipleHeaderBinding;
import com.smallcake.guess.databinding.ItemMultipleHeaderRedVioletBinding;
import com.smallcake.guess.utils.DataBindingAdapter;

/**
 * Date: 2020/1/16
 * author: SmallCake
 *  1.banner
 *  2.游戏列表
 *  3.最新竞猜
 *  4.热门动态
 *  5.热门赛事
 *  6.热门战队
 *  7.热门视频
 *  8.最新资讯
 *  9.广告
 *  10.红紫头部
 *  11.底部白色圆角
 *  12.顶部白色圆角
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter() {
        super(null);
        addItemType(MultipleItem.HEADER, R.layout.item_multiple_header);
        addItemType(MultipleItem.HEADER_RED_VIOLET, R.layout.item_multiple_header_red_violet);
        addItemType(MultipleItem.TOP_ROUND_WHITE, R.layout.item_multiple_top_round_white);
        addItemType(MultipleItem.BOTTOM_ROUND_WHITE, R.layout.item_multiple_bottom_round_white);
        addItemType(MultipleItem.ADVERT, R.layout.item_multiple_advert);

        addItemType(MultipleItem.BANNER, R.layout.item_multiple_banner);//1.banner
        addItemType(MultipleItem.GAME_LIST, R.layout.item_multiple_game_list);//2.游戏列表
        addItemType(MultipleItem.GUESS, R.layout.item_multiple_new_guess);//3.最新竞猜
        addItemType(MultipleItem.HOT_TRENDS, R.layout.item_multiple_hot_trends);//4.热门动态
        addItemType(MultipleItem.HOT_MATCH, R.layout.item_multiple_hot_match);//5.热门赛事

        addItemType(MultipleItem.HOT_TEAM, R.layout.item_multiple_game_list);//6.热门战队
        addItemType(MultipleItem.HOT_VIDEO, R.layout.item_multiple_hot_trends);//7.热门视频
        addItemType(MultipleItem.NEWS, R.layout.item_multiple_hot_trends);//8.最新资讯
    }



     @Override
     protected void convert(BaseViewHolder helper, MultipleItem item) {
         try {
             switch (helper.getItemViewType()) {
                 case MultipleItem.HEADER:
                     if (helper.itemView.getTag()!=null){
                         ItemMultipleHeaderBinding bind = DataBindingUtil.bind(helper.itemView);
                         HeaderItem itemHeader = (HeaderItem) item;
                         bind.setItem(itemHeader);
                     }
                     break;
                 case MultipleItem.HEADER_RED_VIOLET:
                     if (helper.itemView.getTag()!=null){
                         ItemMultipleHeaderRedVioletBinding bind = DataBindingUtil.bind(helper.itemView);
                         HeaderItem itemHeader = (HeaderItem) item;
                         bind.setItem(itemHeader);
                     }
                     break;
                 case MultipleItem.BANNER:
                     break;
                 case MultipleItem.GAME_LIST:
                     GameItem gameItem = (GameItem) item;
                     DataBindingAdapter.bindImageCircleUrl(helper.getView(R.id.iv_logo),gameItem.getLogo());
                     break;
                 case MultipleItem.GUESS:
                     break;
                 case MultipleItem.HOT_TRENDS:
                     break;
                 case MultipleItem.HOT_MATCH://热门赛事
                     ImageView ivHotMatch = helper.getView(R.id.iv_hot_match);
                     DataBindingAdapter.bindImageRoundUrl(ivHotMatch,R.mipmap.banner2,5);
                     break;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

}