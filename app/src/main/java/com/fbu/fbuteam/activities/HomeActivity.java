package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.fragments.NewsFragment;
import com.fbu.fbuteam.fragments.ProfileFragment;
import com.fbu.fbuteam.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bnvHome;
    private static MenuItem menuActionProgressItem;
    private static MenuItem searchItem;
    private static MenuItem settings;

    private FragmentManager fragmentManager;
    private Fragment newsFragment;
    private Fragment profileFragment;
    private Fragment settingsFragment;
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
        toolbar.setBackgroundColor(Color.rgb(121, 14, 139));
    }

    private void setupFragments() {
        fragmentManager = getSupportFragmentManager();
        newsFragment = new NewsFragment();
        profileFragment = new ProfileFragment();
        settingsFragment = new SettingsFragment();

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

        setUpMenuItems(menu);
        setUpSearch(menu);
      
      return super.onCreateOptionsMenu(menu);
    }
}


private void setUpMenuItems(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_settings);
        MenuItem  progressItem = menu.findItem(R.id.miActionProgress);
        final ProgressBar progressBar = (ProgressBar) MenuItemCompat.getActionView(progressItem);
        final MenuItem settings = (MenuItem) MenuItemCompat.getActionView(menuItem);
    }

    private void setUpSearch(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                NewsFragment.searchNameQuery(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NewsFragment.searchNameQuery(newText);
                Toast.makeText(HomeActivity.this, "Result:"+newText, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuActionProgressItem = menu.findItem(R.id.miActionProgress);
        settings = menu.findItem(R.id.action_settings);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_settings) {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void showProgressBar() {
        menuActionProgressItem.setVisible(true);
    }

    public static void hideProgressBar() {
        menuActionProgressItem.setVisible(false);
    }
}
