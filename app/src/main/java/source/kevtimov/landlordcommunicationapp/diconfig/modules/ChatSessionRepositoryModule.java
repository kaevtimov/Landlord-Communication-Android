package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.base.ChatSessionRepository;
import source.kevtimov.landlordcommunicationapp.repositories.implementation.SqlChatSessionRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class ChatSessionRepositoryModule {

    @Provides
    @Singleton
    public ChatSessionRepository chatRepository(HttpRequester requester, JsonParser<ChatSession> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/chats";
        return new SqlChatSessionRepository(url, requester, parser);
    }
}
