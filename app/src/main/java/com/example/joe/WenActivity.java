package com.example.joe;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class WenActivity extends AppCompatActivity {

//    public static void actionStart(Context context,String newsTitle,String newsContent){
//        Intent intent = new Intent(context,WenActivity.class);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen);
        final Toolbar toolbar = findViewById(R.id.toolbar_wen);
        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            WenActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String title=getIntent().getStringExtra("title");
        String content=getIntent().getStringExtra("content");
        String content2=getIntent().getStringExtra("content2");
        int isHaveImage=getIntent().getIntExtra("isHaveImage",0);
        NewsContentFragment newsContentFragment=(NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        if (content2!=null&&!content2.isEmpty()) {
            newsContentFragment.refresh(title,content,content2,isHaveImage);
        }
        else {

            newsContentFragment.refresh(title, content, isHaveImage);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
