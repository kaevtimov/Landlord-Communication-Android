package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.MessageRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpMessageService;
import source.kevtimov.landlordcommunicationapp.services.MessageService;

@Module
public class MessageServiceModule {
    @Provides
    public MessageService getService(MessageRepository repo){
        return new HttpMessageService(repo);
    }
}
