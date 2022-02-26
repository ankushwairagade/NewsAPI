package com.ankush.newsapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapater extends RecyclerView.Adapter<CategoryRvAdapater.ViewHolder> {
    private ArrayList<CategoryRvModel> categoryRvModels;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRvAdapater(ArrayList<CategoryRvModel> categoryRvModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRvModels = categoryRvModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRvAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
    return new CategoryRvAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRvAdapater.ViewHolder holder, int position) {
    CategoryRvModel categoryRvModel = categoryRvModels.get(position);
    holder.cattext.setText(categoryRvModel.getCategory());
    Picasso.get().load(categoryRvModel.getCategoryImageUrl()).into(holder.catImage);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            categoryClickInterface.onCategoryClick(position);
        }
    });

    }

    @Override
    public int getItemCount() {
        return categoryRvModels.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cattext;
        private ImageView catImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cattext = itemView.findViewById(R.id.tabid);
            catImage = itemView.findViewById(R.id.catImage);

        }
    }
}
