package com.wpmac.carinsrance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.wpmac.carinsrance.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wpmac on 16/4/6 14:52.
 * Email mzeht@outlook.com
 */
public class SplashActivity extends Activity {
    private Timer mTimer;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        className=this.getClass().getSimpleName();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);
                intent.putExtra("FromActivityName", className);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
