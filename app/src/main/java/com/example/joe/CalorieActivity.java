package com.example.joe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.joe.db.FoodName;
import com.example.joe.util.RecycleViewDivider;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CalorieActivity extends AppCompatActivity {

    private static final String TAG = "CalorieActivity";
    private static List<FoodName> calorieItemList=new ArrayList<>();
    private CalorieAdapter adapter;
    private TextView calorie_textview;
    private TextView calorieCalender_textview;
    private double calorie=0;
    private int date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        Toolbar toolbar=findViewById(R.id.toolbar_calorie);
        Button addFood=findViewById(R.id.addFood);
        calorieCalender_textview=findViewById(R.id.calorieCalender_textview);
        calorie_textview=findViewById(R.id.calorie_textview);
        RecyclerView recyclerView=findViewById(R.id.calorieRecycler);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            CalorieActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        date=Integer.parseInt(preferences.getString("date",""));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CalorieAdapter(calorieItemList,calorie_textview,date);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.colorPrimary)));
        addFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(CalorieActivity.this,ChangeFoodActivity.class);
                startActivityForResult(intent,1);
            }
        });

        List<FoodName> list=LitePal.where("foodID = ?",date+"").find(FoodName.class);
        if (!list.isEmpty()){
            calorieItemList.clear();
            Log.e(TAG, "onCreate: "+list.size());
            calorieItemList.addAll(list);
            adapter.notifyDataSetChanged();
           setCalorie_textview();
        }
        int year=date/10000;
        int a=date/100;
        int month=a%100;
        int day=date%100;
        calorieCalender_textview.setText(year+"."+month+"."+day);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(CalorieActivity.this,MainActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (data.getSerializableExtra("data")!=null) {
                    FoodName foodName = (FoodName) data.getSerializableExtra("data");
                    calorieItemList.add(foodName);
                    adapter.notifyDataSetChanged();
                    setCalorie_textview();
                }
                else
                    Log.e(TAG, "onActivityResult: " );
                break;
                default:
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(CalorieActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop: ");
    }
    /*
    *设置Calorie_textview
    */
    private void setCalorie_textview(){
        for (int i=0;i<calorieItemList.size();i++){
            FoodName foodName=calorieItemList.get(i);
            calorie+=foodName.getFoodCalorie();
        }
        calorie_textview.setText(String.format("%.2f",calorie));
        calorie=0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!calorieItemList.isEmpty()) {
            LitePal.deleteAll(FoodName.class,"foodID = ?",date+"");
            for (int i = 0; i < calorieItemList.size(); i++) {
                FoodName foodName = calorieItemList.get(i);
                foodName.setFoodID(date);
                foodName.assignBaseObjId(0);
                Log.e(TAG, "onDestroy: "+foodName.save()+"");
            }

        }
        Log.e(TAG, "onDestroy: "+calorieItemList.size());
    }
    public static List<FoodName> getCalorieItemList(){
        return calorieItemList;
    }
}
