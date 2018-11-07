package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.validation.RatingValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class RatingValidatorModule {

    @Provides
    @Singleton
    public Validator<Rating> ratingValidator(){
        return new RatingValidator();
    }
}
