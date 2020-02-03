package com.smallcake.guess.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.smallcake.guess.R;


/**
 * Date: 2020/1/4
 * author: SmallCake
 * 必选：
 * <include layout="@layout/common_action_bar"/>
 */
public abstract class BaseBindBarActivity<DB extends ViewDataBinding> extends BaseBindActivity {
    protected abstract int getLayoutId();
    protected DB db;
    protected TextView tvTitle;
    protected ImageView ivClose;
    protected ImageView ivRight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         db = DataBindingUtil.setContentView(this, getLayoutId());
         tvTitle = findViewById(R.id.tv_title);
         ivClose = findViewById(R.id.iv_close);
        ivRight = findViewById(R.id.iv_right);
         ivClose.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }
}
