package com.fbu.fbuteam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.BigIdeasFragment;
import com.fbu.fbuteam.fragments.DetailTagsFragment;
import com.fbu.fbuteam.fragments.TestFragment1;

import java.util.List;

public class TagActivity extends AppCompatActivity implements BigIdeasFragment.OnItemSelectedListener {

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
            bigIdeasFragment.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void changeFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailTagsFragment detailTagsFragment = DetailTagsFragment.newInstance(BigIdeasFragment.nextUserSelection);
        transaction.replace(R.id.placeholder, detailTagsFragment);
        transaction.commit();
    }
}
