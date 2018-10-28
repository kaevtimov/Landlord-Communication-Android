package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.views.login.home.ContractsHome;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeFragment;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomePresenter;

@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ActivityScoped
    @Binds
    abstract ContractsHome.Presenter homePresenter(HomePresenter homePresenter);

}
