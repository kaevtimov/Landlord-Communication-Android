package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.repositories.base.RentRepository;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpRentService;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class RentServiceModule {

    @Provides
    public RentService getService(RentRepository repo, Validator<Rent> validator){
        return new HttpRentService(validator, repo);
    }
}
