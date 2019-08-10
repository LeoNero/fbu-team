package com.fbu.fbuteam.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.Models.NewsArticle;
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.WebViewDetailsFragment;
import com.fbu.fbuteam.utils.Time;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewsArticle> newsArticles;
    public static int pos;

    public NewsAdapter(Context context, ArrayList<NewsArticle> listOfArticles) {
        this.context = context;
        this.newsArticles = listOfArticles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsArticle newsA = newsArticles.get(position);
        holder.bind(newsA);
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvCreatedAt;
        private ImageView savePost;

        private ViewHolder(View itemView) {
            super(itemView);
            setUpComponents(itemView);
            toastSave(savePost);
        }

        public void setUpComponents(View itemView) {
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            savePost = itemView.findViewById(R.id.savePost);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            pos = getAdapterPosition();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new WebViewDetailsFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutHome, fragment).addToBackStack(null).commit();
        }

        @Override
        public boolean onLongClick(View view) {
            pos = getAdapterPosition();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new WebViewDetailsFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutHome, fragment).addToBackStack(null).commit();
            return true;
        }

        private void bind(NewsArticle newsArticle) {
            tvTitle.setText(newsArticle.getName());
            tvCreatedAt.setText(Time.getRelativeTimeAgo(newsArticle.getCreatedAt().toString()));
            imageFormat(newsArticle);
        }

        private void imageFormat(NewsArticle newsArticle) {
            int radius = 20;
            int margin = 0;
            if (newsArticle.getImage().contains("npr") || newsArticle.getImage().contains("bbc")) {
                Glide.with(context)
                        .load("ht" + newsArticle.getImage())
                        .transform(new RoundedCornersTransformation(radius, margin))
                        .into(ivImage);
            } else if (newsArticle.getImage().contains("cdn")) {
                Glide.with(context)
                        .load("https://" + newsArticle.getImage())
                        .transform(new RoundedCornersTransformation(radius, margin))
                        .into(ivImage);
            }
        }
    }
    
    private void toastSave(ImageView savePost) {
        savePost.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "This post has been saved to your profile!", Toast.LENGTH_LONG).show();
        });
    }

    private void toastSave(ImageView savePost) {
        savePost.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "This post has been saved to your profile!", Toast.LENGTH_LONG).show();
        });
    }

    public void clear() {
        newsArticles.clear();
        notifyDataSetChanged();
    }
}