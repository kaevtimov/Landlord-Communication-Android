package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.views.login.ContractsLogin;
import source.kevtimov.landlordcommunicationapp.views.login.LoginFragment;
import source.kevtimov.landlordcommunicationapp.views.login.LoginPresenter;

@Module
public abstract class LogInModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ActivityScoped
    @Binds
    abstract ContractsLogin.Presenter loginPresenter(LoginPresenter loginPresenter);


}
