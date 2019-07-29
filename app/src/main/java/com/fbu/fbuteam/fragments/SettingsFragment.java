package com.fbu.fbuteam.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.fbu.fbuteam.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    //To retrieve instances of preferences saved as key value pairs
    //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(android.content.Context);

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
