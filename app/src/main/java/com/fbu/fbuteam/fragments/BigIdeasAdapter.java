package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.List;

public class BigIdeasAdapter extends RecyclerView.Adapter<BigIdeasAdapter.ViewHolder> {

    private Context context;
    private List<Node> bigIdeas;
    private List<Boolean> listOfChecked;

    public BigIdeasAdapter(Context context, List<Node> bigIdeas, List<Boolean> listOfChecked) {
        this.context = context;
        this.bigIdeas = bigIdeas;
        this.listOfChecked = listOfChecked;
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
            if (listOfChecked.get(position)) {
                holder.cardView.setCardBackgroundColor(Color.rgb(83, 29, 85));
                holder.bigIdeaBox.setTextColor(Color.WHITE);
            }
        });

        for (int i = 0; i < bigIdeas.size(); i++) {
            Node bigIdea = bigIdeas.get(position);
            holder.bigIdeaBox.setText(bigIdea.getName());
            holder.bigIdeaBox.setChecked(listOfChecked.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return bigIdeas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox bigIdeaBox;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bigIdeaBox = (CheckBox) itemView.findViewById(R.id.bigIdeaBox);
            cardView = (CardView) itemView.findViewById(R.id.cardview1);
        }
    }
}
