package source.kevtimov.landlordcommunicationapp.diconfig;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import source.kevtimov.landlordcommunicationapp.LandlordCommunicationApplication;

@Singleton
@Component(modules = {
        BitmapAgentModule.class,
        RentParserModule.class,
        PlaceParserModule.class,
        PlaceRepositoryModule.class,
        PlaceServiceModule.class,
        RentRepositoryModule.class,
        RentServiceModule.class,
        UserServiceModule.class,
        AgentModule.class,
        UserRepositoryModule.class,
        UserParserModule.class,
        HttpModule.class,
        AsyncModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<LandlordCommunicationApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
