package ir.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ir.news.R;
import ir.news.core.ApiClient;
import ir.news.interfaces.ApiInterface;

public class NewsActivity extends AppCompatActivity {

    public String news_title,news_publishedAt,news_author,news_content,news_urlToImage;
    public TextView tv_title,tv_con;
    public ImageView img,img_back;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        //////////////getting data//////////////////////
        news_title=getIntent().getStringExtra("news_title");
        news_publishedAt=getIntent().getStringExtra("news_publishedAt");
        news_content=getIntent().getStringExtra("news_content");
        news_urlToImage=getIntent().getStringExtra("news_urlToImage");

        tv_con=findViewById(R.id.tv_con);
        tv_title=findViewById(R.id.tv_title);
        img=findViewById(R.id.img);
        img_back=findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_title.setText(news_title);
        tv_con.setText(news_content);
        Picasso.get()
                .load(news_urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(img);
    }
}