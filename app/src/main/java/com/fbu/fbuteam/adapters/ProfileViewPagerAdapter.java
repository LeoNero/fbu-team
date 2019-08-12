package com.fbu.fbuteam.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fbu.fbuteam.fragments.SavedPostsFragment;
import com.fbu.fbuteam.fragments.TopicsFragment;

public class ProfileViewPagerAdapter extends FragmentPagerAdapter {
    private TopicsFragment topicsFragment;
    private SavedPostsFragment savedPostsFragment;

    public ProfileViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        createFragments();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return topicsFragment;
        }

        return savedPostsFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    private void createFragments() {
        topicsFragment = new TopicsFragment();
        savedPostsFragment = new SavedPostsFragment();
    }
}
