package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.List;

public class BigIdeasAdapter extends RecyclerView.Adapter<BigIdeasAdapter.ViewHolder> {

    private Context context;
    private List<Node> bigIdeas;
    private List<Boolean> listOfChecked;

    public BigIdeasAdapter(Context context, List<Node> bigIdeas, List<Boolean> isChecked) {
        this.context = context;
        this.bigIdeas = bigIdeas;
        this.listOfChecked = isChecked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bigidea_item, parent, false);
        return new BigIdeasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bigIdeaBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            listOfChecked.set(position, isChecked);
        });

        Node bigIdea = bigIdeas.get(position);
        holder.bigIdeaBox.setText(bigIdea.getName());
        holder.bigIdeaBox.setChecked(listOfChecked.get(position));
    }

    @Override
    public int getItemCount() {
        return bigIdeas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox bigIdeaBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bigIdeaBox = (CheckBox) itemView.findViewById(R.id.bigIdeaBox);
        }
    }
}
