package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.fbu.fbuteam.R;
import java.util.ArrayList;
import java.util.List;

public class DetailTagsFragment extends Fragment {

    public static Button finishButton;
    private Button button;
    private List <String> children;
    private CheckBox tagOne;
    private CheckBox tagTwo;
    private CheckBox tagThree;
    private CheckBox tagFour;
    private CheckBox tagFive;
    private CheckBox tagSix;
    private CheckBox tagSeven;
    private CheckBox tagEight;
    private CheckBox tagNine;
    private CheckBox tagTen;

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
        initializeCheckBoxes(view);
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

    private void initializeCheckBoxes(View rootView) {
        tagOne = (CheckBox) rootView.findViewById(R.id.checkBox1);
        tagTwo = (CheckBox) rootView.findViewById(R.id.checkBox2);
        tagThree = (CheckBox) rootView.findViewById(R.id.checkBox3);
        tagFour = (CheckBox) rootView.findViewById(R.id.checkBox4);
        tagFive = (CheckBox) rootView.findViewById(R.id.checkBox5);
        tagSix = (CheckBox) rootView.findViewById(R.id.checkBox6);
        tagSeven = (CheckBox) rootView.findViewById(R.id.checkBox7);
        tagEight = (CheckBox) rootView.findViewById(R.id.checkBox8);
        tagNine = (CheckBox) rootView.findViewById(R.id.checkBox9);
        tagTen = (CheckBox) rootView.findViewById(R.id.checkBox10);
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToNextDetailsFragment();
    }
}
