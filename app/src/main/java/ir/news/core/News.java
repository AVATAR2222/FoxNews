package ir.news.core;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

//    private HashMap<String,String> source;

   public Source source;

public String title ,description,urlToImage,content,publishedAt;



    @SerializedName("articles")
    private List<News> AllNews;


    public List<News> getAllNews() {
        return AllNews;
    }
}
