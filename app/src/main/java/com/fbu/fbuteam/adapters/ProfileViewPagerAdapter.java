package com.fbu.fbuteam.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fbu.fbuteam.fragments.SavedPostsFragment;
import com.fbu.fbuteam.fragments.TopicsFragment;

public class ProfileViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    private TopicsFragment topicsFragment;
    private SavedPostsFragment savedPostsFragment;

    public ProfileViewPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;

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
