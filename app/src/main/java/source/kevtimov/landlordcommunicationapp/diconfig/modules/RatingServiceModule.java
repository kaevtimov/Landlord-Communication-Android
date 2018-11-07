package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.repositories.base.RatingRepository;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpRatingService;
import source.kevtimov.landlordcommunicationapp.services.base.RatingService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class RatingServiceModule {

    @Provides
    public RatingService getService(RatingRepository repo, Validator<Rating> validator){
        return new HttpRatingService(repo, validator);
    }
}
