package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;

public class NewsArticleDetailsFragment extends Fragment {

    private TextView articleName;
    private TextView articleAuthor;
    private TextView articleDateCreated;
    private TextView articleBody;
    private TextView articleSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_article_details, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initialize(view);


    }

    private void initialize(View view){
        articleName = view.findViewById(R.id.tvName);
        articleAuthor = view.findViewById(R.id.tvAuthor);
        articleDateCreated = view.findViewById(R.id.tvCreatedAt);
        articleBody = view.findViewById(R.id.tvBody);
        articleSource = view.findViewById(R.id.tvSource);
    }


}

