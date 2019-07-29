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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigIdeasFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private Button nextButton;
    private BigIdeasAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvBigIdeas;
    private ArrayList<CheckBox> allTags = new ArrayList<>();
    private ArrayList<CheckBox> selectedTags = new ArrayList<>();

    public static List<Node> selectedBigIdeas = new ArrayList<>();
    public List<Node> bigIdeas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnNextClickListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.bigideas_fragment, container, false);
        initializeObjects(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBigIdeas = (RecyclerView) view.findViewById(R.id.rvBigIdeas);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvBigIdeas.setLayoutManager(layoutManager);
        adapter = new BigIdeasAdapter(getContext(), bigIdeas);


        queryNodes();
        goToDetailTags();
    }

    private void initializeObjects(View view) {
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        nextButton = view.findViewById(R.id.nextButton);
    }

    private boolean atLeastOneChecked() {
        boolean atLeastOneChecked = false;
        for (int i = 0; i < allTags.size(); i++) {
            if (allTags.get(i).isChecked()) {
                selectedTags.add(allTags.get(i));
                atLeastOneChecked = true;
                break;
            }
        }
        return atLeastOneChecked;
    }

    public void setOnNextClickListener(OnNextClickListener callback) {
        this.callback = callback;
    }

    public interface OnNextClickListener {
        void goToDetailTagsFragment(List<Node> selectedBigIdeas);
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

    private List<Node> getSelectedBigIdeas() {
        List<Node> b = new ArrayList<>();
        for (int i = 0; i < allTags.size(); i++) {
            if (allTags.get(i).isChecked()) {
                Node bigIdea = bigIdeas.get(i);
                Log.d("LL", bigIdeas.get(i).getName());
                b.add(bigIdea);
            }
        }
        return b; //this is the list of selected big ideas
    }

    private void queryNodes() {
        Node.Query query = new Node.Query();
        query.withRelations();
        query.fromType("big-idea");

        query.findInBackground((response, e) -> { //response queries for the big ideas
            if (e == null) {
                for (Node a : response) {
                    bigIdeas.add(a);
                    List<Node> children = a.getChildren(); //list containing a list of the children of each big idea ([],[],[]) --> get a list<> of children for every big idea
                    if (children != null) { //if every big idea has children
                        for (Node b : children) { //b represents each "mini" list of children
                            Log.d("CHILDREN", b.getName()); //children
                            Log.d("BB", children.size()+"");
                        }
                    }
                }
                populateBigIdeas(bigIdeas);
                Log.d("DD", bigIdeas.get(0).getName());
                Log.d("EE", bigIdeas.get(1).getName());
                Log.d("FF",  bigIdeas.get(2).getName());
                Log.d("GG",  bigIdeas.get(3).getName());
                Log.d("HH", bigIdeas.get(4).getName());
                Log.d("II", bigIdeas.get(5).getName());
                Log.d("JJ",  bigIdeas.get(6).getName());
                Log.d("KK",  bigIdeas.get(7).getName());
            }
        });
    }

    private void populateBigIdeas(List<Node> bigIdeas) {
        for (int i = 0; i < bigIdeas.size(); i++) {
            CheckBox bigIdeaBox = new CheckBox(getContext());
            bigIdeaBox.setText(bigIdeas.get(i).getName());
            allTags.add(bigIdeaBox);
        }
        Log.d("ALLTAGS", allTags.size()+"");
    }
}
