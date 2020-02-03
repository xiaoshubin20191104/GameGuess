package com.smallcake.guess.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.smallcake.guess.inject.DaggerCommonComponent;
import com.smallcake.guess.inject.DataProvider;
import com.smallcake.guess.inject.NetWorkMoudle;
import com.smallcake.utils.ActivityCollector;

import javax.inject.Inject;


/**
 * Date: 2020/1/3
 * author: SmallCake
 * Dagger2不支持Activity泛型，所以使用{@link BaseBindActivity}继承此类来引入DataBind
 * 1.注入了内容提供者{@link DataProvider}
 * 2.使用{@link ActivityCollector}对页面进行统一管理
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    protected DataProvider dataProvider;
    protected abstract int getLayoutId();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(getLayoutId());
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).init();
        DaggerCommonComponent.builder().netWorkMoudle(new NetWorkMoudle(this)).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    protected void goActivity(Class<?> clz){
        startActivity(new Intent(this,clz));
    }

}
