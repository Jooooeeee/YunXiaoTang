package com.example.joe.db;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * 日历统计表
 */
public class CalendarInfo extends LitePalSupport implements Serializable {
    private int calendarId;//日历主键
    private int todayMethod;//当天心情
    private double todaySport;//当天运动量
    private double todayWeight;//当天体重
    private double todayBooldSugar;//空腹血糖
    private double todayTwoHourBooldSugar;//餐后晚血糖
    private double todayTwoHourBooldSugarN;//餐后午血糖
    private double todayTwoHourBooldSugarM;//餐后早血糖

    public CalendarInfo(int calendarId, int todayMethod, double todaySport, double todayWeight, double todayBooldSugar, double todayTwoHourBooldSugar, double todayTwoHourBooldSugarN, double todayTwoHourBooldSugarM) {
        this.calendarId = calendarId;
        this.todayMethod = todayMethod;
        this.todaySport = todaySport;
        this.todayWeight = todayWeight;
        this.todayBooldSugar = todayBooldSugar;
        this.todayTwoHourBooldSugar = todayTwoHourBooldSugar;
        this.todayTwoHourBooldSugarN = todayTwoHourBooldSugarN;
        this.todayTwoHourBooldSugarM = todayTwoHourBooldSugarM;
    }

    public double getTodayTwoHourBooldSugarN() {
        return todayTwoHourBooldSugarN;
    }

    public void setTodayTwoHourBooldSugarN(double todayTwoHourBooldSugarN) {
        this.todayTwoHourBooldSugarN = todayTwoHourBooldSugarN;
    }

    public double getTodayTwoHourBooldSugarM() {
        return todayTwoHourBooldSugarM;
    }

    public void setTodayTwoHourBooldSugarM(double todayTwoHourBooldSugarM) {
        this.todayTwoHourBooldSugarM = todayTwoHourBooldSugarM;
    }

    public CalendarInfo() {
    }

    public double getTodayBooldSugar() {
        return todayBooldSugar;
    }

    public void setTodayBooldSugar(double todayBooldSugar) {
        this.todayBooldSugar = todayBooldSugar;
    }

    public double getTodayTwoHourBooldSugar() {
        return todayTwoHourBooldSugar;
    }

    public void setTodayTwoHourBooldSugar(double todayTwoHourBooldSugar) {
        this.todayTwoHourBooldSugar = todayTwoHourBooldSugar;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getTodayMethod() {
        return todayMethod;
    }

    public void setTodayMethod(int todayMethod) {
        this.todayMethod = todayMethod;
    }

    public double getTodaySport() {
        return todaySport;
    }

    public void setTodaySport(double todaySport) {
        this.todaySport = todaySport;
    }

    public double getTodayWeight() {
        return todayWeight;
    }

    public void setTodayWeight(double todayWeight) {
        this.todayWeight = todayWeight;
    }
}
