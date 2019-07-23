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
import com.fbu.fbuteam.activities.NewsAdapter;

import org.w3c.dom.Node;

import java.util.List;


public class NewsFragment extends Fragment {

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private List<Node> myNewsNodes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvNews = view.findViewById(R.id.rvNews);

        //TODO create the data source
        //myNewsNodes = new ArrayList<>();
        // Use this in the query for news articles to power recycler view

        //creating the adapter
        adapter = new NewsAdapter(getContext(), myNewsNodes);
        //set the adapter on the recycler view
        rvNews.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
