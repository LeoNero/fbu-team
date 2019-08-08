package com.fbu.fbuteam.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.DetailTagsAdapter;
import com.fbu.fbuteam.adapters.TopicsAdapter;
import com.fbu.fbuteam.models.Node;
import com.fbu.fbuteam.models.User;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class TopicsFragment extends Fragment {

    private User currentUser;
    private List<Node> bigIdeaTags;
    private List<Node> detailTags;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBigIdeasTags();
        getDetailTags();
        createRecyclerView(view);
    }

    private void createRecyclerView(View view) {
        RecyclerView rvTopics = (RecyclerView) view.findViewById(R.id.rvTopics);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvTopics.setLayoutManager(layoutManager);
        List<Node> userTags = new ArrayList<>();
        userTags.addAll(bigIdeaTags);
        userTags.addAll(detailTags);
        bigIdeaTags = new ArrayList<>();
        detailTags = new ArrayList<>();
        TopicsAdapter adapter = new TopicsAdapter(getContext(), userTags);
        rvTopics.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getCurrentUser() {
        currentUser = User.getCurrentUser();
    }

    private void getBigIdeasTags() {
        bigIdeaTags = currentUser.getBigIdeaTags();
    }

    private void getDetailTags() {
        detailTags = currentUser.getDetailTags();
    }
}
