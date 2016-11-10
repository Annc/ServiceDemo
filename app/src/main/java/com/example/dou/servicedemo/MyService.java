package com.example.dou.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Dou on 2016/11/10.
 */

public class MyService extends Service {
    private int num = 0;
    //创建MyBinder实例
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        //获取进程id， 获取线程Id
        Log.v("here", "Service进程ID是" + String.valueOf(android.os.Process.myPid()));
        Log.v("here","Service线程ID是" + String.valueOf(Thread.currentThread().getId()));
        num += 10;
        Log.v("here","Myservice onCreate");
    }
    /**Service的主要内容都写在onStartCommand方法中*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("here", "Myservice onStartCommand");
        num++;
        //service和ui处于同一个线程，耗时操作会导致UI阻塞，应该开启新线程
        try{
            Thread.sleep(16000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v("here","Myservice onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("here","Myservice bind");
        return null;
    }
    //创建一个binder类，实例化之后就可以在Onbind中返回
    class  MyBinder extends Binder{
        public int getNum(){
            return num;
        }
    }
}
