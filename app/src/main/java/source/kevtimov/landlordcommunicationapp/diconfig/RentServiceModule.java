package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.RentRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpRentService;
import source.kevtimov.landlordcommunicationapp.services.RentService;

@Module
public class RentServiceModule {

    @Provides
    public RentService getService(RentRepository repo){
        return new HttpRentService(repo);
    }
}
