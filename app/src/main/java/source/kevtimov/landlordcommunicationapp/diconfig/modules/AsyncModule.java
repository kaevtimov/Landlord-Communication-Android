package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.async.AsyncSchedulerProvider;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;

@Module
public class AsyncModule {

    @Provides
    @Singleton
    public SchedulerProvider getScheduler(){
        return AsyncSchedulerProvider.getSchedulerProvider();
    }
}
