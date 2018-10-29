package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.PlaceRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlPlaceRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class PlaceRepositoryModule {

    @Provides
    @Singleton
    public PlaceRepository placeRepository(HttpRequester requester, JsonParser<Place> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/places";
        return new SqlPlaceRepository(url, requester, parser);
    }
}
