package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.preferences.SettingsFragment;

@Module
public abstract class SettingsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SettingsFragment settingsFragment();

}
