package com.smallcake.guess.ui;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.smallcake.guess.R;
import com.smallcake.guess.base.BaseBindBarActivity;
import com.smallcake.guess.databinding.ActivityVideoInfoBinding;
import com.smallcake.utils.DpPxUtils;
import com.smallcake.utils.L;

public class VideoInfoActivity extends BaseBindBarActivity<ActivityVideoInfoBinding> {
    private static final String TAG = "VideoInfoActivity";

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
                    case R.id.btn_stop:
                       db.videoView.stopPlayback();
                        break;
                }
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
    }
    MediaController mediaController;
    @SuppressLint("ClickableViewAccessibility")
    private void initVideo() {

        //初始化videoview控制条
         mediaController = new MediaController(this);
        //设置videoview的控制条
        db.videoView.setMediaController(mediaController);
        //设置显示控制条
        mediaController.show(0);
        //设置播放完成以后监听
        db.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                db.videoView.start();
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
                    getVideoInfo();
                    mHandler.sendEmptyMessage(1);
                    break;
                case 1:
                    if (!db.videoView.isPlaying())break;
                    int arg1 = msg.arg1;
                    L.e("当前播放进度=="+arg1);
                    int duration = db.videoView.getDuration();
                    int videoTime = duration/1000;
                    db.tvTime.setText("时间："+arg1+"/"+videoTime);
                    sendProgress();
                    break;
            }

            return false;
        }
    });

    private void sendProgress() {
        int currentPosition = db.videoView.getCurrentPosition();
        int time = currentPosition/1000;
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.arg1 = time;
        mHandler.sendMessageDelayed(message, 50);
    }

    private void getVideoInfo() {
        int duration = db.videoView.getDuration();
        int videoTime = duration/1000+(duration%1000>500?1:0);
        L.e(TAG,"视频时长=="+videoTime+"秒");
    }
}
