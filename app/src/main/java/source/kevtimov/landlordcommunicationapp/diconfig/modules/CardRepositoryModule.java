package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.CardRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlCardRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class CardRepositoryModule {

    @Provides
    @Singleton
    public CardRepository cardRepository(HttpRequester requester, JsonParser<Card> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/cards";
        return new SqlCardRepository(url, requester, parser);
    }
}
