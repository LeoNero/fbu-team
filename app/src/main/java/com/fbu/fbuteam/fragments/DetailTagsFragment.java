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
import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    public static Button finishButton;
    private Button button;
    private Node bigIdea;
    private OnNextClickListener callback;
    private ConstraintLayout layout;

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
        initializeObjects(view);
        setTextBasedOnBigIdea();
        populateDetailTags(view);
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
        finishButton.setOnClickListener(view -> {
            callback.goToNextDetailsFragment();
        });
    }

    private void populateDetailTags(View view) {
        //create checkBoxes dynamically
        layout = view.findViewById(R.id.layout);
        for (int i = 0; i < bigIdea.getChildren().size(); i++) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(bigIdea.getChildren().get(i).getName());
            layout.addView(checkBox);
        }
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToNextDetailsFragment();
    }
}
