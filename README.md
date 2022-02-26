#API Documentation
api key =xxxxxxxxxxxxxxxxxxxxxx 

https://newsapi.org/v2/top-headlines?country=Country&category=business&apiKey=xxxxxxxxxxxxxxxxxxxxx.

country = ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za.

category = business , entertainment , general  ,  health , science  ,  sports  ,  technolog


## Introduction

[News API](https://newsapi.org/) is an amazing source of information as it provides a single API that exposes breaking news headlines and older articles from over 3,000 news sources and blogs!

You can [sign up here](https://newsapi.org/account) for a free developer plan which allows you to make 500 requests per day. One can easily exceed the limit

## Solution

This project queries the newsapi at a regular interval and logs the reponse to a JSON file.
This json file is then committed and pushed to github. Now one can get the latest news from this json instead of newsapi.

## Pros

- No API KEY needed
- No limit on number of requests

## Cons

- Cannot query for news (Can be fixed by cloning this repo and customizing according to your needs)
- Slightly delayed result (Can be reduced by reducing the query time interval to newsapi)





##   [Download_APK](https://github.com/ankushwairagade/NewsAPI/blob/master/app/apk/newsapi.apk)






  ![alt text](https://github.com/ankushwairagade/NewsAPI/blob/master/app/apk/screenshot.png)  
