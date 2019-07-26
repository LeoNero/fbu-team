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

import static java.security.AccessController.getContext;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private Context context;
    private List<Node> selectedBigIdeas;
    Node bigIdea;

    public TagsAdapter(Context context, List<Node> bigIdeas) {
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
        bigIdea = selectedBigIdeas.get(position);
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return selectedBigIdeas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox tagBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagBox = itemView.findViewById(R.id.tagBox);
        }

        public void bind() {
            //TODO bind the view elements to...?
            for (int i = 0; i < bigIdea.getChildren().size(); i++) {
                tagBox = new CheckBox(context);
                tagBox.setText(bigIdea.getChildren().get(i).getName());
            }
        }
    }
}
