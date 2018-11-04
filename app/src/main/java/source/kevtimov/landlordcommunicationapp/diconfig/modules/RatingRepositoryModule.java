package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.RatingRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlRatingRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class RatingRepositoryModule {

    @Provides
    @Singleton
    public RatingRepository ratingRepository(HttpRequester requester, JsonParser<Rating> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/ratings";
        return new SqlRatingRepository(url, requester, parser);
    }
}
