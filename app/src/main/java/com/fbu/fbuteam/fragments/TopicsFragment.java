package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.Node;
import com.fbu.fbuteam.models.User;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.List;

public class TopicsFragment extends Fragment {

    private User currentUser;
    private List<Node> bigIdeaTags;
    private List<Node> detailTags;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBigIdeasTags();
        getDetailTags();
    }

    private void getCurrentUser() {
        currentUser = User.getCurrentUser();
    }

    private void getBigIdeasTags() {
        bigIdeaTags = currentUser.getBigIdeaTags();
        displayTags(bigIdeaTags);
    }

    private void getDetailTags() {
        detailTags = currentUser.getDetailTags();
        displayTags(detailTags);
    }

    private void displayTags(List<Node> tags) {
        for (Node tag : tags) {
            tag.fetchIfNeededInBackground((res, e) -> {
                String name = ((Node) res).getName();
                int level = ((Node) res).getLevel();

                TextView tv = new TextView(getContext());
                tv.setText(name);

                if (level == 1) {
                    tv.setTextColor(getResources().getColor(R.color.bigIdeaTopic));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.detailIdeaTopic));
                }

                //llTopics.addView(tv);
            });
        }
    }
}
