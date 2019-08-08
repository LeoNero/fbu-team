package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.SavedPostsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SavedPostsFragment extends Fragment {

    List<String> mockData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.savedposts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvSavedPosts = (RecyclerView) view.findViewById(R.id.rvSavedPosts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSavedPosts.setLayoutManager(layoutManager);

        mockData = new ArrayList<>();
        mockData.add("Data1");
        mockData.add("Data2");
        mockData.add("Data3");
        mockData.add("Data4");
        mockData.add("Data5");

        SavedPostsAdapter adapter = new SavedPostsAdapter(getContext(), mockData);
        rvSavedPosts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
