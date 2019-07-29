package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.TagActivity;
import com.fbu.fbuteam.models.Node;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    public static Button finishButton;
    private Button button;
    private OnNextClickListener callback;
    private DetailTagsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvTags;
    private Node bigIdea;
    private List<Node> bigIdeasList;
    private int currentUserSelection; //TODO this needs to go

    public static DetailTagsFragment newInstance(Node bigIdea, int currentUserSelection) {
        DetailTagsFragment detailTagsFragment = new DetailTagsFragment();
        Bundle args = new Bundle();
        args.putParcelable("bigIdea", bigIdea);
        args.putInt("currentUserSelection", currentUserSelection);
        detailTagsFragment.setArguments(args);
        return detailTagsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bigIdea = getArguments().getParcelable("bigIdea");
        currentUserSelection = getArguments().getInt("currentUserSelection");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailtags_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //super.onViewCreated(view, savedInstanceState);
        rvTags = (RecyclerView) view.findViewById(R.id.rvTags);
        //set the layout manager on the recycler view
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvTags.setLayoutManager(layoutManager);

        //create the data source
        bigIdeasList = new ArrayList<>();
        //create the adapter
        adapter = new DetailTagsAdapter(getContext(), bigIdeasList);
        //set the adapter on the recycler view
        rvTags.setAdapter(adapter);
        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            bigIdeasList.add(bigIdea.getChildren().get(i));
            Log.d("ZZ", bigIdea.getChildren().get(i).getName());
        }
        //adapter.notifyDataSetChanged();

        initializeObjects(view);
        setTextBasedOnBigIdea();
        populateDetailTags();
        nextClick();
    }

    private void setTextBasedOnBigIdea() {
        button.setText(bigIdea.getName());
    }

    private void initializeObjects(@NonNull View view) {
        finishButton = view.findViewById(R.id.finishButton);
        button = view.findViewById(R.id.button);
    }

    private void nextClick() {
        finishButton.setOnClickListener(view -> callback.goToNextDetailsFragment());
    }

    private void populateDetailTags() {
        //create checkBoxes dynamically
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
