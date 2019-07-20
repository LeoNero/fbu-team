package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;

public class BigIdeasFragment extends Fragment {

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

    private OnItemSelectedListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bigideas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeObjects(view);
        onCheckboxClicked(view);
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
    }

    public void onCheckboxClicked(View view) {
        switch (view.getId()) {
            case R.id.sportsBox:
                if (sportsTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.entBox:
                if (entTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.moneyBox:
                if (moneyTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.techBox:
                if (techTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.envBox:
                if (envTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.govBox:
                if (govTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.socialBox:
                if (socialTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.militaryBox:
                if (militaryTag.isChecked()) {
                    //do something
                } else {
                    //do something else
                }
                break;
        }
    }

    private boolean allChecked() {
        checkBoxes[0] = sportsTag;
        checkBoxes[1] = entTag;
        checkBoxes[2] = moneyTag;
        checkBoxes[3] = techTag;
        checkBoxes[4] = envTag;
        checkBoxes[5] = govTag;
        checkBoxes[6] = socialTag;
        checkBoxes[7] = militaryTag;

        boolean allChecked = true;

        for (int i = 0; i < checkBoxes.length; i++) {
            if (!checkBoxes[i].isChecked()) {
                allChecked = false;
                break;
            }
        }
        return allChecked;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener callback) {
        this.callback = callback;
    }

    public interface OnItemSelectedListener {
        void changeFragments();
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            if (allChecked()) {
                if (callback != null) {
                    callback.changeFragments();
                }
            } else {
                Toast.makeText(getContext(), "Please select at least one topic.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
