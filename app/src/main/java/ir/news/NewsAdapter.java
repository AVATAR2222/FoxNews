package ir.news;

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

import java.util.List;

import ir.news.activity.NewsActivity;
import ir.news.core.News;
import ir.news.model.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.UsersViewHolder> {

    List<News> newsModelList;
    Context context;

    public NewsAdapter(List<News> newsModelList, Context context) {
        this.newsModelList = newsModelList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NewsAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_news,viewGroup,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.UsersViewHolder holder, int position) {

        News news=newsModelList.get(position);
        holder.tv_title.setText(news.title);
        holder.tv_des.setText(news.description);
        holder.tv_time.setText(news.publishedAt);
        Picasso.get()
                .load(news.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img);



        //////////////////sending data//////////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, NewsActivity.class);
                intent.putExtra("news_title",news.title);
                intent.putExtra("news_publishedAt",news.publishedAt);
                intent.putExtra("news_content",news.content);
                intent.putExtra("news_urlToImage",news.urlToImage);
//                intent.putExtra("user_name",usersModel.getName());
//                intent.putExtra("user_email",usersModel.getEmail());
//                intent.putExtra("user_password",usersModel.getPassword());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_des,tv_time;
        ImageView img;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_title=itemView.findViewById(R.id.tv_title);
            tv_des=itemView.findViewById(R.id.tv_des);
            tv_time=itemView.findViewById(R.id.tv_time);
            img=itemView.findViewById(R.id.img);
        }
    }
}
