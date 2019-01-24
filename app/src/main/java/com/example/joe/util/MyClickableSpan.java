package com.example.joe.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.joe.MainActivity;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.util.DateUtils;

public class MyClickableSpan extends ClickableSpan {
    private TextView textView;
    private int i;
    private final List<String> options1Items = new ArrayList<>();
    private final List<String> options2Items = new ArrayList<>();

    public MyClickableSpan(TextView textView) {
        this.textView = textView;
        initData();
        i = 1;
    }

    public MyClickableSpan(TextView textView, int i) {
        this.textView = textView;
        this.i = i;
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (i) {
            case 1:
                setDoublePicker();
                break;
            case 3:
                onNumberPicker2();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setUnderlineText(false);
    }

    private void initData() {
        for (int i = 20; i <= 149; i++) {
            options1Items.add(i + "");
        }
        for (int i = 0; i <= 9; i++) {
            options2Items.add(DateUtils.fillZero(i));
        }
    }

    public void setDoublePicker() {
        DoublePicker picker =MainActivity.getDoublePicker(1);
        picker.setCanceledOnTouchOutside(true);
        picker.setTopLineColor(0xFFFB2C3C);
        picker.setSubmitTextColor(0xFFFB2C3C);
        picker.setCancelTextColor(0xFFFB2C3C);
        picker.setLineSpaceMultiplier(2.2f);
        picker.setTextSize(20);
        picker.setTitleText("体重");
        picker.setContentPadding(10, 8);
        picker.setUseWeight(true);
        picker.setFirstLabel("", ".");
        picker.setSecondLabel("", "kg");
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                textView.setText(options1Items.get(selectedFirstIndex) + "." + options2Items.get(selectedSecondIndex) + "kg");
                SharedPreferences.Editor editor=MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                double weight=Integer.parseInt(options1Items.get(selectedFirstIndex))+Integer.parseInt(options2Items.get(selectedSecondIndex))/100;
                editor.putString("weight",weight+"");
                editor.apply();
            }
        });
        picker.show();
    }


    public void onNumberPicker2() {
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
                textView.setText(item.floatValue()+"小时");
                SharedPreferences.Editor editor=MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                editor.putString("sportHour",item.floatValue()+"");
                editor.apply();
            }
        });
        picker.show();
    }
}