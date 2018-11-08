package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.ContractsPlaceDetails;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.PlaceDetailsFragment;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.PlaceDetailsPresenter;

@Module
public abstract class PlaceDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PlaceDetailsFragment placeDetailsFragment();

    @ActivityScoped
    @Binds
    abstract ContractsPlaceDetails.Presenter placeDetailsPresenter(PlaceDetailsPresenter placeDetailsPresenter);


}
