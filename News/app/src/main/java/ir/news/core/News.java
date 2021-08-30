package ir.news.core;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import ir.news.model.NewsModel;

public class News implements Serializable {

//    private HashMap<String,String> source;

   public Source source;

//public String title ,description,urlToImage,content,publishedAt;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getPublishedAt() {
//        return publishedAt;
//    }

    @SerializedName("articles")
    private List<News> AllNews;


    public List<News> getAllNews() {
        return AllNews;
    }
}
