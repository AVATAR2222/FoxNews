package ir.news.interfaces;

import ir.news.core.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("everything?q=keyword&apiKey=98944c19915f4617acb1b5babc7e68ec")
    Call<News> performAllFethedNews();

}
