package ir.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ir.news.R;
import ir.news.core.ApiClient;
import ir.news.core.News;
import ir.news.database.BookmarksDatabase;
import ir.news.core.Bookmark;
import ir.news.interfaces.ApiInterface;

public class NewsActivity extends AppCompatActivity {

    private News news;
    public TextView tv_title, tv_con,tv_des,tv_time;
    public ImageView img, img_back, img_bookmark;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        //////////////getting data//////////////////////
        news= (News) getIntent().getSerializableExtra("news");

        tv_con = findViewById(R.id.tv_con);
        tv_title = findViewById(R.id.tv_title);
        img = findViewById(R.id.img);
        img_back = findViewById(R.id.img_back);
        img_bookmark = findViewById(R.id.img_bookmark);
        tv_des = findViewById(R.id.tv_des);
        tv_time = findViewById(R.id.tv_time);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_title.setText(news.title);
        tv_time.setText(news.publishedAt);
        tv_des.setText(news.description);
        tv_con.setText(news.content);
        Picasso.get()
                .load(news.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(img);


        img_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBookmark();
            }
        });

    }

    private void saveBookmark() {


        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(news.title);
        bookmark.setContent(news.content);
        bookmark.setDescription(news.description);
        bookmark.setPublishedAt(news.publishedAt);
        bookmark.setUrlToImage(news.urlToImage);


        @SuppressLint("StaticFieldLeak")
        class SaveBookmarkTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                BookmarksDatabase.getDatabase(getApplicationContext()).bookmarkDao().insertBookmark(bookmark);
                Log.i("save","SSSdoInBackground");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                Log.i("save","SSSonPostExecute");
                finish();
            }
        }

        new SaveBookmarkTask().execute();
    }


}