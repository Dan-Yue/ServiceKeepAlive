package com.example.danyu.servicekeepalive.system;

import android.app.Application;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by danyue on 2017/11/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        final Timer timer = new Timer();
        timer.schedule(task, 0, 5000);

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getApplicationContext().startService
                    (new Intent(getApplicationContext(), JobAwakenService.class));
        }
    };
}
