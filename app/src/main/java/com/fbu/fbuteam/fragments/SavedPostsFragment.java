package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.SavedPostsAdapter;
import com.fbu.fbuteam.models.NewsArticle;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SavedPostsFragment extends Fragment {
    public static List<NewsArticle> newsArticles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.savedposts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvSavedPosts = (RecyclerView) view.findViewById(R.id.rvSavedPosts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSavedPosts.setLayoutManager(layoutManager);

        SavedPostsAdapter adapter = new SavedPostsAdapter(getContext(), newsArticles);
        createMockSavedArticles(adapter);
        rvSavedPosts.setAdapter(adapter);
    }

    private void createMockSavedArticles(SavedPostsAdapter adapter) {
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        newsArticleQuery.setLimit(8);
        newsArticleQuery.addDescendingOrder(NewsArticle.KEY_CREATED_AT);
        newsArticleQuery.findInBackground((objects, e) -> {
            if (e == null) {
                newsArticles.addAll(objects);
            }
            adapter.notifyDataSetChanged();
        });
    }
}
