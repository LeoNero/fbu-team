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


import com.fbu.fbuteam.R;
import com.fbu.fbuteam.utils.EndlessRecyclerViewScrollListener;
import com.fbu.fbuteam.activities.HomeActivity;
import com.fbu.fbuteam.activities.NewsAdapter;

import org.w3c.dom.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NewsFragment extends Fragment {

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private List<Node> myNewsNodes;
    private LinearLayoutManager linearLayoutManager;
    public static final int newsNodesPerQuery = 20;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvNews = view.findViewById(R.id.rvNews);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        //TODO create the data source
        // Use this in the query for news articles to power recycler view
        myNewsNodes = new ArrayList<>();
        adapter = new NewsAdapter(getContext(), myNewsNodes);
        rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(linearLayoutManager);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        rvNews.addOnScrollListener(scrollListener);

        queryForNodes();
        swipeRefresh();
        adapter.testNewsArray();
        endlessScrollListener();

    }

    private void endlessScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextData();
            }

        };
    }

    private void  swipeRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HomeActivity.showProgressBar();
                adapter.clear();
                queryForNodes();
                adapter.testNewsArrayRefresh();
                HomeActivity.hideProgressBar();
                scrollListener.resetState();
                swipeContainer.setRefreshing(false);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void loadNextData() {
        ;
        //TODO query for next posts for endless scroll
    }

    private void queryForNodes() {
        ;
        //TODO query for top news articles
    }

}
