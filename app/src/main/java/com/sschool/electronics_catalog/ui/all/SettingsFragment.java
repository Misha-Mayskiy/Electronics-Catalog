package com.sschool.electronics_catalog.ui.all;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.sschool.electronics_catalog.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}