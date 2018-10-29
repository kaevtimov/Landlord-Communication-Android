package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.signup.ContractsSignUp;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpFragment;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpPresenter;

@Module
public abstract class SignUpModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();

    @ActivityScoped
    @Binds
    abstract ContractsSignUp.Presenter signUpPresenter(SignUpPresenter signUpPresenter);

}
