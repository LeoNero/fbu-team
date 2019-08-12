package com.fbu.fbuteam.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.LoginActivity;
import com.fbu.fbuteam.adapters.ProfileViewPagerAdapter;
import com.fbu.fbuteam.models.User;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupComponents(view);
        setupViewPagerAdapter();
        setupTabsIcon();
        getCurrentUser();
        imageRounded();
    }

    private void imageRounded() {
        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.profile_picture)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        ivPhoto.setImageBitmap(imageRounded);
    }

    private void setupComponents(View view) {
        tvName = view.findViewById(R.id.tvName);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvFollowers = view.findViewById(R.id.tvFollowers);
        tvFollowing = view.findViewById(R.id.tvFollowing);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        vpTabs = view.findViewById(R.id.vpTabs);
        tlTabs = view.findViewById(R.id.tlTabs);
    }

    private void setupViewPagerAdapter() {
        FragmentManager fragmentManager = getChildFragmentManager();
        profileViewPagerAdapter = new ProfileViewPagerAdapter(fragmentManager);
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
            Log.d("SDDD", "AA");
            goToLogin();
            return;
        }

        displayUserInformation();
    }


    private void goToLogin() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
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
}

