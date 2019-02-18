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

import com.example.joe.db.IsStartNotifi;
import com.example.joe.db.UserInfo;
import com.example.joe.gson.SaveDatas;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyService extends Service {
    private Date date;
    private int notifiTimes[]=new int[11];
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
            List<IsStartNotifi> isStartNotifis=LitePal.findAll(IsStartNotifi.class);
            if (!isStartNotifis.isEmpty()){
                IsStartNotifi isStartNotifi=isStartNotifis.get(0);
                initNotifiTimes(isStartNotifi);
            }
            AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
            Intent i2=new Intent("com.joe.example.broadcasttest.SHOW_NOTIFICATION");
            i2.putExtra("type","1");
            i2.setComponent( new ComponentName( "com.example.joe" ,
                    "com.example.joe.service.ShowNotificationReceiver") );

            long timeInMills[]=initCalendar();
            for (int i=0;i<notifiTimes.length;i++){

                if (notifiTimes[i]==SaveDatas.NOUSER){
                    PendingIntent pendingIntent = PendingIntent.
                            getBroadcast(this, i+1, i2, PendingIntent.FLAG_ONE_SHOT);
                    manager.set(AlarmManager.RTC,timeInMills[i],pendingIntent);
                    //pendingIntent.cancel();
                    Log.e("notifiTimes", "onStartCommand: "+timeInMills[i]);
                }
                Log.e("notifiTimes", "onStartCommand: "+notifiTimes[i] );
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void initNotifiTimes(IsStartNotifi isStartNotifi){
            notifiTimes[0] = isStartNotifi.getSix();
            notifiTimes[1] = isStartNotifi.getFourteen();
            notifiTimes[2] = isStartNotifi.getTwenty();
            notifiTimes[3] = isStartNotifi.getTwenty_four();
            notifiTimes[4] = isStartNotifi.getTwenty_eight();
            notifiTimes[5] = isStartNotifi.getThirty_two();
            notifiTimes[6] = isStartNotifi.getThirty_seven();
            notifiTimes[7] = isStartNotifi.getThirty_eight();
            notifiTimes[8] = isStartNotifi.getThirty_nine();
            notifiTimes[9] = isStartNotifi.getForty();
            notifiTimes[10] = isStartNotifi.getForty_one();
    }
    private long[] initCalendar(){


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //long nowTimeinMills=calendar.getTimeInMillis();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, 42);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.setTimeInMillis(calendar.getTimeInMillis());
        calendar1.add(Calendar.DATE, 56);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.setTimeInMillis(calendar.getTimeInMillis());
        calendar2.add(Calendar.DATE, 98);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date);
        calendar3.setTimeInMillis(calendar.getTimeInMillis());
        calendar3.add(Calendar.DATE, 126);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date);
        calendar4.setTimeInMillis(calendar.getTimeInMillis());
        calendar4.add(Calendar.DATE, 154);
        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTime(date);
        calendar5.setTimeInMillis(calendar.getTimeInMillis());
        calendar5.add(Calendar.DATE, 182);
        Calendar calendar6 = Calendar.getInstance();
        calendar6.setTime(date);
        calendar6.setTimeInMillis(calendar.getTimeInMillis());
        calendar6.add(Calendar.DATE, 217);
        Calendar calendar7 = Calendar.getInstance();
        calendar7.setTime(date);
        calendar7.setTimeInMillis(calendar.getTimeInMillis());
        calendar7.add(Calendar.DATE, 224);
        Calendar calendar8 = Calendar.getInstance();
        calendar8.setTime(date);
        calendar8.setTimeInMillis(calendar.getTimeInMillis());
        calendar8.add(Calendar.DATE, 231);
        Calendar calendar9 = Calendar.getInstance();
        calendar9.setTime(date);
        calendar9.setTimeInMillis(calendar.getTimeInMillis());
        calendar9.add(Calendar.DATE, 238);
        Calendar calendar10 = Calendar.getInstance();
        calendar10.setTime(date);
        calendar10.setTimeInMillis(calendar.getTimeInMillis());
        calendar10.add(Calendar.DATE, 245);
        long calendarTimeInMillis=calendar.getTimeInMillis();
        long timeInMills [] = {calendarTimeInMillis,calendar1.getTimeInMillis(),calendar2.getTimeInMillis(),calendar3.getTimeInMillis(),calendar4.getTimeInMillis(),
                calendar5.getTimeInMillis(),calendar6.getTimeInMillis(),calendar7.getTimeInMillis(),calendar8.getTimeInMillis(),calendar9.getTimeInMillis(),calendar10.getTimeInMillis()};
        return timeInMills;
    }
}
