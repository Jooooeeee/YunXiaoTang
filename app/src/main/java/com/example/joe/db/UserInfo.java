package com.example.joe.db;

import android.content.SharedPreferences;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 */
public class UserInfo extends LitePalSupport implements Serializable {
    private int userId;//主键
    private String username;
    private int userAge;
    private double userHeight;
    private double userWeight;
    private String userStart;//用户开始使用APP时间
    private String userLastPeriod;//最后一次月经时间
    private String userDueData;//预产期产检次数

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public UserInfo(int userId, String username, int userAge, double userHeight, double userWeight, String userStart, String userLastPeriod, String userDueData, int times) {
        this.userId = userId;
        this.username = username;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userStart = userStart;
        this.userLastPeriod = userLastPeriod;
        this.userDueData = userDueData;
        this.times = times;
    }

    private int times;//

    public UserInfo() {
    }

    public UserInfo(int userId, String username, int userAge, double userHeight, double userWeight, String userStart, String userLastPeriod, String userDueData) {
        this.userId = userId;
        this.username = username;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userStart = userStart;
        this.userLastPeriod = userLastPeriod;
        this.userDueData = userDueData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserStart() {
        return userStart;
    }

    public void setUserStart(String userStart) {
        this.userStart = userStart;
    }

    public String getUserLastPeriod() {
        return userLastPeriod;
    }

    public void setUserLastPeriod(String userLastPeriod) {
        this.userLastPeriod = userLastPeriod;
    }

    public String getUserDueData() {
        return userDueData;
    }

    public void setUserDueData(String userDueData) {
        this.userDueData = userDueData;
    }
}
