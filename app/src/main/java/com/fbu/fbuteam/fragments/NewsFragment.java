package com.fbu.fbuteam.fragments;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fbu.fbuteam.Models.NewsArticle;
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.HomeActivity;
import com.fbu.fbuteam.activities.NewsAdapter;
import com.fbu.fbuteam.utils.EndlessRecyclerViewScrollListener;
import com.parse.ParseQuery;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    public static final String TAG = "NewsFragment";
    public static final int newsArticlesPerQuery = 20;
    public RecyclerView rvNews;
    public static NewsAdapter adapter;
    public static ArrayList<NewsArticle> news;
    public LinearLayoutManager linearLayoutManager;
    public SwipeRefreshLayout swipeContainer;
    public EndlessRecyclerViewScrollListener scrollListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvNews = view.findViewById(R.id.rvNews);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        setupRecycler();
        setup(view);
        loadArticles();
        swipeRefresh();
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvNews.setLayoutManager(linearLayoutManager);
        news = new ArrayList<>();
        endlessScrollListener();
        rvNews.addOnScrollListener(scrollListener);
        adapter = new NewsAdapter(getContext(), news);
        rvNews.setAdapter(adapter);
    }

    private void endlessScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextData();
            }
        };
    }

    private void setup(View view) {
        swipeContainer = view.findViewById(R.id.swipeContainer);
    }

    private void swipeRefresh() {
        swipeContainer.setOnRefreshListener(() -> {
            HomeActivity.showProgressBar();
            adapter.clear();
            loadArticles();
            HomeActivity.hideProgressBar();
            scrollListener.resetState();
            swipeContainer.setRefreshing(false);
        });
        swipeContainerColorScheme();
    }

    private void swipeContainerColorScheme() {
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void loadNextData() {
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        newsArticleQuery.setLimit(newsArticlesPerQuery);
        newsArticleQuery.whereLessThan("createdAt", news.get(news.size()-1).getCreatedAt());
        newsArticleQuery.addDescendingOrder(NewsArticle.KEY_CREATED_AT);
        newsArticleQuery.findInBackground((objects, e) -> {
            if (e != null) {
                Log.e(TAG, "Error with query");
                e.printStackTrace();
            } else {
                news.addAll(objects);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < objects.size(); i++) {
                    NewsArticle newsArticle = objects.get(i);
                    Log.d(TAG, "Name: " + newsArticle.getName()
                            + " Created At: " + newsArticle.getCreatedAt().toString()
                            + " Body Snippet: " + newsArticle.getBodySnippet());
                }
            }
        });
    }

    private void loadArticles() {
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        newsArticleQuery.setLimit(newsArticlesPerQuery);
        newsArticleQuery.addDescendingOrder(NewsArticle.KEY_CREATED_AT);
        newsArticleQuery.findInBackground((objects, e) -> {
            if (e != null) {
                Log.e(TAG, "Error with query");
                e.printStackTrace();
            }
            else {
                news.addAll(objects);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < objects.size(); i++) {
                    NewsArticle newsArticle = objects.get(i);
                    Log.d(TAG, "Name: " + newsArticle.getName()
                            + " Created At: " + newsArticle.getCreatedAt().toString()
                            + " Body Snippet: " + newsArticle.getBodySnippet());
                }
            }
        });
    }

    public static void searchNameQuery(String query) {
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        //ParseQuery<NewsArticle> newsArticleQuery = ParseQuery.getQuery("NewsArticle");
        newsArticleQuery.whereFullText("Body", query);
        newsArticleQuery.findInBackground((articles, e) -> {
            if (e != null) {
                Log.e(TAG, "Error with query");
                e.printStackTrace();
            } else {
                news.addAll(articles);
                Log.d("AA", news.size()+"");
                adapter.notifyDataSetChanged();
                for (int i = 0; i < articles.size(); i++) {
                    NewsArticle newsArticle = articles.get(i);
                    Log.d(TAG, "Name: " + newsArticle.getName()
                            + " Created At: " + newsArticle.getCreatedAt().toString()
                            + " Body Snippet: " + newsArticle.getBodySnippet());
                }
            }
        });
    }

    public static void searchAuthorQuery(String query) {
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        newsArticleQuery.whereContains(NewsArticle.KEY_AUTHOR, query) ;
        newsArticleQuery.findInBackground((objects, e) -> {
            if (e != null) {
                Log.e(TAG, "Error with query");
                e.printStackTrace();
            } else {
                news.addAll(objects);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < objects.size(); i++) {
                    NewsArticle newsArticle = objects.get(i);
                    Log.d(TAG, "Name: " + newsArticle.getName()
                            + " Created At: " + newsArticle.getCreatedAt().toString()
                            + " Body Snippet: " + newsArticle.getBodySnippet());
                }
            }
        });
    }