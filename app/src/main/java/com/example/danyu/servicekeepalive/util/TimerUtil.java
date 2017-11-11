package com.example.danyu.servicekeepalive.util;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by danyue on 2017/11/12.
 */
public class TimerUtil {
    private static String Tag = "TimerUtil";


    public static void startPolling(String tag) {
        Tag = tag;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.w(Tag, "" + (System.currentTimeMillis() / 1000));
            }
        };
        new Timer().schedule(task, 0, 5000);
    }
}
