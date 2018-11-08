package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.RatingRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpRatingService;
import source.kevtimov.landlordcommunicationapp.services.RatingService;

@Module
public class RatingServiceModule {

    @Provides
    public RatingService getService(RatingRepository repo){
        return new HttpRatingService(repo);
    }
}
