package com.smallcake.guess.ui;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.databinding.ActivityVideoInfoBinding;
import com.smallcake.utils.DpPxUtils;
import com.smallcake.utils.L;

import java.util.Formatter;
import java.util.Locale;

import androidx.annotation.NonNull;

/**
 * 视频详情
 */
public class VideoInfoActivity extends BaseBindBarActivity<ActivityVideoInfoBinding> {
    private static final String TAG = "VideoInfoActivity";
    private int totalTime;//视频总时间
    StringBuilder mFormatBuilder;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initVideo();
        onEvent();
    }

    private void onEvent() {
        db.setClicklistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_full_screen:
                        setVideoViewLayoutParams(db.videoView,1);
                        break;
                    case R.id.btn_little_screen:
                        setVideoViewLayoutParams(db.videoView,2);
                        break;
                    case R.id.btn_pause:
                        if (db.videoView.isPlaying()) db.videoView.pause();
                        break;
                    case R.id.btn_play:
                        if (!db.videoView.isPlaying())db.videoView.start();
                        break;
                }
            }
        });
        db.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 设置videiview的全屏和窗口模式
     *
     * @param paramsType 标识 1为全屏模式 2为窗口模式
     */
    public void setVideoViewLayoutParams(VideoView videoView,int paramsType) {
        //全屏模式
        if (1 == paramsType) {
            //设置充满整个父布局
            RelativeLayout.LayoutParams LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            //设置相对于父布局四边对齐
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            //为VideoView添加属性
            videoView.setLayoutParams(LayoutParams);

        } else {
            //窗口模式
            //获取整个屏幕的宽高
            DisplayMetrics DisplayMetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(DisplayMetrics);
            //设置窗口模式距离边框50
            int videoHeight = DisplayMetrics.heightPixels;
            int videoWidth = DisplayMetrics.widthPixels;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(videoWidth/2, videoHeight/2);
            //设置居中
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.setMargins(0,0,DpPxUtils.dp2px(16),0);
            //为VideoView添加属性
            videoView.setLayoutParams(layoutParams);
        }
    }

    private void initView() {
        tvTitle.setText("视频详情");
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }
    MediaController mediaController;
    @SuppressLint("ClickableViewAccessibility")
    private void initVideo() {

        //初始化videoview控制条
//         mediaController = new MediaController(this);
        //设置videoview的控制条
//        db.mediaController.setAnchorView(db.videoView);
//        db.videoView.setMediaController(db.mediaController);
        //设置显示控制条
//        mediaController.show(8000);
        //设置播放完成以后监听
        db.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        //设置发生错误监听，如果不设置videoview会向用户提示发生错误
        db.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        //设置在视频文件在加载完毕以后的回调函数
        db.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                totalTime = db.videoView.getDuration()/1000;
                db.seekBar.setMax(totalTime);
                db.videoView.start();
                mHandler.sendEmptyMessage(0);
            }
        });
        //设置videoView的点击监听
        db.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //设置网络视频路径
        Uri uri = Uri.parse("https://www.w3school.com.cn/example/html5/mov_bbb.mp4");
        db.videoView.setVideoURI(uri);



    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int what = msg.what;
            switch (what){
                case 0://视频加载完毕，开始播放
                    mHandler.sendEmptyMessage(1);
                    break;
                case 1:
                    if (!db.videoView.isPlaying())break;//如果当前视频未播放，不发送进度通知
                    int arg1 = msg.arg1;
//                    L.e("当前播放进度=="+arg1);
                    db.seekBar.setProgress(arg1/1000);
                    db.tvTime.setText("时间："+stringForTime(arg1)+"/"+totalTime);
                    sendProgress();
                    break;
            }

            return false;
        }
    });
    //发送播放时间进度
    private void sendProgress() {
        int position =  db.videoView.getCurrentPosition();
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.arg1 = position;
        mHandler.sendMessageDelayed(message, 500);
    }
    Formatter mFormatter;
    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            L.e("竖屏=====");
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,DpPxUtils.dp2px(235f));
//            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            params.addRule(RelativeLayout.BELOW,R.id.include3);
            db.videoView.setLayoutParams(params);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            L.e("横屏=====");
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //设置相对于父布局四边对齐
            RelativeLayout.LayoutParams LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            db.videoView.setLayoutParams(LayoutParams);
        }
    }
}
