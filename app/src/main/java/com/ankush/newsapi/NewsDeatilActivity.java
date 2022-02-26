package com.ankush.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDeatilActivity extends AppCompatActivity {


    String title,content,description,urltoimage,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_deatil);
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        description=getIntent().getStringExtra("description");
        urltoimage=getIntent().getStringExtra("urltoimage");
        url=getIntent().getStringExtra("url");


    }
}