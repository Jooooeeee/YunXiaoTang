package com.example.joe;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.db.CalendarInfo;
import com.example.joe.db.FoodName;
import com.example.joe.db.UserInfo;
import com.example.joe.gson.SaveDatas;
import com.example.joe.service.MyService;
import com.example.joe.util.MyApplication;
import com.example.joe.util.SystemUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.util.DateUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawerLayout;
    private CalendarView calendarView;
    private TextView tv_month_day;
    private TextView tv_current_day;
    private TextView tv_year;
    private TextView tv_lunar;
    private static DoublePicker doublePicker;
    private static DoublePicker doublePicker2;
    private static NumberPicker numberPicker;
    private List<String> options1Items=new ArrayList<>();
    private List<String> options2Items=new ArrayList<>();
    private List<String> options4Items=new ArrayList<>();
    private List<String> options3Items=new ArrayList<>();
    private String date;

    private List<UserInfo> userInfoList;
    private List<RecylerItem> recylerItems = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ImageButton nav_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isFirstUse()) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        List<FoodName> foodNames = LitePal.findAll(FoodName.class);
        if (foodNames.isEmpty()) {
            SaveDatas saveDatas = new SaveDatas();
            saveDatas.initFoodName();
            saveDatas.initFoodName2();
            saveDatas.initFoodName3();
            saveDatas.initFoodName4();
            saveDatas.initFoodName5();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        calendarView = findViewById(R.id.calendarView);
        tv_month_day=findViewById(R.id.tv_month_day);
        tv_current_day=findViewById(R.id.tv_current_day);
        recyclerView = findViewById(R.id.recyclerView);

        tv_year=findViewById(R.id.tv_year);
        tv_lunar=findViewById(R.id.tv_lunar);
        FrameLayout fl_current=findViewById(R.id.fl_current);
        nav_btn=findViewById(R.id.nav_btn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        initRecyler();
        RecylerAdapter adapter = new RecylerAdapter(recylerItems);

        mdrawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        initNav(navigationView);

        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        SelectCalendarView();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        options1Items.addAll(initData(20,149));
        options2Items.addAll(initData(0,9));
        options3Items.addAll(initData(0,20));
        options4Items.addAll(initData(0,99));

        doublePicker = new DoublePicker(MainActivity.this,options1Items,options2Items);
        numberPicker =new NumberPicker(MainActivity.this);
        doublePicker2 = new DoublePicker(MainActivity.this,options3Items,options4Items);

       //存日期
        date = calendarView.getCurYear() + "" + DateUtils.fillZero(calendarView.getCurMonth()) + "" + calendarView.getCurDay();
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("date", date);
        editor.apply();
        tv_current_day.setText(calendarView.getCurDay()+"");
        Intent startIntent  =   new Intent(this,MyService.class);
        startService(startIntent);
        alarm();
        nav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        fl_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.scrollToCurrent();
            }
        });

        isShowNotification();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mdrawerLayout.closeDrawers();
        switch (item.getItemId()){
            case R.id.nav_user_tui:
                Intent i=new Intent(MainActivity.this,WenZhangBtnActivity.class);
                startActivity(i);
                break;
            case R.id.nav_share:
                String imgPath=SystemUtils.getResourcesUri(this.getApplicationContext(),R.drawable.share);
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imgPath));
                startActivity(Intent.createChooser(intent, getTitle()));

                break;
        }
        return true;
    }

    public void initRecyler() {
        RecylerItem recylerItem = new RecylerItem(45);
        recylerItems.add(recylerItem);
    }

    public void SelectCalendarView() {
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                if (isClick) {
                    tv_month_day.setText("孕小糖·"+calendar.getMonth() + "月"+calendar.getDay() + "日");
                    tv_year.setText(calendar.getYear()+"");
                    tv_lunar.setText(calendar.getLunar());
                     update();
                     date= calendar.getYear()+""+DateUtils.fillZero(calendar.getMonth())+DateUtils.fillZero(calendar.getDay());
                    SharedPreferences.Editor editor=getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                    editor.putString("date",date);
                    editor.apply();
                    recyclerView.setAdapter(new RecylerAdapter(recylerItems));
                }
            }
        });
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {

            @Override
            public void onMonthChange(int year, int month) {
                Calendar calendar = calendarView.getSelectedCalendar();
                tv_month_day.setText("孕小糖·"+calendar.getMonth() + "月"+calendar.getDay()+ "日");
                tv_year.setText(calendar.getYear()+"");
                tv_lunar.setText(calendar.getLunar());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();

    }
    public static DoublePicker getDoublePicker(int i){

        DoublePicker doublePicker0;
        switch (i){
            case 1:
                doublePicker0= doublePicker;
                break;
            case 2:
                doublePicker0= doublePicker2;
                break;
                default:
                    doublePicker0= doublePicker;
                    break;
        }
        return doublePicker0;

    }
    public static NumberPicker getNumberPicker(){
        return numberPicker;
    }
    private List<String> initData(int start,int end){
        List<String> optionsItems=new ArrayList<>();
        for (int i=start;i<=end;i++){
            optionsItems.add(DateUtils.fillZero(i));
        }
        return optionsItems;
    }
    //判断是否第一次使用APP
    private boolean isFirstUse(){
        userInfoList=LitePal.findAll(UserInfo.class);
        if (userInfoList.isEmpty()){
            return true;
        }
        return false;
    }
    //初始化nav
    private void initNav(NavigationView navigationView){
        UserInfo userInfo=userInfoList.get(0);
        MenuItem item1=navigationView.getMenu().getItem(0);
        MenuItem item2=navigationView.getMenu().getItem(1);
        MenuItem item3=navigationView.getMenu().getItem(2);
        MenuItem item4=navigationView.getMenu().getItem(3);
        MenuItem item5=navigationView.getMenu().getItem(4);
        MenuItem item6=navigationView.getMenu().getItem(5);
        MenuItem item7=navigationView.getMenu().getItem(6);

        double bmiHeight=userInfo.getUserHeight()/100;
        String Bmi=String.format("%.1f",userInfo.getUserWeight()/(bmiHeight*bmiHeight));
        item1.setTitle(userInfo.getUsername());
        item2.setTitle("仙女的年龄不过才："+userInfo.getUserAge());
        item3.setTitle("仙女身高："+userInfo.getUserHeight()+"CM");
        item4.setTitle("这只是虚胖："+userInfo.getUserWeight()+"Kg");
        item5.setTitle("末次月经："+userInfo.getUserLastPeriod());
        item6.setTitle("预计宝宝出生："+userInfo.getUserDueData());
        item7.setTitle("BMI："+Bmi);
    }

    private void saveData(){
        CalendarInfo calendarInfo = new CalendarInfo();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String sportHour = preferences.getString("sportHour", "");
        String weight = preferences.getString("weight", "");
        String booldSugar = preferences.getString("booldSugar", "");
        String twoHourbooldSugar = preferences.getString("twoHourbooldSugar", "");
        String twoHourbooldSugarM = preferences.getString("twoHourbooldSugarM", "");
        String twoHourbooldSugarN = preferences.getString("twoHourbooldSugarN", "");
        LitePal.deleteAll(CalendarInfo.class,"calendarId = ?",date);
        int mood=preferences.getInt("mood",0);
        if (!weight.isEmpty()){
            double w = Double.parseDouble(weight);
            calendarInfo.setTodayWeight(w);
        }
        if (!sportHour.isEmpty()){
            double sport = Double.parseDouble(sportHour);
            calendarInfo.setTodaySport(sport);
        }
        if (!date.isEmpty()){
            calendarInfo.setCalendarId(Integer.parseInt(date));
        }
        if (mood!=0){
            calendarInfo.setTodayMethod(mood);
        }
        if (!booldSugar.isEmpty()){
            double booldSugars = Double.parseDouble(booldSugar);
            calendarInfo.setTodayBooldSugar(booldSugars);
        }
        if (!twoHourbooldSugar.isEmpty()){
            double twoHourbooldSugars = Double.parseDouble(twoHourbooldSugar);
            calendarInfo.setTodayTwoHourBooldSugar(twoHourbooldSugars);
        }
        if (!twoHourbooldSugarM.isEmpty()){
            double booldSugars = Double.parseDouble(twoHourbooldSugarM);
            calendarInfo.setTodayTwoHourBooldSugarM(booldSugars);
        }
        if (!twoHourbooldSugarN.isEmpty()){
            double booldSugars = Double.parseDouble(twoHourbooldSugarN);
            calendarInfo.setTodayTwoHourBooldSugarN(booldSugars);
        }
        calendarInfo.save();
    }
    private void update(){
        CalendarInfo calendarInfo = new CalendarInfo();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String sportHour = preferences.getString("sportHour", "");
        String weight = preferences.getString("weight", "");
        String booldSugar = preferences.getString("booldSugar", "");
        String twoHourbooldSugar = preferences.getString("twoHourbooldSugar", "");
        String twoHourbooldSugarM = preferences.getString("twoHourbooldSugarM", "");
        String twoHourbooldSugarN = preferences.getString("twoHourbooldSugarN", "");
        int mood=preferences.getInt("mood",0);
        if (!weight.isEmpty()){
            double w = Double.parseDouble(weight);
            calendarInfo.setTodayWeight(w);
        }
        if (!sportHour.isEmpty()){
            double sport = Double.parseDouble(sportHour);
            calendarInfo.setTodaySport(sport);
        }
        if (!date.isEmpty()){
            calendarInfo.setCalendarId(Integer.parseInt(date));
        }
        if (mood!=0){
            calendarInfo.setTodayMethod(mood);
        }
        if (!booldSugar.isEmpty()){
            double booldSugars = Double.parseDouble(booldSugar);
            calendarInfo.setTodayBooldSugar(booldSugars);
        }
        if (!twoHourbooldSugar.isEmpty()){
            double twoHourbooldSugars = Double.parseDouble(twoHourbooldSugar);
            calendarInfo.setTodayTwoHourBooldSugar(twoHourbooldSugars);
        }
        if (!twoHourbooldSugarM.isEmpty()){
            double booldSugars = Double.parseDouble(twoHourbooldSugarM);
            calendarInfo.setTodayTwoHourBooldSugarM(booldSugars);
        }
        if (!twoHourbooldSugarN.isEmpty()){
            double booldSugars = Double.parseDouble(twoHourbooldSugarN);
            calendarInfo.setTodayTwoHourBooldSugarN(booldSugars);
        }
        if(calendarInfo.saveOrUpdate("calendarId=?",date)){

        }
        else {
            Toast.makeText(MainActivity.this,"出现了很奇怪的错误，请使用必杀技-重开APP",Toast.LENGTH_SHORT).show();
        }
    }
    private void alarm(){
        AlarmManager am;
        Intent i2=new Intent("com.joe.example.broadcasttest.SHOW_NOTIFICATION");
        i2.putExtra("type","2");
        i2.setComponent( new ComponentName( "com.example.joe" ,
                "com.example.joe.service.ShowNotificationReceiver") );
        PendingIntent pi=PendingIntent.getBroadcast(this, 12, i2,0);
        long firstTime = SystemClock.elapsedRealtime();	//获取系统当前时间
        long systemTime = System.currentTimeMillis();//java.lang.System.currentTimeMillis()，它返回从 UTC 1970 年 1 月 1 日午夜开始经过的毫秒数。


        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //  这里时区需要设置一下，不然会有8个小时的时间差
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 19);//设置为19：00点提醒
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        //选择的定时时间
        long selectTime = calendar.getTimeInMillis();	//计算出设定的时间

        //  如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);
            selectTime = calendar.getTimeInMillis();
        }

        long time = selectTime - systemTime;// 计算现在时间到设定时间的时间差
        long my_Time = firstTime + time;//系统 当前的时间+时间差

        // 进行闹铃注册
        am=(AlarmManager)getSystemService(ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, my_Time, AlarmManager.INTERVAL_DAY, pi);
    }

    private void isShowNotification(){
        long day=SystemUtils.dateDiff(userInfoList.get(0).getUserLastPeriod(),calendarView.getCurYear() + "-" + DateUtils.fillZero(calendarView.getCurMonth()) + "-" + calendarView.getCurDay(),"yyyy-MM-dd");
        if (day<=14){
            SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
            String showNotifiDate=sharedPreferences.getString("curdate","a");
            if (!showNotifiDate.equals(calendarView.getCurYear()+"-"+DateUtils.fillZero(calendarView.getCurMonth())+"-"+calendarView.getCurDay())) {
                showNotification();
            }
        }
    }

    private void showNotification(){
        NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("my_channel_03", "渠道名字2", NotificationManager.IMPORTANCE_LOW);
            mChannel.enableLights(true);
            Log.i(TAG, mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(MainActivity.this)
                    .setChannelId("my_channel_03")
                    .setContentTitle("预约住院")
                    .setContentText("宝妈做好分娩准备，预约入院啦！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.logo)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.logo))
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setContentTitle("预约住院")
                    .setContentText("宝妈做好分娩准备，预约入院啦！")
                    .setSmallIcon(R.mipmap.logo)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.logo))
                    .setOngoing(true);

            notification = notificationBuilder.build();
        }
        notificationManager.notify(111125, notification);

        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("curdate",calendarView.getCurYear()+"-"+DateUtils.fillZero(calendarView.getCurMonth())+"-"+calendarView.getCurDay());
        editor.apply();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

