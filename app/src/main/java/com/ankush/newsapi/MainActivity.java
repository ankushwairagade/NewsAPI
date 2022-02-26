package com.ankush.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  implements CategoryRvAdapater.CategoryClickInterface{
    //api =96b1a710832c416c97fee80b3fbe572e   //category=business
    //https://newsapi.org/v2/top-headlines?country=Country&category=business&apiKey=96b1a710832c416c97fee80b3fbe572e
    //country = ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za
    //category     = business , entertainment , general  ,  health , science  ,  sports  ,  technology

    private RecyclerView newsRV, catRV;
    private ProgressBar progressBar;
    private ArrayList<Articles> articlesArrayList;
   private ArrayList<CategoryRvModel> categoryRvAdapaterArrayList;
    private CategoryRvAdapater categoryRvAdapater;
    private NewsRvAdapater newsRvAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressbar);
        newsRV=findViewById(R.id.recycleViewNews);
        catRV= findViewById(R.id.catMenu);
        articlesArrayList = new ArrayList<>();
        categoryRvAdapaterArrayList= new ArrayList<>();
        newsRvAdapater = new NewsRvAdapater(articlesArrayList,this);
        categoryRvAdapater = new CategoryRvAdapater(categoryRvAdapaterArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRvAdapater);
        catRV.setAdapter(categoryRvAdapater);
        getCategories();
        getNews("All");
        newsRvAdapater.notifyDataSetChanged();


    }

    private void getCategories()
    {     // category     = business , entertainment  general  health  science    sports  technology
        categoryRvAdapaterArrayList.add(new CategoryRvModel("All","https://images.unsplash.com/photo-1523995462485-3d171b5c8fa9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Business","https://images.unsplash.com/photo-1532619187608-e5375cab36aa?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Entertainment","https://images.unsplash.com/photo-1549342902-be005322599a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Health","https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Science","https://images.unsplash.com/photo-1518152006812-edab29b069ac?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvAdapaterArrayList.add(new CategoryRvModel("Technology","https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));

        categoryRvAdapater.notifyDataSetChanged();

    }

    private void getNews(String category)
    {  progressBar.setVisibility(View.VISIBLE);
       articlesArrayList.clear();
       String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+ category +"&apiKey=96b1a710832c416c97fee80b3fbe572e";
       String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=96b1a710832c416c97fee80b3fbe572e";
       String BASE_URL="https://newsapi.org";
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
       Call<NewsModel> call;
       if(category.equals("All"))
       {
           call = retrofitAPI.getAllNews(url);
       }else{
           call = retrofitAPI.getNewsByCategory(categoryURL);
       }

       call.enqueue(new Callback<NewsModel>() {
           @Override
           public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
               NewsModel newsModel = response.body();
               progressBar.setVisibility(View.GONE);
               ArrayList<Articles> articles = newsModel.getArticles();
               for(int i=0;i<articles.size();i++)
               {
                   articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrl(),articles.get(i).getUrlToImage(),articles.get(i).getContent()));

               }
               newsRvAdapater.notifyDataSetChanged();
                }
           @Override
           public void onFailure(Call<NewsModel> call, Throwable t) {
               Toast.makeText(MainActivity.this,"Fail to get News", Toast.LENGTH_SHORT).show();

           }
       });
    }
    @Override
    public void onCategoryClick(int position) {
        String category =categoryRvAdapaterArrayList.get(position).getCategory();
        getNews(category);

    }
}