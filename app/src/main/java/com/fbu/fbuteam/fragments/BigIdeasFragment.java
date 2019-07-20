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
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.sportsBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.entBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.moneyBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.techBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.envBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.govBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.socialBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
            case R.id.militaryBox:
                if (checked) {
                    //do something
                } else {
                    //do something else
                }
                break;
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener callback) {
        this.callback = callback;
    }

    public interface OnItemSelectedListener {
        void changeFragments();
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            if(callback != null){
                callback.changeFragments();
            }
        });
    }
}
