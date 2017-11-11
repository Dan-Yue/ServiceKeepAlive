package com.example.danyu.servicekeepalive.backstage;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.danyu.servicekeepalive.R;

public class MainActivity extends Activity {

    private Button bt_hehe;
    private NotificationManager notificationManager;
    private Notification notification;
    private int icon;
    private CharSequence tickerText;
    private long when;
    RemoteViews contentView;
    private Intent intent;
    private PendingIntent pendingIntent;
    private int notification_id = 0;
    private MyBroadCast receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initNotification();
    }

    private void initNotification() {
        receiver = new MyBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("a");
        filter.addAction("b");
        filter.addAction("c");
        filter.addAction("d");
        registerReceiver(receiver, filter);
        initView();
        initData();
    }


    private void initData() {
        icon = R.mipmap.ic_launcher; // 通知图标
        tickerText = "Hello"; // 状态栏显示的通知文本提示
        when = System.currentTimeMillis(); // 通知产生的时间，会在通知信息里显示
    }

    private void initView() {
        bt_hehe = (Button) findViewById(R.id.bt_hehe);
        bt_hehe.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 启动提示栏
                createNotification();
            }
        });
    }

    private void createNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification();
        notification.icon = icon;
        notification.tickerText = tickerText;
        notification.when = when;

        /***
         * 在这里我们用自定的view来显示Notification
         */
        contentView = new RemoteViews(getPackageName(),
                R.layout.notification_item);
        contentView.setTextViewText(R.id.text11, "小说");
        contentView.setTextViewText(R.id.text22, "视频");
        contentView.setTextViewText(R.id.text33, "新闻");
        contentView.setTextViewText(R.id.text44, "扯淡");

        notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻，不能滑动取消
        //默认跳转的主界面
        intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //自定义跳转
        contentView.setOnClickPendingIntent(R.id.text11, PendingIntent.getBroadcast(MainActivity.this, 11, new Intent().setAction("a"), PendingIntent.FLAG_UPDATE_CURRENT));
        contentView.setOnClickPendingIntent(R.id.text22, PendingIntent.getBroadcast(MainActivity.this, 11, new Intent().setAction("b"), PendingIntent.FLAG_UPDATE_CURRENT));
        contentView.setOnClickPendingIntent(R.id.text33, PendingIntent.getBroadcast(MainActivity.this, 11, new Intent().setAction("c"), PendingIntent.FLAG_UPDATE_CURRENT));
        contentView.setOnClickPendingIntent(R.id.text44, PendingIntent.getBroadcast(MainActivity.this, 11, new Intent().setAction("d"), PendingIntent.FLAG_UPDATE_CURRENT));
        notification.contentView = contentView;
        notification.contentIntent = pendingIntent;
        notificationManager.notify(notification_id, notification);
    }

    // 取消通知
    private void cancelNotification() {
        notificationManager.cancelAll();
    }


    @Override
    protected void onDestroy() {
        cancelNotification();
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键 onKeyDown()");
            cancelNotification();
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("a")) {
                Toast.makeText(MainActivity.this, "11111111111111",
                        Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals("b")) {
                Toast.makeText(MainActivity.this, "222222222222222",
                        Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals("c")) {
                Toast.makeText(MainActivity.this, "333333333333",
                        Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals("d")) {
                Toast.makeText(MainActivity.this, "4444444444444",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
