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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fbu.fbuteam.models.NewsArticle;
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.HomeActivity;
import com.fbu.fbuteam.models.Node;
import com.fbu.fbuteam.models.User;
import com.fbu.fbuteam.adapters.NewsAdapter;
import com.fbu.fbuteam.utils.EndlessRecyclerViewScrollListener;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsFragment extends Fragment {

    public static final String TAG = "NewsFragment";
    public static final int newsArticlesPerQuery = 20;
    public static List<NewsArticle> currentRecommendations = new ArrayList<>();
    public RecyclerView rvNews;
    public static NewsAdapter adapter;
    public LinearLayoutManager linearLayoutManager;
    public SwipeRefreshLayout swipeContainer;
    public EndlessRecyclerViewScrollListener scrollListener;
    private List<String> userSelectedTagsId = new ArrayList<>();
    private Iterator<NewsArticle> currentRecommendationsIterator;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvNews = view.findViewById(R.id.rvNews);
        swipeContainer = view.findViewById(R.id.swipeContainer);

        setCurrentUser();
        setUserSelectedTagsId();
        setupRecycler();
        setup(view);
        loadPersonalizedNews();
        swipeRefresh();
    }

    private void setCurrentUser() {
        currentUser = User.getCurrentUser();
    }

    private void setUserSelectedTagsId() {
        List<Node> bigIdeaTags = currentUser.getBigIdeaTags();
        List<Node> detailTags = currentUser.getDetailTags();

        List<Node> allTags = new ArrayList<>();
        allTags.addAll(bigIdeaTags);
        allTags.addAll(detailTags);

        for (Node node : allTags) {
            userSelectedTagsId.add(node.getObjectId());
        }
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvNews.setLayoutManager(linearLayoutManager);
        endlessScrollListener();
        rvNews.addOnScrollListener(scrollListener);
        adapter = new NewsAdapter(getContext(), currentRecommendations);
        rvNews.setAdapter(adapter);
    }

    private void endlessScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getNextRecommendations();
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
            loadPersonalizedNews();
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

    private void loadPersonalizedNews() {
        NewsArticle.Query getAllQuery = new NewsArticle.Query();
        getAllQuery.withRelations();
        getAllQuery.setLimit(300);

        getAllQuery.findInBackground((objects, e) -> {
            if (e == null) {
                getRecommendations(objects);
            } else {
                e.printStackTrace();
            }
        });
    }

    private void getRecommendations(List<NewsArticle> articles) {
        currentRecommendations.clear();

        List<NewsArticle> allRecommendations = new ArrayList<>();
        for (NewsArticle article : articles) {
            List<ParseObject> articleTags = article.getTags();

            for (ParseObject tag : articleTags) {
                String tagId = tag.getObjectId();

                if (userSelectedTagsId.contains(tagId)) {
                    allRecommendations.add(article);
                    break;
                }
            }
        }

        currentRecommendationsIterator = allRecommendations.iterator();

        getNextRecommendations();
    }

    private void getNextRecommendations() {
        for (int i = 0; i < newsArticlesPerQuery; i++) {
            if (currentRecommendationsIterator.hasNext()) {
                currentRecommendations.add(currentRecommendationsIterator.next());
            } else {
                break;
            }
        }

        adapter.notifyDataSetChanged();
    }
}
