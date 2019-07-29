package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.TagActivity;
import com.fbu.fbuteam.models.Node;

import java.util.List;

public class DetailTagsAdapter extends RecyclerView.Adapter<DetailTagsAdapter.ViewHolder> {

    private Context context;
    private List<Node> selectedBigIdeas;

    public DetailTagsAdapter(Context context, List<Node> bigIdeas) {
        this.context = context;
        this.selectedBigIdeas = bigIdeas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Node bigIdea = selectedBigIdeas.get(position);
        holder.tagBox.setText(bigIdea.getName());
    }

    @Override
    public int getItemCount() {
        return selectedBigIdeas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox tagBox;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagBox = itemView.findViewById(R.id.tagBox);
        }
    }
}
