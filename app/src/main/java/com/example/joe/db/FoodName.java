package com.example.joe.db;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class FoodName extends LitePalSupport implements Serializable {
    private int foodID;
    private String foodName;
    private double foodCalorie;
    private int foodNumber;

    public FoodName(int foodID, String foodName, double foodCalorie, int foodNumber) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodCalorie = foodCalorie;
        this.foodNumber = foodNumber;
    }

    public FoodName() {
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(double foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }
}
