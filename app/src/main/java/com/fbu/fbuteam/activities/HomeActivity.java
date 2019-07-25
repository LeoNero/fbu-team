package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.fbu.fbuteam.R;
import com.parse.ParseUser;
import com.fbu.fbuteam.fragments.NewsFragment;
import com.fbu.fbuteam.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class  HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bnvHome;
    static MenuItem miActionProgressItem;


    private FragmentManager fragmentManager;
    private Fragment newsFragment;
    private Fragment profileFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupComponents();
        setupToolbar();
        setupFragments();
        setupBottomNavigationViewItemSelectedListener();
        setupDefaultFragment();
    }

    private void setupComponents() {
        toolbar = findViewById(R.id.toolbar);
        bnvHome = findViewById(R.id.bottomNavigationView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        setLogoutBtnListener(toolbar);
    }

    private void setLogoutBtnListener(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.miLogout:
                    ParseUser.logOut();
                    goToLogin();
                    break;
            }
            return true;
        });
    }

    private void goToLogin() {
        final Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupFragments() {
        fragmentManager = getSupportFragmentManager();

        newsFragment = new NewsFragment();
        profileFragment = new ProfileFragment();
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
        currentFragment = newsFragment;
    }

    private void openSelectedFragment() {
        fragmentManager.beginTransaction().replace(R.id.frameLayoutHome, currentFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        miActionProgressItem = menu.findItem(R.id.miActionProgress);

        return super.onPrepareOptionsMenu(menu);
    }

    public static void showProgressBar() {
        miActionProgressItem.setVisible(true);
    }

    public static void hideProgressBar() {
        miActionProgressItem.setVisible(false);
    }

}
