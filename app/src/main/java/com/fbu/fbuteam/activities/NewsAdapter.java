package com.fbu.fbuteam.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fbu.fbuteam.R;
import com.parse.ParseFile;

import org.w3c.dom.Node;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<Node> news;

    public NewsAdapter(Context context, List<Node> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Node news = news.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvCreatedAt;
        private TextView tvBodySnippet;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvBodySnippet = itemView.findViewById(R.id.tvBodySnippet);

        }

    }

    public void bind(Node news) {
        //TODO: bind the view elements to the post
    }

}

