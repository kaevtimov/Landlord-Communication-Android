package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.base.MessageRepository;
import source.kevtimov.landlordcommunicationapp.repositories.implementation.SqlMessageRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class MessageRepositoryModule {

    @Provides
    @Singleton
    public MessageRepository messageRepository(HttpRequester requester, JsonParser<Message> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/messages";
        return new SqlMessageRepository(url, requester, parser);
    }
}
