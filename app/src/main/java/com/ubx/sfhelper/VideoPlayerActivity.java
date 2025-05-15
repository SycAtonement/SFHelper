package com.ubx.sfhelper;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        int videoNumber = getIntent().getIntExtra("video", 0);

        // 初始化VideoView和按钮
        videoView = findViewById(R.id.videoView);

        // 设置视频路径
//        if (videoNumber == 1) {
//            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ubx_video); // 更换为你的视频资源
//        } else if (videoNumber == 2) {
//            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dj_video); // 更换为你的视频资源
//        } else {
//            finish();
//        }

        // 设置MediaController
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // 设置视频URI
        videoView.setVideoURI(videoUri);

        playVideo();
    }

    private void playVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    private void stopVideo() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
            videoView.resume(); // 可选，可以重置视频为开始状态
            videoView.setVideoURI(videoUri); // 重新设置视频URI
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在活动销毁时停止视频播放
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}
