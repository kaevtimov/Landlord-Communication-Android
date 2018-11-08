package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.ContractsMyUsers;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.MyUsersFragment;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.MyUsersPresenter;

@Module
public abstract class MyUsersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyUsersFragment myUsersFragment();

    @ActivityScoped
    @Binds
    abstract ContractsMyUsers.Presenter myUsersPresenter(MyUsersPresenter loginPresenter);
}
