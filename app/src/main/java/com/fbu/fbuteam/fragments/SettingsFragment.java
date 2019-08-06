package com.fbu.fbuteam.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.activities.LoginActivity;
import com.parse.DeleteCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);


        Preference cache = (Preference) findPreference(getString(R.string.clear_cache));
        cache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(requireContext(), "Test!", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Preference logout = (Preference) findPreference(getString(R.string.logout));
        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                logout();
                return true;
            }
        });

        Preference deleteAccount = (Preference) findPreference(getString(R.string.delete_account));
        deleteAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                deleteAcc();
                logout();
                return true;
            }

        });

    }

    private void logout() {
        ParseUser user = ParseUser.getCurrentUser();
        user.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    if (user == null) {
                        goToLogin();
                    }
                } else {
                    Toast.makeText(getContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
        });
    }

    private void deleteAcc() {
        ParseUser user = ParseUser.getCurrentUser();
        user.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getContext(), "Account deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    goToLogin();
                } else {
                    Toast.makeText(getContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });
    }

    private void goToLogin(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}