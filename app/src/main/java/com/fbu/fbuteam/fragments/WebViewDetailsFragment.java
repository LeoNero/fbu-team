package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.Models.NewsArticle;
import com.fbu.fbuteam.R;


public class WebViewDetailsFragment extends Fragment {

    private WebView detailsWebView;
    private String articleSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_view_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsWebView = view.findViewById(R.id.webView);
        setItems();
        detailsWebView.loadUrl(articleSource);
    }

    private void setItems(){
        NewsArticle newsArticle = NewsFragment.news.get(NewsFragment.clickPosition);
        articleSource = newsArticle.getSource();
    }
}
