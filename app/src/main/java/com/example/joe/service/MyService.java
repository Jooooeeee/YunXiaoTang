package com.example.joe.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.example.joe.db.UserInfo;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyService extends Service {
    private Date date;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        List<UserInfo> userInfos=LitePal.findAll(UserInfo.class);
        if (!userInfos.isEmpty()) {
            String dueData =userInfos.get(0).getUserLastPeriod();
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd");
            try {
                date = sdf.parse(dueData);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            stopSelf();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(date!=null){

            AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
            Intent i2=new Intent("com.joe.example.broadcasttest.SHOW_NOTIFICATION");
            i2.putExtra("type","1");
            i2.setComponent( new ComponentName( "com.example.joe" ,
                    "com.example.joe.service.ShowNotificationReceiver") );
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.setTimeInMillis(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 42);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Log.e("onStartCommand", "onStartCommand: "+ sdf.format(calendar.getTime()) );
            Log.e("onStartCommand", "onStartCommand: "+ sdf.format(date) );
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar1.setTimeInMillis(calendar.getTimeInMillis());
            calendar1.add(Calendar.DATE, 98);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
            calendar2.add(Calendar.DATE, 140);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date);
            calendar3.setTimeInMillis(calendar.getTimeInMillis());
            calendar3.add(Calendar.DATE, 168);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTime(date);
            calendar4.setTimeInMillis(calendar.getTimeInMillis());
            calendar4.add(Calendar.DATE, 196);
            Calendar calendar5 = Calendar.getInstance();
            calendar5.setTime(date);
            calendar5.setTimeInMillis(calendar.getTimeInMillis());
            calendar5.add(Calendar.DATE, 224);
            Calendar calendar6 = Calendar.getInstance();
            calendar6.setTime(date);
            calendar6.setTimeInMillis(calendar.getTimeInMillis());
            calendar6.add(Calendar.DATE, 259);
            Calendar calendar7 = Calendar.getInstance();
            calendar7.setTime(date);
            calendar7.setTimeInMillis(calendar.getTimeInMillis());
            calendar7.add(Calendar.DATE, 266);
            Calendar calendar8 = Calendar.getInstance();
            calendar8.setTime(date);
            calendar8.setTimeInMillis(calendar.getTimeInMillis());
            calendar8.add(Calendar.DATE, 273);
            Calendar calendar9 = Calendar.getInstance();
            calendar9.setTime(date);
            calendar9.setTimeInMillis(calendar.getTimeInMillis());
            calendar9.add(Calendar.DATE, 280);
            long timeInMills [] = {calendar.getTimeInMillis(),calendar1.getTimeInMillis(),calendar2.getTimeInMillis(),calendar3.getTimeInMillis(),calendar4.getTimeInMillis(),
                    calendar5.getTimeInMillis(),calendar6.getTimeInMillis(),calendar7.getTimeInMillis(),calendar8.getTimeInMillis(),calendar9.getTimeInMillis()};

            for (int i=1;i<11;i++){
                PendingIntent pendingIntent = PendingIntent.
                        getBroadcast(this, i, i2, PendingIntent.FLAG_UPDATE_CURRENT);
                manager.set(AlarmManager.RTC,timeInMills[i-1],pendingIntent);
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }
}
