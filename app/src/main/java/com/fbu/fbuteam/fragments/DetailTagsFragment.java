package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    public static Button finishButton;
    private TextView textView;
    private OnNextClickListener callback;
    private DetailTagsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvTags;
    private Node bigIdea;
    private List<Node> bigIdeasList;

    public static DetailTagsFragment newInstance(Node bigIdea) {
        DetailTagsFragment detailTagsFragment = new DetailTagsFragment();
        Bundle args = new Bundle();
        args.putParcelable("bigIdea", bigIdea);
        detailTagsFragment.setArguments(args);
        return detailTagsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bigIdea = getArguments().getParcelable("bigIdea");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailtags_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        createRV(view);
        initializeObjects(view);
        setTextBasedOnBigIdea();
        populateDetailTags();
        nextClick();
    }

    private void createRV(@NonNull View view) {
        rvTags = (RecyclerView) view.findViewById(R.id.rvTags);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvTags.setLayoutManager(layoutManager);
        bigIdeasList = new ArrayList<>();
        adapter = new DetailTagsAdapter(getContext(), bigIdeasList);
        rvTags.setAdapter(adapter);

        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            bigIdeasList.add(bigIdea.getChildren().get(i));
        }
    }

    private void setTextBasedOnBigIdea() {
        textView.setText(bigIdea.getName());
    }

    private void initializeObjects(@NonNull View view) {
        finishButton = view.findViewById(R.id.finishButton);
        textView = view.findViewById(R.id.textView);
    }

    private void nextClick() {
        finishButton.setOnClickListener(view -> callback.goToNextDetailsFragment());
    }

    private void populateDetailTags() {
        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            CheckBox tagBox = new CheckBox(getContext());
            tagBox.setText(bigIdea.getChildren().get(i).getName());
        }
        adapter.notifyDataSetChanged();
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToNextDetailsFragment();
    }
}
