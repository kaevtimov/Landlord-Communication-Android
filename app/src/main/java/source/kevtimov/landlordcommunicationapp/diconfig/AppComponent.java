package source.kevtimov.landlordcommunicationapp.diconfig;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import source.kevtimov.landlordcommunicationapp.LandlordCommunicationApplication;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.AgentModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.AsyncModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.BitmapAgentModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.CardParserModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.CardRepositoryModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.CardServiceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.HttpModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PaymentParserModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PaymentRepositoryModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PaymentServiceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PlaceParserModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PlaceRepositoryModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PlaceServiceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.RentParserModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.RentRepositoryModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.RentServiceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.UserParserModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.UserRepositoryModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.UserServiceModule;

@Singleton
@Component(modules = {
        PaymentParserModule.class,
        CardParserModule.class,
        PaymentServiceModule.class,
        CardServiceModule.class,
        CardRepositoryModule.class,
        PaymentRepositoryModule.class,
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
