package com.fbu.fbuteam.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.NewsFragment;
import com.fbu.fbuteam.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bnvHome;

    private FragmentManager fragmentManager;
    private Fragment newsFragment;
    private Fragment profileFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupComponents();
        setupFragments();
        setupBottomNavigationViewItemSelectedListener();
        setupDefaultFragment();
    }

    private void setupComponents() {
        toolbar = findViewById(R.id.toolbar);
        bnvHome = findViewById(R.id.bottomNavigationView);
    }

    private void setupFragments() {
        fragmentManager = getSupportFragmentManager();
        newsFragment = new NewsFragment();
        profileFragment = new ProfileFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupBottomNavigationViewItemSelectedListener() {
        bnvHome.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.action_news:
                    currentFragment = newsFragment;
                    break;
                case R.id.action_profile:
                    currentFragment = profileFragment;
                    break;
            }
            openSelectedFragment();
            return true;
        });
    }

    private void setupDefaultFragment() {
        bnvHome.setSelectedItemId(R.id.action_news);
        currentFragment = profileFragment;
    }

    private void openSelectedFragment() {
        fragmentManager.beginTransaction().replace(R.id.frameLayoutHome, currentFragment).commit();
    }

}
