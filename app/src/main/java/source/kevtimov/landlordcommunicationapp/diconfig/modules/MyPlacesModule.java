package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.ContractsMyPlaces;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesFragment;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesPresenter;

@Module
public abstract class MyPlacesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyPlacesFragment myPlacesFragment();

    @ActivityScoped
    @Binds
    abstract ContractsMyPlaces.Presenter myPlacesPresenter(MyPlacesPresenter myPlacesPresenter);


}
