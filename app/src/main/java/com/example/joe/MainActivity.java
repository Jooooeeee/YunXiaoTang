package com.example.joe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.joe.db.CalendarInfo;
import com.example.joe.db.FoodName;
import com.example.joe.db.UserInfo;
import com.example.joe.gson.SaveDatas;
import com.example.joe.service.MyService;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.util.DateUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawerLayout;
    private CalendarView calendarView;
    private TextView toolbar_month;
    private TextView toolbar_day;
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
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        calendarView = findViewById(R.id.calendarView);
        toolbar_month = findViewById(R.id.toolbar_month);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        toolbar_day = findViewById(R.id.toolbar_day);
        initRecyler();
        RecylerAdapter adapter = new RecylerAdapter(recylerItems);

        mdrawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        }

        navigationView.setCheckedItem(R.id.nav_user_name);
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

        Intent startIntent  =   new Intent(this,MyService.class);
        startService(startIntent);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_user_tui:
                        Intent i=new Intent(MainActivity.this,WenZhangBtnActivity.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mdrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mdrawerLayout.closeDrawers();
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
                    toolbar_month.setText(calendar.getMonth() + "月");
                    toolbar_day.setText(calendar.getDay() + "日");
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
                toolbar_month.setText(month + "月");
                Calendar calendar = calendarView.getSelectedCalendar();
                toolbar_day.setText(calendar.getDay() + "日");
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
        item1.setTitle("本仙女的名称是："+userInfo.getUsername());
        item2.setTitle("本仙女的年龄不过才："+userInfo.getUserAge());
        item3.setTitle("本仙女高达："+userInfo.getUserHeight());
        item4.setTitle("这只是虚胖："+userInfo.getUserWeight());
        item5.setTitle("末次大姨妈："+userInfo.getUserLastPeriod());
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
            calendarInfo.updateAll("calendarId=?",date);

    }
}

