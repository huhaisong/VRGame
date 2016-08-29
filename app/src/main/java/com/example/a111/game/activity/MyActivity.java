package com.example.a111.game.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.a111.game.view.MySurfaceView;
import com.example.a111.game.R;

public class MyActivity extends Activity {

    private MySurfaceView mySurfaceView;
    private ImageView myImageView0,myImageView1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1111) {
                showImage();
            }
        }
    };

    private void showImage() {

        myImageView0.setVisibility(View.VISIBLE);
        myImageView1.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myImageView0.setVisibility(View.GONE);
                        myImageView1.setVisibility(View.GONE);
                    }
                });

            }
        }).start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为横屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //切换到主界面
        setContentView(R.layout.main);
        mySurfaceView = (MySurfaceView) findViewById(R.id.myView);
        myImageView0 = (ImageView) findViewById(R.id.imageView0);
        myImageView1 = (ImageView) findViewById(R.id.imageView1);
        mySurfaceView.setHandler(mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.onPause();
    }
}