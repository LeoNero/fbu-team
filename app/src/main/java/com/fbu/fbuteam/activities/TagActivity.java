package com.fbu.fbuteam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.BigIdeasFragment;

public class TagActivity extends AppCompatActivity {

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
}
