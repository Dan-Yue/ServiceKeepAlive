package com.example.danyu.servicekeepalive.futility;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.danyu.servicekeepalive.R;
import com.example.danyu.servicekeepalive.backstage.MainActivity;

/**
 * Created by danyue on 2017/11/12.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notifyIntent,0);

        Notification notification = new Notification.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle("通知").
                setContentText("我是服务的通知").
                setContentIntent(pendingIntent).
                setDefaults(Notification.DEFAULT_ALL). // 设置用手机默认的震动或声音来提示
                build();

        // 设置为前台服务,在系统状态栏显示
        startForeground(1, notification);

        super.onCreate();
    }
}