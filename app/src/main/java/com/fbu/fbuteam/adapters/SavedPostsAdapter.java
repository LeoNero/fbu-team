package com.fbu.fbuteam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.parse.ParseObject;

import java.util.List;

public class SavedPostsAdapter extends RecyclerView.Adapter<SavedPostsAdapter.ViewHolder> {

    Context context;
    List<ParseObject> newsArticles;


    public SavedPostsAdapter(Context context, List<ParseObject> newsArticles) {
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
        ParseObject article = newsArticles.get(position);
        holder.bind(article);
        displayMockData(article, holder);
    }

    private void displayMockData(ParseObject article, SavedPostsAdapter.ViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvTitle;
        TextView tvCreatedAt;
        TextView tvBodySnippet;

        private ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvMockArticle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvBodySnippet = itemView.findViewById(R.id.tvBodySnippet);
        }

        private void bind(ParseObject article) {
            tvTitle.setText(article.getName());
            tvCreatedAt.setText(Time.getRelativeTimeAgo(article.getCreatedAt().toString()));
            tvBodySnippet.setText(article.getBodySnippet());
        }
    }
}
