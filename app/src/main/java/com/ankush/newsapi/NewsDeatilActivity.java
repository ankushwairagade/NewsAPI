package com.ankush.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDeatilActivity extends AppCompatActivity {


    String title,content,description,urltoimage,url;

    private TextView Ititle, Isubdesc, Icontent;
    private ImageView Iimage;
    private Button readnewsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_deatil);
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        description=getIntent().getStringExtra("description");
        urltoimage=getIntent().getStringExtra("urltoimage");
        url=getIntent().getStringExtra("url");

        Ititle=findViewById(R.id.idITitle);
        Isubdesc=findViewById(R.id.idISubdesc);
        Icontent=findViewById(R.id.idIcontent);
        Iimage=findViewById(R.id.idIView);
        readnewsbtn=findViewById(R.id.idBtnReader);
        Ititle.setText(title);
        Isubdesc.setText(description);
        Icontent.setText(content);
        Picasso.get().load(urltoimage).into(Iimage);
        readnewsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}