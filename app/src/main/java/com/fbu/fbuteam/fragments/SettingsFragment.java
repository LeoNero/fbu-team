package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toolbar;

import com.fbu.fbuteam.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    public ListPreference KEY_LANGUAGE_PREFERENCE;
    public Preference KEY_CLEAR_CACHE;
    public ListPreference KEY_LINK_SOCIAL;
    public SwitchPreference KEY_TURN_OFF_NOTIFICATIONS;
    public SwitchPreference KEY_ENABLE_QUIET_HOURS;
    public ListPreference KEY_CHANGE_NOTIFICATION_SOUND;
    public CheckBoxPreference KEY_NEW_FOLLOW;
    public CheckBoxPreference KEY_NEW_STORY;
    public CheckBoxPreference KEY_NEW_LIKE_ON_STORY;
    public SwitchPreference KEY_TURN_OFF_LOCATION;
    public CheckBoxPreference KEY_MAKE_ACCOUNT_PRIVATE;
    public Preference KEY_RESET_PASSWORD;
    public Preference KEY_DELETE_ACCOUNT;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        keyInitializations();
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceManager().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //TODO: do as a stretch feature

    }

    public void keyInitializations() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        KEY_LANGUAGE_PREFERENCE = (ListPreference) getPreferenceManager().findPreference("language_preference");
        KEY_CLEAR_CACHE = getPreferenceManager().findPreference("clear_cache");
        KEY_LINK_SOCIAL = (ListPreference) getPreferenceManager().findPreference("social_media_preference");
        KEY_TURN_OFF_NOTIFICATIONS = (SwitchPreference) getPreferenceManager().findPreference("offNotifications");
        KEY_ENABLE_QUIET_HOURS = (SwitchPreference) getPreferenceManager().findPreference("quietHours");
        KEY_CHANGE_NOTIFICATION_SOUND = (ListPreference) getPreferenceManager().findPreference("change_notification_sound");
        KEY_NEW_FOLLOW = (CheckBoxPreference) getPreferenceManager().findPreference("new_follow");
        KEY_NEW_STORY = (CheckBoxPreference) getPreferenceManager().findPreference("new_story");
        KEY_NEW_LIKE_ON_STORY = (CheckBoxPreference) getPreferenceManager().findPreference("new_like_on_story");
        KEY_TURN_OFF_LOCATION = (SwitchPreference) getPreferenceManager().findPreference("turn_off_location");
        KEY_MAKE_ACCOUNT_PRIVATE = (CheckBoxPreference) getPreferenceManager().findPreference("make_account_private");
        KEY_RESET_PASSWORD = getPreferenceManager().findPreference("reset_password");
        KEY_DELETE_ACCOUNT = getPreferenceManager().findPreference("delete_account");
    }

}





