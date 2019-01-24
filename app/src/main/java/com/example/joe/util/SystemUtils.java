package com.example.joe.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class SystemUtils {
    public static boolean isAppaLive(Context context , String str) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list;
        list = am.getRunningTasks(100);
        boolean isAppRunning = false;

        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(str)
                    || info.baseActivity.getPackageName().equals(str)) {
                isAppRunning = true;
                Log.i("SystemUtils", info.topActivity.getPackageName()
                        + " info.baseActivity.getPackageName()="
                        + info.baseActivity.getPackageName());
                break;
            }
        }
        return isAppRunning;
    }

}
