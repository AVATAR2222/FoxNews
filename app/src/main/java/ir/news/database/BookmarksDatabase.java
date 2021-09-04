package ir.news.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ir.news.core.Bookmark;
import ir.news.interfaces.BookmarkDao;

@Database(entities = Bookmark.class, version = 1, exportSchema = false)
public abstract class BookmarksDatabase extends RoomDatabase {

    private static BookmarksDatabase bookmarksDatabase;

    public static synchronized BookmarksDatabase getDatabase(Context context){
        if (bookmarksDatabase==null){
            bookmarksDatabase= Room.databaseBuilder(context,BookmarksDatabase.class,"bookmarks_db").build();
        }return bookmarksDatabase;
    }

    public abstract BookmarkDao bookmarkDao();
}
