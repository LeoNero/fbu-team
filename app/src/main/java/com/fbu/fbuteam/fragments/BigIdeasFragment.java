package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    CheckBox[] allTags = new CheckBox[8];
    ArrayList<CheckBox> selectedTags = new ArrayList<>();

    //****TESTS****

    public static List<List<String>> selectedBigIdeas = new ArrayList<>();

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
        createCheckBoxArray();
        queryNodes();
        goToDetailTags();
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

    public interface OnNextClickListener {
        void goToDetailTagsFragment(List<List<String>> selectedBigIdeas);
    }

    private void goToDetailTags() {
        nextButton.setOnClickListener(view1 -> {
            if (atLeastOneChecked()) {
                if (callback != null) {
                    selectedBigIdeas = getSelectedBigIdeas();
                    callback.goToDetailTagsFragment(selectedBigIdeas);
                }
            } else {
                Toast.makeText(getContext(), "Please select at least one topic.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<List<String>> getSelectedBigIdeas() {
        List<List<String>> b = new ArrayList<>();
        for (int i = 0; i < allTags.length; i++) {
            if (allTags[i].isChecked()) {
                List<String> children = new ArrayList<>(Arrays.asList(i + "", allTags[i].getText().toString()));
                b.add(children);
            }
        }
        return b;
    }

    private void queryNodes() {
        List<Node> bigIdeas = new ArrayList<>();
        Node.Query query = new Node.Query();
        query.withRelations();
        query.fromType("big-idea");

        query.findInBackground((response, e) -> { //response queries for the big ideas
            if (e == null) {
                for (Node a : response) {
                    bigIdeas.add(a);
                    Log.d("RESPONSE", a.getName()); //a represents each selected big idea
                    Log.d("AA", bigIdeas.size()+"");
                    List<Node> children = a.getChildren(); //list containing a list of the children of each big idea ([],[],[]) --> get a list<> of children for every big idea
                    if (children != null) { //if every big idea has children
                        for (Node b : children) { //b represents each "mini" list of children
                            Log.d("CHILDREN", b.getName()); //children
                        }
                    }
                }
                populateBigIdeas(bigIdeas);
            }
        });
    }

    private void populateBigIdeas(List<Node> bigIdeas) {
        sportsTag.setText(bigIdeas.get(0).getName());
        entTag.setText(bigIdeas.get(1).getName());
        moneyTag.setText(bigIdeas.get(2).getName());
        techTag.setText(bigIdeas.get(3).getName());
        envTag.setText(bigIdeas.get(4).getName());
        govTag.setText(bigIdeas.get(5).getName());
        socialTag.setText(bigIdeas.get(6).getName());
        militaryTag.setText(bigIdeas.get(7).getName());
    }
}
