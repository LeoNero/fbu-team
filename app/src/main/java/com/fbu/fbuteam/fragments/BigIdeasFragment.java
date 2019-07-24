package com.fbu.fbuteam.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.nfc.Tag;
import android.os.Bundle;
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
import java.util.Queue;

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
    CheckBox[] checkBoxes = new CheckBox[8];
    ArrayList<CheckBox> selectedTags = new ArrayList<>();

    //****TESTS****

    public static List<BigIdea> allBigIdeas = new ArrayList<>();
    public static List<BigIdea> userSelectedBigIdeas = new ArrayList<>();
    private int nextUserSelection = 0;

    //****TESTS****

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnItemSelectedListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.bigideas_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeObjects(view);
        goToDetailTags();
    }

    private void initializeObjects(View view) {
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        sportsTag = view.findViewById(R.id.sportsBox);
        entTag = view.findViewById(R.id.entBox);
        moneyTag = view.findViewById(R.id.moneyBox);
        techTag = view.findViewById(R.id.techBox);
        envTag = view.findViewById(R.id.envBox);
        govTag = view.findViewById(R.id.govBox);
        socialTag = view.findViewById(R.id.socialBox);
        militaryTag = view.findViewById(R.id.militaryBox);
        nextButton = view.findViewById(R.id.nextButton);

        allBigIdeas.add(new BigIdea("Sports")); //index 0
        allBigIdeas.add(new BigIdea("Entertainment"));
        allBigIdeas.add(new BigIdea("Money"));
        allBigIdeas.add(new BigIdea("Technology"));
        allBigIdeas.add(new BigIdea("Environment"));
        allBigIdeas.add(new BigIdea("Government"));
        allBigIdeas.add(new BigIdea("Social Justice"));
        allBigIdeas.add(new BigIdea("Military"));
    }

//    private boolean atLeastOneChecked() {
//
//        checkBoxes[0] = sportsTag;
//        checkBoxes[1] = entTag;
//        checkBoxes[2] = moneyTag;
//        checkBoxes[3] = techTag;
//        checkBoxes[4] = envTag;
//        checkBoxes[5] = govTag;
//        checkBoxes[6] = socialTag;
//        checkBoxes[7] = militaryTag;
//
//        boolean atLeastOneChecked = false;
//
//        for (int i = 0; i < checkBoxes.length; i++) {
//            if (checkBoxes[i].isChecked()) {
//                selectedTags.add(checkBoxes[i]);
//                atLeastOneChecked = true;
//                break;
//            }
//        }
//        return atLeastOneChecked;
//    }

    public void setOnItemSelectedListener(OnItemSelectedListener callback) {
        this.callback = callback;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.sportsBox:
                if (sportsTag.isChecked()) {
                    Toast.makeText(getContext(), "It worked", Toast.LENGTH_LONG).show();
                    //***TESTS***
                    //add selected boxes to userSelectedBigIdeas
                    userSelectedBigIdeas.add(allBigIdeas.get(0));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(0));
                }
                break;
            case R.id.entBox:
                if (entTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(1));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(1));
                }
                break;
            case R.id.moneyBox:
                if (moneyTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(2));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(2));
                }
                break;
            case R.id.techBox:
                if (entTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(3));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(3));
                }
                break;
            case R.id.envBox:
                if (envTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(4));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(4));
                }
                break;
            case R.id.govBox:
                if (govTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(5));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(5));
                }
                break;
            case R.id.socialBox:
                if (socialTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(6));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(6));
                }
                break;
            case R.id.militaryBox:
                if (militaryTag.isChecked()) {
                    userSelectedBigIdeas.add(allBigIdeas.get(7));
                } else {
                    userSelectedBigIdeas.remove(allBigIdeas.get(7));
                }
                break;
        }
    }

    public interface OnItemSelectedListener {
        void changeFragments();
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            nextUserSelection++; //increment variable that keeps track of where we are in the list
            if (!userSelectedBigIdeas.isEmpty()) { //if the arrayList of selected tags is NOT empty
                if (nextUserSelection <= userSelectedBigIdeas.size()) { //if the tracking variable is less than or equal to the size of the arrayList (we haven't gone through the whole list yet)
                    if (callback != null) {
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
