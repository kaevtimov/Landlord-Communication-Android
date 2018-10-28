package source.kevtimov.landlordcommunicationapp.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.RentRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlRentRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class RentRepositoryModule {

    @Provides
    @Singleton
    public RentRepository rentRepository(HttpRequester requester, JsonParser<Rent> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/rents";
        return new SqlRentRepository(url, requester, parser);
    }
}
