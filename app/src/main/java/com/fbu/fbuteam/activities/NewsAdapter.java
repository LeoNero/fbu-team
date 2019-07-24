package com.fbu.fbuteam.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;

import org.w3c.dom.Node;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<Node> newsNodes;

    public NewsAdapter(Context context, List<Node> newsNodes) {
        this.context = context;
        this.newsNodes = newsNodes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_node, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Node node = newsNodes.get(position);
        holder.bind(node);
    }

    @Override
    public int getItemCount () {
        return newsNodes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvCreatedAt;
        private TextView tvBodySnippet;

        private ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvBodySnippet = itemView.findViewById(R.id.tvBodySnippet);
        }

        private void bind(final Node node) {
            //TODO: bind the view elements to the news article node
        }
    }

    public void clear () {
        newsNodes.clear();
        notifyDataSetChanged();
    }

    // Clean all elements of the recycler
    public void clear() {
        newsNodes.clear();
        notifyDataSetChanged();
    }
}

