package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.fbu.fbuteam.Models.NewsArticle;
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.NewsAdapter;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

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
        setItems();

    }

    private void initialize(View view){
        articleName = view.findViewById(R.id.tvName);
        articleAuthor = view.findViewById(R.id.tvAuthor);
        articleDateCreated = view.findViewById(R.id.tvCreatedAt);
        articleBody = view.findViewById(R.id.tvBody);
        articleSource = view.findViewById(R.id.tvSource);
    }

    private void setItems(){
        ParseQuery<NewsArticle> newsArticleQuery = new ParseQuery<>(NewsArticle.class);
        newsArticleQuery.findInBackground((objects, e) -> {
            if (e != null) {
                Log.e("Details", "Error with query");
                e.printStackTrace();
            }
            else {
                NewsArticle newsArticle = objects.get(NewsFragment.clickPosition);
                Log.d("Details", "Name: " + newsArticle.getName()
                        + " Author: " + newsArticle.getAuthor()
                        + " Created At: " + newsArticle.getCreatedAt().toString()
                        + " Body: " + newsArticle.getBody()
                        + " Source: " + newsArticle.getSource());
                articleName.setText(newsArticle.getName());
                articleAuthor.setText(newsArticle.getAuthor());
                articleDateCreated.setText(newsArticle.getCreatedAt().toString());
                articleBody.setText(newsArticle.getBody());
                articleSource.setText(newsArticle.getSource());

            }
        });
    }


}
