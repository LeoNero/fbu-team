package com.fbu.fbuteam.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TestFragment1 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> nodeChildren = getArguments().getStringArrayList("nodeChildren");
    }

    public static TestFragment1 newInstance(List<String> nodeChildren) {
        TestFragment1 testFragment1 = new TestFragment1();
        Bundle args = new Bundle();
        args.putStringArrayList("nodeChildren", (ArrayList) nodeChildren);
        testFragment1.setArguments(args);
        return testFragment1;
    }
}
