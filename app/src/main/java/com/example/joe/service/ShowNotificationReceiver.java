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
import com.example.joe.db.IsStartNotifi;
import com.example.joe.db.UserInfo;
import com.example.joe.gson.SaveDatas;
import com.example.joe.util.SystemUtils;

import org.litepal.LitePal;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import cn.qqtheme.framework.util.DateUtils;

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
        long times;
        UserInfo userInfo=null;
        List<UserInfo> list=LitePal.findAll(UserInfo.class);

        if (!list.isEmpty()){
            userInfo=list.get(0);
            Calendar c=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            int year = c.get(Calendar.YEAR);    //获取年
            String month = DateUtils.fillZero(c.get(Calendar.MONTH) + 1);   //获取月份，0表示1月份
            int curday = c.get(Calendar.DAY_OF_MONTH);    //获取当前天数
           long day=SystemUtils.dateDiff(userInfo.getUserLastPeriod(),year+"-"+month+"-"+curday,"yyyy-MM-dd");

           times=day/7;
            Log.e(TAG, "onReceive: "+times);
           times=getTimes(times);

        }else {
            times=6;
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
                        .setContentText("宝妈该去第" + times  + "周产检啦")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .build();
            } else {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                        .setContentTitle("产检通知")
                        .setContentText("宝妈该去第" + times  + "周产检啦")
                        .setSmallIcon(R.mipmap.logo)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo))
                        .setOngoing(true);

                notification = notificationBuilder.build();
            }

            notificationManager.notify(111123, notification);
            List<IsStartNotifi> isStartNotifiLists=LitePal.findAll(IsStartNotifi.class);
            IsStartNotifi isStartNotifi=isStartNotifiLists.get(0);
            saveNotifi(times,isStartNotifi);
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

    private void saveNotifi(long times,IsStartNotifi isStartNotifi){
        if (times>=6&&times<=13){
            isStartNotifi.setSix(SaveDatas.HAVEUSE);
        }
        else if (times>=14&&times<=19){
            isStartNotifi.setFourteen(SaveDatas.HAVEUSE);
        }
        else if (times>=20&&times<=23){
            isStartNotifi.setTwenty(SaveDatas.HAVEUSE);
        }
        else if (times>=24&&times<=27){
            isStartNotifi.setTwenty_four(SaveDatas.HAVEUSE);
        }
        else if (times>=28&&times<=31){
            isStartNotifi.setTwenty_eight(SaveDatas.HAVEUSE);
        } else if (times>=32&&times<=36){
            isStartNotifi.setThirty_two(SaveDatas.HAVEUSE);
        }if (times==37){
            isStartNotifi.setThirty_seven(SaveDatas.HAVEUSE);
        }
        if (times==38){
            isStartNotifi.setThirty_eight(SaveDatas.HAVEUSE);
        }
        if (times==39){
            isStartNotifi.setThirty_nine(SaveDatas.HAVEUSE);
        }
        if (times==40){
            isStartNotifi.setForty(SaveDatas.HAVEUSE);
        }
        if (times==41){
            isStartNotifi.setForty_one(SaveDatas.HAVEUSE);
        }
        isStartNotifi.save();
    }
    private long getTimes(long times){
        if (times>=6&&times<=13){
            times=6;
        }
        else if (times>=14&&times<=19){
            times=14;
            Log.e(TAG, "getTimes: " );
        }
        else if (times>=20&&times<=23){
            times=20;
        }
        else if (times>=24&&times<=27){
            times=24;
        }
        else if (times>=28&&times<=31){
            times=28;
        }
        else if (times>=32&&times<=36){
            times=32;
        }

        return times;


    }
}
