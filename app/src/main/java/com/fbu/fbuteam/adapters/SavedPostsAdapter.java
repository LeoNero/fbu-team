package com.fbu.fbuteam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;

import java.util.List;

public class SavedPostsAdapter extends RecyclerView.Adapter<SavedPostsAdapter.ViewHolder> {

    Context context;
    List<String> mockData;


    public SavedPostsAdapter(Context context, List<String> mockData) {
        this.context = context;
        this.mockData = mockData;
    }

    @NonNull
    @Override
    public SavedPostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_savedpost, parent, false);
        return new SavedPostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPostsAdapter.ViewHolder holder, int position) {
        String dataPoint = mockData.get(position);
        displayMockData(dataPoint, holder);
    }

    private void displayMockData(String dataPoint, SavedPostsAdapter.ViewHolder holder) {
        holder.tvMockData.setText(dataPoint);
        holder.tvMockData.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return mockData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMockData;

        private ViewHolder(View itemView) {
            super(itemView);

            tvMockData = itemView.findViewById(R.id.tvMockData);
        }
    }
}
