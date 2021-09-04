package ir.news.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.news.adapter.BookmarksAdapter;
import ir.news.R;
import ir.news.database.BookmarksDatabase;
import ir.news.core.Bookmark;

public class BookmarkFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Bookmark> bookmarksModelList;
    private BookmarksAdapter bookmarksAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmark, container, false);

        getBookmarks();

        recyclerView = v.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        bookmarksModelList = new ArrayList<>();
        bookmarksAdapter = new BookmarksAdapter(bookmarksModelList, getContext());
        recyclerView.setAdapter(bookmarksAdapter);
        bookmarksAdapter.notifyDataSetChanged();

        return v;
    }

    private void getBookmarks() {

        @SuppressLint("StaticFieldLeak")
        class GetBookmarksTask extends AsyncTask<Void, Void, List<Bookmark>> {


            @Override
            protected List<Bookmark> doInBackground(Void... voids) {
                return BookmarksDatabase.getDatabase(getContext()).bookmarkDao().getAllBookmark();

            }

            @Override
            protected void onPostExecute(List<Bookmark> bookmarks) {
                super.onPostExecute(bookmarks);
                if (bookmarksModelList.size() == 0) {
                    bookmarksModelList.addAll(bookmarks);

                    bookmarksAdapter.notifyDataSetChanged();

                } else {
                    bookmarksModelList.add(0, bookmarks.get(0));
                    bookmarksAdapter.notifyDataSetChanged();
                }
            }
        }

        new GetBookmarksTask().execute();
    }


}
