package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.base.ChatSessionRepository;
import source.kevtimov.landlordcommunicationapp.services.base.ChatSessionService;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpChatSessionService;

@Module
public class ChatSessionServiceModule {

    @Provides
    public ChatSessionService getService(ChatSessionRepository repo){
        return new HttpChatSessionService(repo);
    }
}
