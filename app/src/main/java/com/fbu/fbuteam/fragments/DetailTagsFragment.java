package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.DetailTagsAdapter;
import com.fbu.fbuteam.models.Node;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    public Button finishButton;
    private TextView textView;
    private OnNextClickListener callback;
    private DetailTagsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvTags;
    private Node bigIdea;
    private List<Node> bigIdeasList;
    private List<Boolean> allTags = new ArrayList<>();
    private List<Boolean> selectedTags = new ArrayList<>();
    public static List<Node> selectedDetails = new ArrayList<>();
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

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
        progressBar();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailtags_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createRecyclerView(view);
        initializeObjects(view);
        setTextBasedOnBigIdea();
        nextClick();
    }

    private void progressBar() {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createRecyclerView(@NonNull View view) {
        rvTags = (RecyclerView) view.findViewById(R.id.rvTags);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvTags.setLayoutManager(layoutManager);
        bigIdeasList = new ArrayList<>();
        adapter = new DetailTagsAdapter(getContext(), bigIdeasList, allTags);
        rvTags.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        populateLists();
    }

    private void populateLists() {
        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            bigIdeasList.add(bigIdea.getChildren().get(i));
            allTags.add(false);
        }
    }

    private void setTextBasedOnBigIdea() {
        textView.setText(bigIdea.getName());
    }

    private void initializeObjects(@NonNull View view) {
        finishButton = view.findViewById(R.id.finishButton);
        textView = view.findViewById(R.id.textView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    private void nextClick() {
        finishButton.setOnClickListener(view -> {
            if (atLeastOneChecked()) {
                if (callback != null) {
                    selectedDetails = getSelectedDetails();
                    saveDetailTags();
                    callback.goToNextDetailsFragment();
                }
            } else {
                Toast.makeText(getContext(), "Please select at least one topic.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Node> getSelectedDetails() {
        List<Node> selectedDetails = new ArrayList<>();
        for (int i = 0; i < allTags.size(); i++) {
            if (allTags.get(i)) {
                Node bigIdea = bigIdeasList.get(i);
                selectedDetails.add(bigIdea);
            }
        }
        return selectedDetails;
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
        void goToNextDetailsFragment();
    }

    private void saveDetailTags() {
        ParseUser user = ParseUser.getCurrentUser();
        user.addAllUnique("detailTags", selectedDetails);
        user.saveInBackground();
    }
 }
