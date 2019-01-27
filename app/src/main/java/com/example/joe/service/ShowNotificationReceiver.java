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

import com.example.joe.R;
import com.example.joe.db.UserInfo;

import org.litepal.LitePal;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ShowNotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "showNotification";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "ShowNotificationReceiver onReceive");
        String type=intent.getStringExtra("type");


        //设置点击通知栏的动作为启动另外一个广播
        Intent broadcastIntent = new Intent("com.joe.example.broadcasttest.NOTIFICATION");
        broadcastIntent.setComponent( new ComponentName( "com.example.joe" ,
                "com.example.joe.service.NotificationReceiver") );
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String id = "my_channel_01";
        String name="我是渠道名字";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (type.equals("1")) {
        int times;
        UserInfo userInfo=null;
        List<UserInfo> list=LitePal.findAll(UserInfo.class);
        String nickName="";
        if (!list.isEmpty()){
            userInfo=list.get(0);
            times=userInfo.getTimes();
            nickName=userInfo.getUsername();
        }else {
            times=0;
        }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
                mChannel.enableLights(true);
                mChannel.enableVibration(true);
                Log.i(TAG, mChannel.toString());
                notificationManager.createNotificationChannel(mChannel);
                notification = new Notification.Builder(context)
                        .setChannelId(id)
                        .setContentTitle("产检通知")
                        .setContentText("宝妈该去第" + times + 1 + "产检啦")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .build();
            } else {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                        .setContentTitle("产检通知")
                        .setContentText("宝妈该去第" + times + 1 + "产检啦")
                        .setSmallIcon(R.mipmap.logo)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .setOngoing(true);

                notification = notificationBuilder.build();
            }


            userInfo.setTimes(++times);
            userInfo.saveOrUpdate("username=?", nickName);
            notificationManager.notify(111123, notification);
            Log.i("repeat", "showNotification");
        }
        else if (type.equals("2")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel("my_channel_02", "渠道名字", NotificationManager.IMPORTANCE_DEFAULT);
                mChannel.enableLights(true);
                mChannel.enableVibration(true);
                Log.i(TAG, mChannel.toString());
                notificationManager.createNotificationChannel(mChannel);
                notification = new Notification.Builder(context)
                        .setChannelId(id)
                        .setContentTitle("运动通知")
                        .setContentText("宝妈该去运动啦！")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .build();
            } else {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                        .setContentTitle("运动通知")
                        .setContentText("宝妈该去运动啦！")
                        .setSmallIcon(R.mipmap.logo)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .setOngoing(true);

                notification = notificationBuilder.build();
            }
            notificationManager.notify(111124, notification);
            Log.i("repeat", "showNotification");
        }

    }

}
