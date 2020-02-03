package com.smallcake.guess.bean;

/**
 * Date: 2020/1/16
 * author: SmallCake
 */
public class HeaderItem extends MultipleItem {
    private String title;
    public HeaderItem(String title) {
        super(MultipleItem.HEADER);
        this.title = title;
    }
    public HeaderItem(String title,int headStyle) {
        super(headStyle);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
