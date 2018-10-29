package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.ContractsPlaceManagement;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementFragment;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementPresenter;

@Module
public abstract class PlaceManagementModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PlaceManagementFragment placeManagementFragment();

    @ActivityScoped
    @Binds
    abstract ContractsPlaceManagement.Presenter placeManagementPresenter(PlaceManagementPresenter placeManagementPresenter);

}
