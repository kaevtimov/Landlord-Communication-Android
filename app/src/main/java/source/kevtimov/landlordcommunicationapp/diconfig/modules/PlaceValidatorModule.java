package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.validation.PlaceValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class PlaceValidatorModule {

    @Provides
    @Singleton
    public Validator<Place> placeValidator(){
        return new PlaceValidator();
    }
}
