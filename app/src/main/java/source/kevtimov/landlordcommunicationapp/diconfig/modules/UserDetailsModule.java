package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.userdetails.ContractsUserDetails;
import source.kevtimov.landlordcommunicationapp.views.login.userdetails.UserDetailsFragment;
import source.kevtimov.landlordcommunicationapp.views.login.userdetails.UserDetailsPresenter;

@Module
public abstract class UserDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract UserDetailsFragment userDetailsFragment();

    @ActivityScoped
    @Binds
    abstract ContractsUserDetails.Presenter userDetailsPresenter(UserDetailsPresenter userDetailsPresenter);

}
