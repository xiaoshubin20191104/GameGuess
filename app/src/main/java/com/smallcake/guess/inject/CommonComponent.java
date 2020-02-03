package com.smallcake.guess.inject;


import com.smallcake.guess.base.BaseActivity;
import com.smallcake.guess.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Date: 2019/11/25
 * author: SmallCake
 * 注入器
 */
@Singleton
@Component(modules = NetWorkMoudle.class)
public interface CommonComponent {
    void inject(BaseFragment baseFragment);
    void inject(BaseActivity baseActivity);
}
