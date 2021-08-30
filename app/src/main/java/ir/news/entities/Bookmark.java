package ir.news.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bookmarks")
public class Bookmark implements Serializable {

@PrimaryKey(autoGenerate = true)
    public int id;

@ColumnInfo(name = "publishedAt")
    public String publishedAt;

@ColumnInfo(name = "content")
    public String content;

@ColumnInfo(name = "title")
    public String title;

@ColumnInfo(name = "urlToImage")
    public String urlToImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

//    @Nullable
//    @Override
//    public String toString() {
//        return title + " : " + dateTime;
//    }
}
