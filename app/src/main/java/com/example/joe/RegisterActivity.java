package com.example.joe;


import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.joe.db.IsStartNotifi;
import com.example.joe.db.UserInfo;
import com.example.joe.gson.SaveDatas;
import com.example.joe.util.MyApplication;
import org.litepal.LitePal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private EditText userDueData;
    private EditText userLastPeriod;
    private String userDueData2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Toolbar toolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            RegisterActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        Button register=findViewById(R.id.register);
        final EditText  userName=findViewById(R.id.userName);
        final EditText userAge=findViewById(R.id.userAge);
        userDueData=findViewById(R.id.userDueData);
        final EditText userHeight=findViewById(R.id.userHeight);
        userLastPeriod=findViewById(R.id.userLastPeriod);
        final EditText userWeight=findViewById(R.id.userWeight);


        userDueData.setEnabled(false);
        userLastPeriod.setCursorVisible(false);
        userLastPeriod.setFocusable(false);
        userLastPeriod.setFocusableInTouchMode(false);

        userLastPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final DatePicker picker = new DatePicker(RegisterActivity.this);
                dataPicker(picker);
                picker.show();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String userName2=userName.getText().toString();
                 String userAge2=userAge.getText().toString();
                 String userHeight2=userHeight.getText().toString();
                 String userLastPeriod2=userLastPeriod.getText().toString();
                 String userWeight2=userWeight.getText().toString();
                UserInfo userInfo=new UserInfo();
                Log.e(TAG, "onClick: "+userDueData2);
                if (!(userName2.isEmpty())&&!(userAge2.isEmpty())&&!(userHeight2.isEmpty())&&!(userLastPeriod2.isEmpty())&&!(userWeight2.isEmpty())&&!(userDueData2.isEmpty())){
                   try {
                       userInfo.setUserAge(Integer.parseInt(userAge2));
                       userInfo.setUserDueData(userDueData2);
                       userInfo.setUserHeight(Double.parseDouble(userHeight2));
                       userInfo.setUserWeight(Double.parseDouble(userWeight2));
                       userInfo.setUserLastPeriod(userLastPeriod2);
                       userInfo.setUsername(userName2);
                       userInfo.setUserId(1);
                       userInfo.setTimes(0);
                       if (userInfo.save()){
                           finish();
                       }
                       else {
                           Toast.makeText(MyApplication.getContext(),"出现了很奇怪的错误，请使用必杀技-重开APP",Toast.LENGTH_SHORT).show();
                       }
                   }
                   catch (Exception e){
                       Toast.makeText(MyApplication.getContext(),"是不是在身高、体重、年龄中多输入了空格或者文字？",Toast.LENGTH_SHORT).show();
                   }

                }
                else {
                    Toast.makeText(MyApplication.getContext(),"小孩子才选填，大人都是全填的",Toast.LENGTH_SHORT).show();
                }
            }
        });
        List<IsStartNotifi> isStartNotifis=LitePal.findAll(IsStartNotifi.class);
        if (isStartNotifis.isEmpty()){
            SaveDatas saveDatas=new SaveDatas();
            saveDatas.initIsStartNotifi();
        }
    }

    public void dataPicker(final DatePicker picker ){
        picker.setTopPadding(2);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2019, 1, 21);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {

                String createDate = year+"-"+month+"-"+day;
                userLastPeriod.setText(createDate);
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy-MM-dd");
                try {
                    Date date = sdf.parse(createDate);
                    userDueData2=extendDate(date,280,sdf);
                    userDueData.setText("预产期："+userDueData2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
    }

    public String extendDate (Date date, int day,SimpleDateFormat sdf)
    {
        Calendar out = Calendar.getInstance();
        out.setTime(date);
        for (int i=0;i<day;i++) {
            out.setTimeInMillis(out.getTimeInMillis() + (86400000 * 1));
        }
        String dateStr = sdf.format(out.getTime());
        return dateStr;
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
