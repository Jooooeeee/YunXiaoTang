package com.example.joe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.joe.db.FoodName;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;



public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.ViewHolder> {
    private List<FoodName> calorieItemList;
    private TextView calorie_textview;


    private int date;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName;
        TextView foodCalorie;
        TextView foodNumber;
        ImageButton delBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            foodName=itemView.findViewById(R.id.food_name);
            foodCalorie=itemView.findViewById(R.id.calorie);
            foodNumber=itemView.findViewById(R.id.foodNumber);
            delBtn=itemView.findViewById(R.id.delete_btn);
        }
    }
    public CalorieAdapter(List<FoodName> calorieAdapter,TextView calorie_textview2,int d){
        calorieItemList=calorieAdapter;
        calorie_textview=calorie_textview2;
        date=d;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcer_caorie2,parent,false);
        final CalorieAdapter.ViewHolder holder=new ViewHolder(view);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CalorieActivity.getCalorieItemList().remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                double calorie=0;
                for (int i=0;i<CalorieActivity.getCalorieItemList().size();i++){
                    FoodName foodName=CalorieActivity.getCalorieItemList().get(i);

                    calorie+=foodName.getFoodCalorie();
                }
                calorie_textview.setText(String.format("%.2f",calorie));

            }

        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodName calorieItem=calorieItemList.get(position);
        holder.foodCalorie.setText(calorieItem.getFoodCalorie()+"大卡");
        holder.foodName.setText(calorieItem.getFoodName());
        holder.foodNumber.setText(calorieItem.getFoodNumber()+"g");

    }

    @Override
    public int getItemCount() {
        return calorieItemList.size();
    }
}
