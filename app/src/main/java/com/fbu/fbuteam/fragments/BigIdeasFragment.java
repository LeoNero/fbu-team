package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.BigIdeasAdapter;
import com.fbu.fbuteam.models.Node;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class BigIdeasFragment extends Fragment {

    private Button nextButton;
    private BigIdeasAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvBigIdeas;
    private List<Boolean> allTags = new ArrayList<>();
    private List<Boolean> selectedTags = new ArrayList<>();

    public static List<Node> selectedBigIdeas = new ArrayList<>();
    public List<Node> bigIdeas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnNextClickListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bigideas, container, false);
        initializeObjects(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createRecyclerView(view);
        queryNodes();
        goToDetailTags();
    }

    private void createRecyclerView(@NonNull View view) {
        rvBigIdeas = view.findViewById(R.id.rvBigIdeas);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvBigIdeas.setLayoutManager(layoutManager);
        adapter = new BigIdeasAdapter(getContext(), bigIdeas, allTags);
        rvBigIdeas.setAdapter(adapter);
    }

    private void initializeObjects(View view) {
        nextButton = view.findViewById(R.id.nextButton);
    }

    private boolean atLeastOneChecked() {
        boolean atLeastOneChecked = false;
        for (int i = 0; i < allTags.size(); i++) {
            if (allTags.get(i)) {
                selectedTags.add(allTags.get(i));
                atLeastOneChecked = true;
                break;
            }
        }
        return atLeastOneChecked;
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToDetailTagsFragment(List<Node> selectedBigIdeas);
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            if (atLeastOneChecked()) {
                if (callback != null) {
                    selectedBigIdeas = getSelectedBigIdeas();
                    saveBigIdeas();
                    callback.goToDetailTagsFragment(selectedBigIdeas);
                }
            } else {
                Toast.makeText(getContext(), "Please select at least one topic.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Node> getSelectedBigIdeas() {
        List<Node> selectedBigIdeas = new ArrayList<>();
        for (int i = 0; i < allTags.size(); i++) {
            if (allTags.get(i)) {
                Node bigIdea = bigIdeas.get(i);
                selectedBigIdeas.add(bigIdea);
            }
        }

        return selectedBigIdeas;
    }

    private void queryNodes() {
        Node.Query query = new Node.Query();
        query.withRelations();
        query.fromType("big-idea");
        query.findInBackground((response, e) -> {
            if (e == null) {
                for (Node node : response) {
                    allTags.add(false);
                    bigIdeas.add(node);
                }

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void saveBigIdeas() {
        ParseUser user = ParseUser.getCurrentUser();
        user.addAllUnique("bigIdeaTags", selectedBigIdeas);
        user.saveInBackground();
    }
}
