package com.example.dou.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mStart,mStop,mBind,mUnbind,mGetNum;
    private MyService.MyBinder mbinder;
    private Intent intent;

    //connection对象，绑定服务时使用
    private ServiceConnection connection = new ServiceConnection() {
        //服务于活动成功绑定时被调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v("here",name.toString());
            //参数service就是服务onBind方法的返回值，取出service就可以在活动中使用
            mbinder = (MyService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("here","Activity oncreate");
        //获取进程id
        Log.v("here","Activity的进程是" + String.valueOf(android.os.Process.myPid()));
        //获取线程ID
        Log.v("here","Activity的线程是" + String.valueOf(Thread.currentThread().getId()));
        init();
        intent = new Intent();
        //设置服务入口
        intent.setClass(MainActivity.this, MyService.class);
    }
    void init(){
        mStart = (Button)findViewById(R.id.start_service);
        mStop = (Button)findViewById(R.id.stopService);
        mBind = (Button)findViewById(R.id.bind);
        mUnbind = (Button)findViewById(R.id.unbind_service);
        mGetNum = (Button)findViewById(R.id.getNum);

        mStart.setOnClickListener(new btnListener());
        mStop.setOnClickListener(new btnListener());
        mUnbind.setOnClickListener(new btnListener());
        mBind.setOnClickListener(new btnListener());
        mGetNum.setOnClickListener(new btnListener());
    }
    //按钮监听器
    class btnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.start_service:
                    //启动服务
                    startService(intent);
                    break;
                case R.id.stopService:
                    //关闭服务
                    stopService(intent);
                    break;
                case R.id.bind:
                    //绑定服务
                    bindService(intent, connection, BIND_AUTO_CREATE);
                    break;
                case R.id.unbind_service:
                    //解除绑定
                    unbindService(connection);
                    break;
                case R.id.getNum:
                    //和服务通信,使用OnBind方法的返回值
                    Log.v("here",String.valueOf(mbinder.getNum()));
                    break;
                default:
                    break;
            }

        }
    }
}





















