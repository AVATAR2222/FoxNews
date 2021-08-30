package ir.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.news.NewsAdapter;
import ir.news.R;
import ir.news.core.ApiClient;
import ir.news.core.News;
import ir.news.interfaces.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment
{
    private RecyclerView recyclerView;
    private List<News> newsModelList;
    private NewsAdapter newsAdapter;
    public static ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
    {
        View v = inflater.inflate( R.layout.fragment_home, container, false );

        recyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        newsModelList = new ArrayList<>();

        Call<News> call = apiInterface.performAllFethedNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsModelList = response.body().getAllNews();

                newsAdapter = new NewsAdapter(newsModelList, getContext());
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

        return v;
    }
}
