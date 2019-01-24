package com.example.joe;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.joe.db.FoodName;
import com.example.joe.util.BottomNavigationViewHelper;
import com.example.joe.util.RecycleViewDivider;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChangeFoodActivity extends AppCompatActivity {

    private static final String TAG = "ChangeFoodActivity";
    private BottomNavigationView navigationView;
    private List<FoodName> foodNames=new ArrayList<>();
    private FoodNameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_food);
        navigationView =findViewById(R.id.nv_navigation);
        Toolbar toolbar=findViewById(R.id.toolbar_change_food);

        RecyclerView recyclerView=findViewById(R.id.recycler_food_name);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。

            ChangeFoodActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        BottomNavigationViewHelper.disableShiftMode(navigationView);  //通过反射取消默认动画
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initFoodName();
         adapter=new FoodNameAdapter(foodNames);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 3, getResources().getColor(R.color.colorPrimary)));
    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    foodNames.clear();
                    initFoodName();
                    adapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_dashboard:
                    foodNames.clear();
                    initFoodName2();
                    adapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_notifications:
                    foodNames.clear();
                    initFoodName3();
                    adapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_person:
                    foodNames.clear();
                    initFoodName4();
                    adapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_person2:  //case不写则该view将没有点击效果
                    foodNames.clear();
                    initFoodName5();
                    adapter.notifyDataSetChanged();
                    return true;
            }
            return false;
        }
    };
//初始化主食数据
    private void initFoodName(){

        foodNames.addAll(LitePal.limit(10).find(FoodName.class));

    }
    //初始化蔬菜数据
    private void initFoodName2(){

        foodNames.addAll(LitePal.limit(10).offset(10).find(FoodName.class));

    }
    //初始化肉蛋
    private void initFoodName3(){

        foodNames.addAll(LitePal.limit(10).offset(20).find(FoodName.class));

    }
    //初始化饮料数据
    private void initFoodName4(){

        foodNames.addAll(LitePal.limit(10).offset(30).find(FoodName.class));

    }
    //初始化快餐数据
    private void initFoodName5(){
        foodNames.addAll(LitePal.limit(5).offset(40).find(FoodName.class));

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("da","Hello");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent();
                intent.putExtra("da","Hello");
                setResult(RESULT_OK,intent);
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
