
package com.fbu.fbuteam.fragments;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fbu.fbuteam.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);


        Preference cache = (Preference) findPreference(getString(R.string.clear_cache));
        cache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(requireContext(), "Mockup!", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Preference logout = (Preference) findPreference(getString(R.string.logout));
        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                //do something
                return true;
            }
        });

        Preference deleteAccount = (Preference) findPreference(getString(R.string.delete_account));
        deleteAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                //do something
                return true;
            }

        });

    }
}
