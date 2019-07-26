package com.fbu.fbuteam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.BigIdeasFragment;
import com.fbu.fbuteam.fragments.DetailTagsFragment;
import com.fbu.fbuteam.fragments.TestFragment1;
import com.fbu.fbuteam.models.Node;

import java.util.ArrayList;
import java.util.List;

public class TagActivity extends AppCompatActivity implements BigIdeasFragment.OnNextClickListener, DetailTagsFragment.OnNextClickListener {

    private int currentUserSelection = -1;
    private List<Node> selectedBigIdeas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_activity);
        goToBigIdeas();
    }

    private void goToBigIdeas() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.placeholder, new BigIdeasFragment());
        ft.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof BigIdeasFragment) {
            BigIdeasFragment bigIdeasFragment = (BigIdeasFragment) fragment;
            bigIdeasFragment.setOnNextClickListener(this);
        }

        if (fragment instanceof DetailTagsFragment) {
            DetailTagsFragment detailsTagsFragment = (DetailTagsFragment) fragment;
            detailsTagsFragment.setOnNextClickListener(this);
        }
    }

    @Override
    public void goToDetailTagsFragment(List<Node> selectedBigIdeas) {
        currentUserSelection = -1;
        this.selectedBigIdeas = selectedBigIdeas;

        populateNextDetailsFragment();

    }

    @Override
    public void goToNextDetailsFragment() {
        populateNextDetailsFragment();
    }

    private void populateNextDetailsFragment() {
        currentUserSelection++;

        if (currentUserSelection >= selectedBigIdeas.size()) {
            Toast.makeText(getApplicationContext(), "Home Screen", Toast.LENGTH_SHORT).show();
            goToHome();
            return;
        }

        Node bigIdea = selectedBigIdeas.get(currentUserSelection);

        DetailTagsFragment detailTagsFragment = DetailTagsFragment.newInstance(bigIdea);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, detailTagsFragment);
        ft.addToBackStack("backstack");
        ft.commit();
    }

    private void goToHome() {
        Intent intent = new Intent(TagActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
