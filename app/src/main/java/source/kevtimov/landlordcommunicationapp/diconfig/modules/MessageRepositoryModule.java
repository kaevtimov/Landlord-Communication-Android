package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.MessageRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlMessageRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class MessageRepositoryModule {
    @Provides
    @Singleton
    public MessageRepository messageRepository(HttpRequester httpRequester, JsonParser<Message> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/messages";
        return new SqlMessageRepository(httpRequester,parser,url);
    }
}
