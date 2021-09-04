package ir.news.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ir.news.core.Bookmark;

@Dao
public interface BookmarkDao {

    @Query("SELECT * FROM bookmarks")
    List<Bookmark> getAllBookmark();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookmark(Bookmark bookmark);

    @Delete
    void deleteBookmark(Bookmark bookmark);
}
