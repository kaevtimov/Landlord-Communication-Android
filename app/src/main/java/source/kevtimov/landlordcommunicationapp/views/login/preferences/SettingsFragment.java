package source.kevtimov.landlordcommunicationapp.views.login.preferences;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {



    @Inject
    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
