package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.ContractsSelectPlace;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlaceFragment;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlacePresenter;

@Module
public abstract class SelectPlaceModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SelectPlaceFragment selectPlaceFragment();

    @ActivityScoped
    @Binds
    abstract ContractsSelectPlace.Presenter selectPlacePresenter(SelectPlacePresenter selectPlacePresenter);

}
