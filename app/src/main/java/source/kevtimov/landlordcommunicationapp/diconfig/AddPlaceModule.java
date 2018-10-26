package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlaceFragment;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlacePresenter;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.ContractsAddPlace;

@Module
public abstract class AddPlaceModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddPlaceFragment addPlaceFragment();

    @ActivityScoped
    @Binds
    abstract ContractsAddPlace.Presenter addPlacePresenter(AddPlacePresenter addPlacePresenter);


}
