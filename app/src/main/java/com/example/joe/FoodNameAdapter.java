package com.example.joe;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joe.db.FoodName;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import java.util.List;


public class FoodNameAdapter extends RecyclerView.Adapter<FoodNameAdapter.ViewHolder> {
    private List<FoodName> foodNameList;
   static class ViewHolder extends RecyclerView.ViewHolder{
       TextView foodName;
       TextView foodNumber;
       TextView foodCalorie;
       View view;
       public ViewHolder(View itemView) {
           super(itemView);
           view=itemView;
           foodName=itemView.findViewById(R.id.food_name2);
           foodCalorie= itemView.findViewById(R.id.calorie2);
           foodNumber=itemView.findViewById(R.id.foodNumber2);
       }
   }

    public FoodNameAdapter(List<FoodName> foodNameList) {
        this.foodNameList = foodNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.food_name_item,parent,false);
        final FoodNameAdapter.ViewHolder holder=new ViewHolder(view);


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(parent.getContext()).builder()
                        .setTitle("请输入")
                        .setEditType(InputType.TYPE_CLASS_NUMBER)
                        .setEditText("");
                myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position=holder.getAdapterPosition();
                        FoodName foodName=foodNameList.get(position);
                        int number=Integer.parseInt(myAlertInputDialog.getResult());
                        double calorie=number*foodName.getFoodCalorie()/100;
                        foodName.setFoodNumber(number);
                        foodName.setFoodCalorie(calorie);
                        Intent intent=new Intent();
                        intent.putExtra("data",foodName);
                        myAlertInputDialog.dismiss();
                        ((Activity) parent.getContext()).setResult(Activity.RESULT_OK,intent);
                        ((Activity) parent.getContext()).finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAlertInputDialog.dismiss();
                    }
                });
                myAlertInputDialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodName foodName=foodNameList.get(position);
        holder.foodNumber.setText(foodName.getFoodNumber()+"g");
        holder.foodCalorie.setText(foodName.getFoodCalorie()+"大卡");
        holder.foodName.setText(foodName.getFoodName());
    }
    @Override
    public int getItemCount() {
        return foodNameList.size();
    }
}
