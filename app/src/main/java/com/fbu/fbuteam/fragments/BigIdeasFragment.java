package com.fbu.fbuteam.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.HomeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigIdeasFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private TextView textView1;
    private TextView textView2;
    private CheckBox sportsTag;
    private CheckBox entTag;
    private CheckBox moneyTag;
    private CheckBox techTag;
    private CheckBox envTag;
    private CheckBox govTag;
    private CheckBox socialTag;
    private CheckBox militaryTag;
    private Button nextButton;
    CheckBox[] allTags = new CheckBox[8];
    ArrayList<CheckBox> selectedTags = new ArrayList<>();

    //****TESTS****

    public static List<List<String>> selectedBigIdeas = new ArrayList<>();

    //****TESTS****

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnNextClickListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.bigideas_fragment, container, false);
        initializeObjects(rootView, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createOnCheckedChangeListener();
        createCheckBoxArray();
        goToDetailTags();
    }

    private void createOnCheckedChangeListener() {
        sportsTag.setOnCheckedChangeListener(this);
        entTag.setOnCheckedChangeListener(this);
        moneyTag.setOnCheckedChangeListener(this);
        techTag.setOnCheckedChangeListener(this);
        envTag.setOnCheckedChangeListener(this);
        govTag.setOnCheckedChangeListener(this);
        socialTag.setOnCheckedChangeListener(this);
        militaryTag.setOnCheckedChangeListener(this);
    }

    private void initializeObjects(View rootView, View view) {
        sportsTag = (CheckBox) rootView.findViewById(R.id.sportsBox);
        entTag = (CheckBox) rootView.findViewById(R.id.entBox);
        moneyTag = (CheckBox) rootView.findViewById(R.id.moneyBox);
        techTag = (CheckBox) rootView.findViewById(R.id.techBox);
        envTag = (CheckBox) rootView.findViewById(R.id.envBox);
        govTag = (CheckBox) rootView.findViewById(R.id.govBox);
        socialTag = (CheckBox) rootView.findViewById(R.id.socialBox);
        militaryTag = (CheckBox) rootView.findViewById(R.id.militaryBox);
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        nextButton = view.findViewById(R.id.nextButton);
    }

    private boolean atLeastOneChecked() {
        boolean atLeastOneChecked = false;
        for (int i = 0; i < allTags.length; i++) {
            if (allTags[i].isChecked()) {
                selectedTags.add(allTags[i]);
                atLeastOneChecked = true;
                break;
            }
        }
        return atLeastOneChecked;
    }

    private void createCheckBoxArray() {
        allTags[0] = sportsTag;
        allTags[1] = entTag;
        allTags[2] = moneyTag;
        allTags[3] = techTag;
        allTags[4] = envTag;
        allTags[5] = govTag;
        allTags[6] = socialTag;
        allTags[7] = militaryTag;
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnItemSelectedListener {
        void goToDetailTagsFragment(List<List<String>> selectedBigIdeas);
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            if (!userSelectedBigIdeas.isEmpty()) { //if the arrayList of selected tags is NOT empty
                nextUserSelection++; //increment variable that keeps track of where we are in the list after user clicks next
                if (nextUserSelection <= userSelectedBigIdeas.size()) { //if the tracking variable is less than or equal to the size of the arrayList (we haven't gone through the whole list yet)
                    if (callback != null) {
                        Log.d("ArrayList Size", userSelectedBigIdeas.size() + ""); //log to check size of list
                        callback.changeFragments(); //continue setup
                    }
                } else { //if the whole list has been traversed, go to the Home Screen
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            } else {
                Toast.makeText(getContext(), "Please select at least one topic.", Toast.LENGTH_LONG).show();
            }
        });
    }

    //***TEST CLASSES***
    static class BigIdea {
        String name;
        List<SmallIdea> smallIdeas;

        public BigIdea(String name) {
            this.name = name;
        }
    }

    static class SmallIdea {
        String name;
    }
}
