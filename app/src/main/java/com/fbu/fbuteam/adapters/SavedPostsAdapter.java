package com.fbu.fbuteam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.WebViewDetailsFragment;
import com.fbu.fbuteam.models.NewsArticle;
import com.fbu.fbuteam.utils.Time;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.fbu.fbuteam.fragments.NewsFragment.news;
import static com.fbu.fbuteam.fragments.NewsFragment.newsArticlesPerQuery;

public class SavedPostsAdapter extends RecyclerView.Adapter<SavedPostsAdapter.ViewHolder> {

    Context context;
    List<NewsArticle> newsArticles;
    NewsArticle article;
    public static int pos;


    public SavedPostsAdapter(Context context, List<NewsArticle> newsArticles) {
        this.context = context;
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public SavedPostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_savedpost, parent, false);
        return new SavedPostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPostsAdapter.ViewHolder holder, int position) {
        article = newsArticles.get(position);
        displayMockData(article);
        holder.bind(article);
    }

    private void displayMockData(NewsArticle article) {
        for (int i = 0; i < 9; i++) {
            loadArticles(article);
        }
    }

    private void loadArticles(NewsArticle article) {
        for (int i = 0; i < newsArticles.size(); i++) {
            Log.d(TAG, "Name: " + article.getName()
                    + " Created At: " + article.getCreatedAt().toString()
                    + " Body Snippet: " + article.getBodySnippet());
        }
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCreatedAt;
        private ImageView ivImage;

        private ViewHolder(View itemView) {
            super(itemView);

            setUpComponents(itemView);
        }

        private void setUpComponents(View itemView) {
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ivImage = itemView.findViewById(R.id.ivImage);
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
                        .load(newsArticle.getImage())
                        .transform(new RoundedCornersTransformation(radius, margin))
                        .into(ivImage);
            } else if (newsArticle.getImage().contains("cdn")) {
                Glide.with(context)
                        .load(newsArticle.getImage())
                        .transform(new RoundedCornersTransformation(radius, margin))
                        .into(ivImage);
            }
        }
    }
}
