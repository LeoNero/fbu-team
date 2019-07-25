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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    //private List<Node> newsNodes;
    private ArrayList<ArrayList<String>> newsNodes;

    public static ArrayList<ArrayList<String>> testNews = new ArrayList<>();
    public static int testArrayLength = 50;
    public static ArrayList<String> nestedArrayList = new ArrayList<>();

    public NewsAdapter(Context context, List<Node> newsNodes) {
        this.context = context;
        //this.newsNodes = newsNodes;
        this.newsNodes = testNews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_node, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Node node = newsNodes.get(position);

        for (position = 0; position < testNews.size(); position++) {
            holder.bind(testNews.get(position));
        }
    }

    @Override
    public int getItemCount () {
        //return newsNodes.size();
        return newsNodes.size();
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

        //public void bind(final Node node) {
        public void bind(ArrayList arrayListObject) {
            //TODO: bind the view elements to the news article node
            for (int x = 0; x < testNews.size(); x++) {
                tvTitle.setText(testNews.get(x).get(0));
                tvCreatedAt.setText(testNews.get(x).get(1));
                tvBodySnippet.setText(testNews.get(x).get(2));
            }
        }
    }

    public void clear () {
        //newsNodes.clear();
        for (int object = 0; object < testNews.size(); object++) {
            testNews.remove(object);
        }
        notifyDataSetChanged();
    }


    public void testNewsArray() {
        for (int i = 0; i < testArrayLength; i++){
            nestedArrayList.add("newsTitle");
            nestedArrayList.add("newsCreatedAt");
            nestedArrayList.add("newsBodySnippet");
            testNews.add(nestedArrayList);
        }
    }

    public void testNewsArrayRefresh() {
        for (int i = 0; i < testArrayLength; i++){
            nestedArrayList.add("newsTitle");
            nestedArrayList.add("newsCreatedAt");
            nestedArrayList.add("This is the beginning of an article");
            testNews.add(nestedArrayList);
        }
    }
}

