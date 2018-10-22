package source.kevtimov.landlordcommunicationapp;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import source.kevtimov.landlordcommunicationapp.diconfig.DaggerAppComponent;

public class LandlordCommunicationApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent.builder().application(this).build();
    }
}
