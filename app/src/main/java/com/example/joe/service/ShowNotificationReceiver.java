package com.example.joe.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.joe.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ShowNotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "showNotification";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "ShowNotificationReceiver onReceive");


        //设置点击通知栏的动作为启动另外一个广播
        Intent broadcastIntent = new Intent("com.joe.example.broadcasttest.NOTIFICATION");
        broadcastIntent.setComponent( new ComponentName( "com.example.joe" ,
                "com.example.joe.service.NotificationReceiver") );
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
//        mBuilder.setContentTitle("产检通知")//设置通知栏标题
//                .setContentText("宝妈该去产检啦") //设置通知栏显示内容
//                .setContentIntent(pendingIntent) //设置通知栏点击意图
//                .setTicker("产检通知来啦") //通知首次出如今通知栏，带上升动画效果的
//                .setWhen(System.currentTimeMillis())//通知产生的时间。会在通知信息里显示，通常是系统获取到的时间
//                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
//	.setAutoCancel(true)//设置这个标志当用户单击面板就能够让通知将自己主动取消
//                .setOngoing(false)//ture。设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极參与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知加入声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，能够组合
//                .setSmallIcon(R.mipmap.logo);//设置通知小ICON

        String id = "my_channel_01";
        String name="我是渠道名字";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            Log.i(TAG, mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(context)
                    .setChannelId(id)
                    .setContentTitle("产检通知")
                    .setContentText("宝妈该去产检啦")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.logo)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.logo))
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setContentTitle("产检通知")
                    .setContentText("宝妈该去产检啦")
                    .setSmallIcon(R.mipmap.logo)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.logo))
                    .setOngoing(true);

            notification = notificationBuilder.build();
        }

        notificationManager.notify(111123, notification);
        Log.i("repeat", "showNotification");

    }

}
