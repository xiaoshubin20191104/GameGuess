package com.smallcake.guess.inject;


import com.smallcake.guess.impl.AdvertImpl;

import javax.inject.Inject;

/**
 * Date: 2019/11/25
 * author: SmallCake
 * 数据提供者
 */
public class DataProvider {
    @Inject
    public DataProvider(){}
    @Inject
    public AdvertImpl ad;

}
