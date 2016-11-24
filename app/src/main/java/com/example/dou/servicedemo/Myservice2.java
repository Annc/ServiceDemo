package com.example.dou.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

/**
 * Created by doudo on 2016/11/24.
 */

public class Myservice2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 前台服务
         * 创建通知实例
         * 图标
         *内容
         * 跳转
         *开启
         */
        Notification notification = new Notification();
        notification.icon = R.drawable.updater;
        notification.contentView = new RemoteViews(getPackageName(), R.layout.notify);
        notification.contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
