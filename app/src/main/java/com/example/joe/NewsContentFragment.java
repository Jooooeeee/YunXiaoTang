package com.example.joe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }
    public void refresh(String newsTitle,String newsContent){
        View visibilty_layout=view.findViewById(R.id.visibilty_layout);
        visibilty_layout.setVisibility(View.VISIBLE);
        TextView newsTitleText=view.findViewById(R.id.news_title);
        TextView newsContentText=view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
        newsContentText.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
