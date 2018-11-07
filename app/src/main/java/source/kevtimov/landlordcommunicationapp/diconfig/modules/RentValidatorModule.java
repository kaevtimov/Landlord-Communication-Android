package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.validation.RentValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class RentValidatorModule {

    @Provides
    @Singleton
    public Validator<Rent> rentValidator(){
        return new RentValidator();
    }
}
