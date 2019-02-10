package com.example.joe;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.joe.db.CalendarInfo;
import com.example.joe.db.UserInfo;
import com.example.joe.util.MyApplication;
import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.login.LoginException;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.util.DateUtils;


public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {
       private List<RecylerItem> mRecylerList;

       private final List<String> options1Items=new ArrayList<>();
       private final List<String> options2Items=new ArrayList<>();
       private final List<String> options3Items=new ArrayList<>();
       private final List<String> options4Items=new ArrayList<>();
       private double weight,sportHourt,booldSugar,twoHourbooldSugar,twoHourbooldSugarM,twoHourbooldSugarN;
       private int mood;
       private long yun_week,yun_day;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View recyclerView;
        public ViewHolder(View view){
            super(view);
           recyclerView=view;

        }
    }

    public RecylerAdapter(List<RecylerItem> mRecylerList) {
        this.mRecylerList = mRecylerList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        final RecylerAdapter.ViewHolder holder=new ViewHolder(view);
        //控件初始化
        final TextView imageaTextview_weight=view.findViewById(R.id.imageaTextview_weight);
        final TextView imageTextviewEnergy=view.findViewById(R.id.imageTextviewEnergy);
        final TextView imgerTextviewSport=view.findViewById(R.id.imageaTextviewSport);
        final TextView textView=view.findViewById(R.id.textSport);
        final TextView imageaBooldSugar=view.findViewById(R.id.imageaBooldSugar);
        final TextView imageaTwoHourBooldSugar=view.findViewById(R.id.imageaTwoHourBooldSugar);
        final TextView imageaTwoHourBooldSugarN=view.findViewById(R.id.imageaTwoHourBooldSugarN);
        final TextView imageaTwoHourBooldSugarM=view.findViewById(R.id.imageaTwoHourBooldSugarM);
        final RadioGroup radioGroup=view.findViewById(R.id.radioGroup);

        final TextView tv_yun_week=view.findViewById(R.id.tv_yun_week);




        SpannableString spannableString;
        SpannableString spannableString2;
        SpannableString spannableString3;
        SpannableString spannableString4;
        SpannableString spannableString4M;
        SpannableString spannableString4N;
        //关闭APP之后数据恢复
        initData(parent);
        options1Items.addAll(initData(20,149));
        options2Items.addAll(initData(0,9));
        options3Items.addAll(initData(0,20));
        options4Items.addAll(initData(0,99));

        Drawable drawable=parent.getContext().getResources().getDrawable(R.drawable.ic_more_horiz_black_24dp);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);


        if (weight!=0.0){
            spannableString=new SpannableString(weight+"kg");
        }else
        {
            spannableString=new SpannableString("A");
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (sportHourt!=0.0){
            spannableString2=new SpannableString(sportHourt+"h");

        }else {
            spannableString2=new SpannableString("A");
            spannableString2.setSpan(imageSpan,0,1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (booldSugar!=0.0){
            spannableString3=new SpannableString(booldSugar+"h");

        }else {
            spannableString3=new SpannableString("A");
            spannableString3.setSpan(imageSpan,0,1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (twoHourbooldSugar!=0.0){
            spannableString4=new SpannableString(twoHourbooldSugar+"h");

        }else {
            spannableString4=new SpannableString("A");
            spannableString4.setSpan(imageSpan,0,1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (twoHourbooldSugarM!=0.0){
            spannableString4M=new SpannableString(twoHourbooldSugarM+"h");

        }else {
            spannableString4M=new SpannableString("A");
            spannableString4M.setSpan(imageSpan,0,1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (twoHourbooldSugarM!=0.0){
            spannableString4N=new SpannableString(twoHourbooldSugarN+"h");

        }else {
            spannableString4N=new SpannableString("A");
            spannableString4N.setSpan(imageSpan,0,1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }


        initRadio(radioGroup);

        SpannableString spannableString1=new SpannableString("A");
        spannableString1.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);



        imageaTextview_weight.setMovementMethod(LinkMovementMethod.getInstance());
        imageaTextview_weight.setHighlightColor(Color.parseColor("#36969696"));
        imageaTextview_weight.setText(spannableString);

        imageTextviewEnergy.setMovementMethod(LinkMovementMethod.getInstance());
        imageTextviewEnergy.setHighlightColor(Color.parseColor("#36969696"));
        imageTextviewEnergy.setText(spannableString1);

        imgerTextviewSport.setMovementMethod(LinkMovementMethod.getInstance());
        imgerTextviewSport.setHighlightColor(Color.parseColor("#36969696"));
        imgerTextviewSport.setText(spannableString2);

        imageaBooldSugar.setMovementMethod(LinkMovementMethod.getInstance());
        imageaBooldSugar.setHighlightColor(Color.parseColor("#36969696"));
        imageaBooldSugar.setText(spannableString3);

        imageaTwoHourBooldSugar.setMovementMethod(LinkMovementMethod.getInstance());
        imageaTwoHourBooldSugar.setHighlightColor(Color.parseColor("#36969696"));
        imageaTwoHourBooldSugar.setText(spannableString4);

        imageaTwoHourBooldSugarN.setMovementMethod(LinkMovementMethod.getInstance());
        imageaTwoHourBooldSugarN.setHighlightColor(Color.parseColor("#36969696"));
        imageaTwoHourBooldSugarN.setText(spannableString4N);

        imageaTwoHourBooldSugarM.setMovementMethod(LinkMovementMethod.getInstance());
        imageaTwoHourBooldSugarM.setHighlightColor(Color.parseColor("#36969696"));
        imageaTwoHourBooldSugarM.setText(spannableString4M);

        tv_yun_week.setText("·孕"+yun_week+"周"+yun_day+"天");

        //体重点击监听
        imageaTextview_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoublePicker picker=showDouble(1,"宝妈的体重","kg");
                picker.setOnPickListener(new DoublePicker.OnPickListener() {
                    @Override
                    public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                        imageaTextview_weight.setText(options1Items.get(selectedFirstIndex)+"."+options2Items.get(selectedSecondIndex)+"kg");
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        double weight=Integer.parseInt(options1Items.get(selectedFirstIndex))+Integer.parseInt(options2Items.get(selectedSecondIndex))/100;
                        editor.putString("weight",weight+"");
                        editor.apply();
                    }
                });
                picker.show();
            }
        });
        //卡路里监听
        imageTextviewEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CalorieActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        //运动点击监听
        imgerTextviewSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberPicker picker = MainActivity.getNumberPicker();
                picker.setWidth(picker.getScreenWidthPixels());
                picker.setContentPadding(10, 8);
                picker.setHeight(picker.getScreenHeightPixels()/3);
                picker.setCycleDisable(false);
                picker.setDividerVisible(false);
                picker.setCanceledOnTouchOutside(true);
                picker.setTopLineColor(0xFFFB2C3C);
                picker.setSubmitTextColor(0xFFFB2C3C);
                picker.setCancelTextColor(0xFFFB2C3C);
                picker.setLineSpaceMultiplier(2.2f);
                picker.setTextSize(20);
                picker.setTitleText("今日运动量");
                picker.setOffset(2);//偏移量
                picker.setRange(0, 5, 0.1);//数字范围
                picker.setLabel("小时");
                picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
                    @Override
                    public void onNumberPicked(int index, Number item) {
                        imgerTextviewSport.setText(item.floatValue()+"小时");
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("sportHour",item.floatValue()+"");
                        editor.apply();
                    }
                });
                picker.show();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                dialog.setTitle("提醒");
                dialog.setMessage("建议每天餐后0.5-1h内进行适量运动，如散步、等楼梯等，持续30分钟。");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                dialog.show();
            }
        });
        //心情点击监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.record_btn_mood1:
                        Toast.makeText(parent.getContext(),"大笑虽好但不能贪杯喔（没有颜表情( •̀ ω •́ )y）",Toast.LENGTH_SHORT).show();
                        editor.putInt("mood",1);
                        editor.apply();
                        break;
                    case R.id.record_btn_mood2:
                        Toast.makeText(parent.getContext(),"你笑的时候宝宝也在笑(●'◡'●)",Toast.LENGTH_SHORT).show();
                        editor.putInt("mood",2);
                        editor.apply();
                        break;
                    case R.id.record_btn_mood3:
                        Toast.makeText(parent.getContext(),"宝妈要尽快好起来喔(ง •_•)ง",Toast.LENGTH_SHORT).show();
                        editor.putInt("mood",3);
                        editor.apply();
                        break;
                    case R.id.record_btn_mood4:
                        Toast.makeText(parent.getContext(),"宝宝也想看妈妈笑╥﹏╥...",Toast.LENGTH_SHORT).show();
                        editor.putInt("mood",4);
                        editor.apply();
                        break;
                    case R.id.record_btn_mood5:
                        Toast.makeText(parent.getContext(),"这时候宝宝也在哭/(ㄒoㄒ)/~~",Toast.LENGTH_SHORT).show();
                        editor.putInt("mood",5);
                        editor.apply();
                        break;
                        default:
                            break;
                }
            }
        });

        imageaBooldSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoublePicker picker=showDouble(2,"宝妈空腹血糖","mmol/L");
                picker.setOnPickListener(new DoublePicker.OnPickListener() {
                    @Override
                    public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                        imageaBooldSugar.setText(options3Items.get(selectedFirstIndex)+"."+options4Items.get(selectedSecondIndex)+"mmol/L");
                        double booldSugar=Integer.parseInt(options3Items.get(selectedFirstIndex))+Integer.parseInt(options4Items.get(selectedSecondIndex))/100;
                        if (booldSugar>6.19){
                            Toast.makeText(MyApplication.getContext(),"宝妈的血糖值高了！！",Toast.LENGTH_SHORT).show();
                            imageaBooldSugar.setTextColor(Color.rgb(255,0,0));
                        }
                        else if (booldSugar<3.15){
                            Toast.makeText(MyApplication.getContext(),"宝妈的血糖值低了！！",Toast.LENGTH_SHORT).show();
                            imageaBooldSugar.setTextColor(Color.rgb(255,0,0));
                        }
                        else {
                            imageaBooldSugar.setTextColor(Color.rgb(172,172,172));
                        }
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("booldSugar",booldSugar+"");
                        editor.apply();
                    }
                });
                picker.show();
            }
        });

        imageaTwoHourBooldSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoublePicker picker=showDouble(2,"宝妈饭后两小时血糖","mmol/L");
                picker.setOnPickListener(new DoublePicker.OnPickListener() {
                    @Override
                    public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                        imageaTwoHourBooldSugar.setText(options3Items.get(selectedFirstIndex)+"."+options4Items.get(selectedSecondIndex)+"mmol/L");
                        double booldSugar=Integer.parseInt(options3Items.get(selectedFirstIndex))+Integer.parseInt(options4Items.get(selectedSecondIndex))/100;
                        if (booldSugar>7.8){
                            Toast.makeText(MyApplication.getContext(),"宝妈的血糖值高了！！",Toast.LENGTH_SHORT).show();
                            imageaTwoHourBooldSugar.setTextColor(Color.rgb(255, 0, 0));
                        }
                        else{
                            imageaTwoHourBooldSugar.setTextColor(Color.rgb(172,172,172));
                        }
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("twoHourbooldSugar",booldSugar+"");
                        editor.apply();
                    }
                });
                picker.show();
            }
        });
        imageaTwoHourBooldSugarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoublePicker picker=showDouble(2,"宝妈饭后两小时血糖","mmol/L");
                picker.setOnPickListener(new DoublePicker.OnPickListener() {
                    @Override
                    public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                        imageaTwoHourBooldSugarM.setText(options3Items.get(selectedFirstIndex)+"."+options4Items.get(selectedSecondIndex)+"mmol/L");
                        double booldSugar=Integer.parseInt(options3Items.get(selectedFirstIndex))+Integer.parseInt(options4Items.get(selectedSecondIndex))/100;
                        if (booldSugar>7.8){
                            Toast.makeText(MyApplication.getContext(),"宝妈的血糖值高了！！",Toast.LENGTH_SHORT).show();
                            imageaTwoHourBooldSugarM.setTextColor(Color.rgb(255, 0, 0));
                        }
                        else{
                            imageaTwoHourBooldSugarM.setTextColor(Color.rgb(172,172,172));
                        }
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("twoHourbooldSugarM",booldSugar+"");
                        editor.apply();
                    }
                });
                picker.show();
            }
        });
        imageaTwoHourBooldSugarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoublePicker picker=showDouble(2,"宝妈饭后两小时血糖","mmol/L");
                picker.setOnPickListener(new DoublePicker.OnPickListener() {
                    @Override
                    public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                        imageaTwoHourBooldSugarN.setText(options3Items.get(selectedFirstIndex)+"."+options4Items.get(selectedSecondIndex)+"mmol/L");
                        double booldSugar=Integer.parseInt(options3Items.get(selectedFirstIndex))+Integer.parseInt(options4Items.get(selectedSecondIndex))/100;
                        if (booldSugar>7.8){
                            Toast.makeText(MyApplication.getContext(),"宝妈的血糖值高了！！",Toast.LENGTH_SHORT).show();
                            imageaTwoHourBooldSugarN.setTextColor(Color.rgb(255, 0, 0));
                        }
                        else{
                            imageaTwoHourBooldSugarN.setTextColor(Color.rgb(172,172,172));
                        }
                        SharedPreferences.Editor editor=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("twoHourbooldSugarN",booldSugar+"");
                        editor.apply();
                    }
                });
                picker.show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRecylerList.size();
    }
    //初始化数据
    private List<String> initData(int start,int end){
        List<String> optionsItems=new ArrayList<>();
        for (int i=start;i<=end;i++){
            optionsItems.add(DateUtils.fillZero(i));
        }
        return optionsItems;
    }

    private void initData(final ViewGroup parent){
        SharedPreferences preferences=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE);
        String date=preferences.getString("date","");
        SharedPreferences.Editor pref=parent.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        List<CalendarInfo> calendarInfoList=LitePal.where("calendarId = ?",date+"").find(CalendarInfo.class);
        List<UserInfo> userInfos=LitePal.findAll(UserInfo.class);
        if (!calendarInfoList.isEmpty()){
            CalendarInfo calendarInfo=calendarInfoList.get(0);
            weight=calendarInfo.getTodayWeight();
            sportHourt=calendarInfo.getTodaySport();
            mood=calendarInfo.getTodayMethod();
            booldSugar=calendarInfo.getTodayBooldSugar();
            twoHourbooldSugar=calendarInfo.getTodayTwoHourBooldSugar();
            twoHourbooldSugarM=calendarInfo.getTodayTwoHourBooldSugarM();
            twoHourbooldSugarN=calendarInfo.getTodayTwoHourBooldSugarN();
            pref.putString("weight",weight+"");
            pref.putString("sportHour",sportHourt+"");
            pref.putInt("mood",mood);
            pref.apply();
        }else
        {
            pref.putString("weight","");
            pref.putString("sportHour","");
            pref.putInt("mood",0);
            pref.apply();
            Log.e("Recyler", "initData: " );
        }
        if (!userInfos.isEmpty()){

            StringBuilder stringBuilder=new StringBuilder(date);
            stringBuilder.insert(4,"-");
            stringBuilder.insert(7,"-");
            long day=dateDiff(userInfos.get(0).getUserLastPeriod(),stringBuilder.toString(),"yyyy-MM-dd");
            Log.e("RecylerAdapter", "initData: " +stringBuilder.toString());
             yun_week=day/7;
             yun_day=day%7;
        }
    }

    private void initRadio(RadioGroup radioGroup){
        if (mood!=0){
            switch (mood){
                case 1:
                    radioGroup.check(R.id.record_btn_mood1);
                    break;
                case 2:
                    radioGroup.check(R.id.record_btn_mood2);
                    break;
                case 3:
                    radioGroup.check(R.id.record_btn_mood3);
                    break;
                case 4:
                    radioGroup.check(R.id.record_btn_mood4);
                    break;
                case 5:
                    radioGroup.check(R.id.record_btn_mood5);
                    break;
            }
        }
    }
    private DoublePicker showDouble(int a,String title,String b){
        DoublePicker picker=MainActivity.getDoublePicker(a);
        picker.setCanceledOnTouchOutside(true);
        picker.setTopLineColor(0xFFFB2C3C);
        picker.setSubmitTextColor(0xFFFB2C3C);
        picker.setCancelTextColor(0xFFFB2C3C);
        picker.setLineSpaceMultiplier(2.2f);
        picker.setTextSize(20);
        picker.setTitleText(title);
        picker.setContentPadding(10, 8);
        picker.setUseWeight(true);
        picker.setFirstLabel("", ".");
        picker.setSecondLabel("", b);
       return picker;
    }
    private long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
    //    Log.e("RecylerAdapter", "dateDiff: ");
        try {

            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();

            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            if (day>=1) {
                return day;
            }else {
                if (day==0) {
                    return 1;
                }else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
