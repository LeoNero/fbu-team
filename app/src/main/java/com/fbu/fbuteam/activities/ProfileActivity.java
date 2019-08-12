package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.adapters.ProfileViewPagerAdapter;
import com.fbu.fbuteam.fragments.EditProfileFragment;
import com.fbu.fbuteam.models.User;
import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvUsername;
    private TextView tvFollowers;
    private TextView tvFollowing;
    private ImageView ivPhoto;
    private ViewPager vpTabs;
    private TabLayout tlTabs;

    private ProfileViewPagerAdapter profileViewPagerAdapter;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupComponents();
        setupViewPagerAdapter();
        setupTabsIcon();

        getCurrentUser();
        goToProfilePicture();
    }

    private void setupComponents() {
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        ivPhoto = findViewById(R.id.ivPhoto);
        vpTabs = findViewById(R.id.vpTabs);
        tlTabs = findViewById(R.id.tlTabs);
    }

    private void setupViewPagerAdapter() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        profileViewPagerAdapter = new ProfileViewPagerAdapter(this, fragmentManager);
        vpTabs.setAdapter(profileViewPagerAdapter);
        tlTabs.setupWithViewPager(vpTabs);
    }

    private void setupTabsIcon() {
        int[] tabIcons = new int[]{
                android.R.drawable.ic_lock_power_off,
                android.R.drawable.ic_input_add,
        };

        int[] tabText = new int[]{
                R.string.topics,
                R.string.saved_posts,
        };

        for (int i = 0; i < 2; i++) {
            tlTabs.getTabAt(i).setIcon(tabIcons[i]);
            tlTabs.getTabAt(i).setText(tabText[i]);
        }
        tlTabs.setTabTextColors(Color.WHITE, Color.rgb(121,14,139));
    }

    private void getCurrentUser() {
        currentUser = User.getCurrentUser();

        if (currentUser == null) {
            goToLogin();
            return;
        }

        displayUserInformation();
    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void displayUserInformation() {
        tvName.setText(currentUser.getName());
        tvUsername.setText(currentUser.getUsername());

        tvFollowers.setText(getFollowersText());
        tvFollowing.setText(getFollowingText());
    }

    private String getFollowersText() {
        int followersCount = currentUser.getFollowers();

        if (followersCount == 1) {
            return "1 follower";
        }

        return followersCount + " followers";
    }

    private String getFollowingText() {
        int followingCount = currentUser.getFollowing();

        return followingCount + " following";
    }

    private void goToProfilePicture() {
        ivPhoto.setOnClickListener(view -> goToEdit());
    }

    private void goToEdit() {
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.editContainer, editProfileFragment);
        transaction.commit();
    }
}
