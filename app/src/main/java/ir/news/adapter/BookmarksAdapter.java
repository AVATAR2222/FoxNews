package ir.news.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.news.R;
import ir.news.core.Bookmark;
import ir.news.database.BookmarksDatabase;

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.UsersViewHolder> {

    List<Bookmark> bookmarksModelList;
    Context context;

    public BookmarksAdapter(List<Bookmark> bookmarksModelList, Context context) {
        this.bookmarksModelList = bookmarksModelList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public BookmarksAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_bookmarks,viewGroup,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarksAdapter.UsersViewHolder holder, int position) {

        Bookmark bookmarks=bookmarksModelList.get(position);
        holder.tv_title.setText(bookmarks.title);
        holder.tv_des.setText(bookmarks.description);
        holder.tv_time.setText(bookmarks.publishedAt);
        holder.tv_con.setText(bookmarks.content);
        Picasso.get()
                .load(bookmarks.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                @SuppressLint("StaticFieldLeak")
                class DeleteBookmarkTask extends AsyncTask<Void,Void,Void>{

                    @Override
                    protected Void doInBackground(Void... voids) {
                        BookmarksDatabase.getDatabase(context.getApplicationContext()).bookmarkDao().deleteBookmark(bookmarks);
//                        notifyDataSetChanged();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);

//                        notifyItemRangeRemoved(position, 1);
//                        notifyItemRangeChanged(position, getItemCount());

                        bookmarksModelList.remove(position);
                        notifyItemRemoved(position);

                    }
                }
                new DeleteBookmarkTask().execute();
            }
        });



        //////////////////sending data//////////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context, NewsActivity.class);
//                intent.putExtra("news_title",news.title);
//                intent.putExtra("news_publishedAt",news.publishedAt);
//                intent.putExtra("news_content",news.content);
//                intent.putExtra("news_urlToImage",news.urlToImage);
////                intent.putExtra("user_name",usersModel.getName());
////                intent.putExtra("user_email",usersModel.getEmail());
////                intent.putExtra("user_password",usersModel.getPassword());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookmarksModelList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_des,tv_time,tv_con;
        ImageView img,btn_delete;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_title=itemView.findViewById(R.id.tv_title);
            tv_con=itemView.findViewById(R.id.tv_con);
            tv_des=itemView.findViewById(R.id.tv_des);
            tv_time=itemView.findViewById(R.id.tv_time);
            img=itemView.findViewById(R.id.img);
            btn_delete=itemView.findViewById(R.id.btn_delete);
        }
    }
}
