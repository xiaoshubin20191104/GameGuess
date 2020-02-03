package com.smallcake.guess.bean;

/**
 * Date: 2020/1/17
 * author: SmallCake
 */
public class GameItem extends MultipleItem {
    public GameItem() {
        super(MultipleItem.GAME_LIST);
    }
    private int logo;

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
