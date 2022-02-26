package com.ankush.newsapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRvAdapater extends RecyclerView.Adapter<NewsRvAdapater.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRvAdapater(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRvAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
       return new NewsRvAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapater.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.subtitletv.setText(articles.getDescription());
        holder.titletv.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context ,NewsDeatilActivity.class );
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("description",articles.getDescription());
                i.putExtra("urltoimage",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titletv,subtitletv;
        private ImageView newsimage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            titletv=itemView.findViewById(R.id.textHeading);
            subtitletv=itemView.findViewById(R.id.textSubhead);
            newsimage=itemView.findViewById(R.id.newsImage);
        }

    }
}
