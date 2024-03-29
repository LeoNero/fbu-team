package com.fbu.fbuteam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.List;

public class DetailTagsAdapter extends RecyclerView.Adapter<DetailTagsAdapter.ViewHolder> {

    private Context context;
    private List<Node> selectedBigIdeas;
    private List<Boolean> listOfChecked;

    public DetailTagsAdapter(Context context, List<Node> bigIdeas, List<Boolean> listOfChecked) {
        this.context = context;
        this.selectedBigIdeas = bigIdeas;
        this.listOfChecked = listOfChecked;
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
        changeCheckedState(holder, position);
        setCheckBoxText(holder, bigIdea);
    }

    private void setCheckBoxText(@NonNull ViewHolder holder, Node bigIdea) {
        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            holder.tagBox.setText(bigIdea.getChildren().get(i).getName());
        }
    }

    private void changeCheckedState(@NonNull ViewHolder holder, int position) {
        holder.tagBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            listOfChecked.set(position, isChecked);
            checkColorChange(holder, position);
        });
    }

    private void checkColorChange(@NonNull ViewHolder holder, int position) {
        if (listOfChecked.get(position)) {
            holder.cardView.setCardBackgroundColor(Color.rgb(83, 29, 85));
            holder.tagBox.setTextColor(Color.WHITE);
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
            holder.tagBox.setTextColor(Color.rgb(56, 14, 67));
        }
    }

    @Override
    public int getItemCount() {
        return selectedBigIdeas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox tagBox;
        CardView cardView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagBox = itemView.findViewById(R.id.tagBox);
            cardView = itemView.findViewById(R.id.cardview1);
        }
    }
}
