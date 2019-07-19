package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;

public class BigIdeasFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private Button sportsTag;
    private Button entTag;
    private Button moneyTag;
    private Button techTag;
    private Button envTag;
    private Button govTag;
    private Button socialTag;
    private Button militaryTag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bigideas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeObjects(view);
    }

    private void initializeObjects(View view) {
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        sportsTag = view.findViewById(R.id.sportsTag);
        entTag = view.findViewById(R.id.entTag);
        moneyTag = view.findViewById(R.id.moneyTag);
        techTag = view.findViewById(R.id.techTag);
        envTag = view.findViewById(R.id.envTag);
        govTag = view.findViewById(R.id.govTag);
        socialTag = view.findViewById(R.id.socialTag);
        militaryTag = view.findViewById(R.id.militaryTag);
    }
}
