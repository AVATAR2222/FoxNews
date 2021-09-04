package ir.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;

import java.util.ArrayList;
import java.util.List;

import ir.news.adapter.NewsAdapter;
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

        ProgressBar progressBar = v.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new CubeGrid();
        progressBar.setIndeterminateDrawable(doubleBounce);

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
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

        return v;
    }
}
