package com.example.danyu.servicekeepalive.backstage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by danyue on 2017/11/12.
 */
public class LogService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String time = "" + (System.currentTimeMillis() / 1000);
        Log.e("LogService", time);
        return super.onStartCommand(intent, flags, startId);
    }
}

