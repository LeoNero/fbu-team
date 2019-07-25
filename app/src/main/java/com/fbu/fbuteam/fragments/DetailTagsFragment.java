package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.fbu.fbuteam.R;
import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    private Button finishButton;
    private Button button;
    private List <String> children;

    private OnNextClickListener callback;

    public static DetailTagsFragment newInstance(List<String> children) {
        DetailTagsFragment detailTagsFragment = new DetailTagsFragment();
        Bundle args = new Bundle();
        //Will change later to putParcelableArray
        args.putStringArrayList("children", (ArrayList<String>) children);
        detailTagsFragment.setArguments(args);
        return detailTagsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        children = getArguments().getStringArrayList("children");
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
        setTextBasedOnChildren();
        nextClick();
    }

    private void setTextBasedOnChildren() {
        String a = "";
        for (String b : children) {
            a += b;
            a += " ";
        }
        button.setText(a);
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

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToNextDetailsFragment();
    }
}
