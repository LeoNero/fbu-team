package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.fbu.fbuteam.adapters.BigIdeasAdapter;
import com.fbu.fbuteam.models.Node;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class BigIdeasFragment extends Fragment {

    private Button nextButton;
    private BigIdeasAdapter adapter;
    private List<Boolean> allTags = new ArrayList<>();
    private List<Boolean> selectedTags = new ArrayList<>();
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    public static List<Node> selectedBigIdeas = new ArrayList<>();
    public List<Node> bigIdeas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar();

    }

    private OnNextClickListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bigideas_fragment, container, false);
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
        RecyclerView rvBigIdeas = (RecyclerView) view.findViewById(R.id.rvBigIdeas);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvBigIdeas.setLayoutManager(layoutManager);
        adapter = new BigIdeasAdapter(getContext(), bigIdeas, allTags);
        rvBigIdeas.setAdapter(adapter);
    }

    private void initializeObjects(View view) {
        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);
        nextButton = view.findViewById(R.id.nextButton);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
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
