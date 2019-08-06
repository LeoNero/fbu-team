package com.fbu.fbuteam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private Context context;
    private List<Node> userTags;

    public TopicsAdapter(Context context, List<Node> userTags) {
        this.context = context;
        this.userTags = userTags;
    }

    @NonNull
    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_taglist, parent, false);
        return new TopicsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsAdapter.ViewHolder holder, int position) {
        Node userTag = userTags.get(position);
        displayTag(userTag, holder);
    }

    private void displayTag(Node userTag, TopicsAdapter.ViewHolder holder) {
        userTag.fetchIfNeededInBackground((res, e) -> {
            String name = ((Node) res).getName();
            int level = ((Node) res).getLevel();
            holder.topicView.setText(name);
            if (level == 1) {
                holder.topicView.setTextColor(Color.RED); //test colors
            } else {
                holder.topicView.setTextColor(Color.GREEN);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userTags.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView topicView;

        private ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvTopic);
            topicView = itemView.findViewById(R.id.topicText);
        }
    }
}
